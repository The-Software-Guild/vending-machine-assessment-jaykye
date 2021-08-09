package controller;

import dao.VendingMachinePersistenceException;
import dto.Changes;
import dto.Item;
import service.VendingMachineInsufficientFundException;
import service.VendingMachineInvalidCashValueException;
import service.VendingMachineNoInventoryException;
import service.VendingMachineService;
import ui.VendingMachineView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class VendingMachineController {
    VendingMachineService service;
    VendingMachineView view;

    public VendingMachineController() {
    }

    public VendingMachineController(VendingMachineService service,
                                    VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        List<Item> items;

        try {
            // 0. Load Data
            startup();

            // 1. Get Items and display items.
            items = getAndDisplayItems(); // get list here.

            // 2. Accept cash
            acceptFund();

            // 3. GetItemSelection -- only allowed to choose one.
            getSelectionAndSellItem(items);

            // 4. give out changes here.
            dispenseChanges();

            // Program shuts down.
            shutdown();
        }

        catch (VendingMachinePersistenceException e){
            System.out.println(e.getMessage());
            view.displayDataErrorMessage();
        }

    }

    private void startup() throws VendingMachinePersistenceException  {
        service.loadData();
        view.displayGreetingMessage();
    }

    private List<Item> getAndDisplayItems(){
        List<Item> items = service.getAllItems();
        view.displayItems(items);
        return items;
    }

    private void acceptFund() {
        boolean hasError = false;

        do {
            String fundAmountString = view.displayMessageAndGetFund();

            try {
                BigDecimal fund = service.processFunding(fundAmountString);
                service.setRemainingCash(service.getRemainingCash().add(fund));
                hasError = false;
            } catch (VendingMachineInvalidCashValueException e) {
                hasError = true;
                view.displayWrongFund();
            }
        }
        while (hasError);

        view.displayRemainingFund(service.getRemainingCash());
    }

    private void getSelectionAndSellItem(List<Item> allItemList) throws
            VendingMachinePersistenceException {

        boolean selectItemAgain = false;
        int subMenuUserSelection = 0;

        do {
            int selectedItemInt = view.getItemSelection(allItemList);
            if (selectedItemInt == allItemList.size()+1){  // The following number of the max index is used as Exit.
                return;
            }

            Item selectedItem = allItemList.get(selectedItemInt-1);  // Menu item starts from 1. Need to shift.
            String selectedItemName = selectedItem.getName();

            try {
                BigDecimal cashAmount = service.getRemainingCash();
                BigDecimal remainingCash = service.sellItem(selectedItemName, cashAmount);
                service.setRemainingCash(remainingCash);
                view.displaySuccessfulPurchaseMessage();

                selectItemAgain = false;
            }

            catch (VendingMachineNoInventoryException e) {
                view.displayErrorMessage(e.getMessage());
                subMenuUserSelection = view.getNoInventoryAction();

                switch (subMenuUserSelection){
                    case 1:
                        selectItemAgain = true;
                        break;
                    case 2:
                        selectItemAgain = false;
                        break;
                }
            }

            catch (VendingMachineInsufficientFundException e) {
                view.displayErrorMessage(e.getMessage());
                subMenuUserSelection = view.getInsufficientFundAction();

                switch (subMenuUserSelection){
                    case 1:
                        selectItemAgain = true;
                        break;
                    case 2:
                        selectItemAgain = true;
                        acceptFund();
                        break;
                    case 3:
                        selectItemAgain = false;
                        break;
                }
            }
        }
        while (selectItemAgain);
    }

    private void dispenseChanges(){
        // Dispense changes.
        BigDecimal remainingCash = service.getRemainingCash();
        view.displayRemainingFund(remainingCash);
        Map<Changes, Integer> changesToGive = service.calculateChangeToGive(remainingCash);
        view.displayReturnChanges(changesToGive);
    }

    private void shutdown() throws VendingMachinePersistenceException{
        service.writeData();
        view.displayGoodbyeMessage();
    }



}
