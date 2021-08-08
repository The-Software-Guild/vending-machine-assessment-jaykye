package controller;

import dao.VendingMachinePersistenceException;
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
        BigDecimal remainingCash = new BigDecimal("0");

        try {
            // 1. Display menu
            displayItems();

            // 2. Accept cash
            BigDecimal cashAmount = acceptFund();

            // 3. GetItemSelection -- only allowed to choose one.
            getSelectionAndSellItem(cashAmount);

            // give out changes here.

            // Program shuts down.
            shutdown();
        }

        catch (VendingMachinePersistenceException e){
            System.out.println(e.getMessage());
            view.displayDataErrorMessage();
        }

    }

    private void displayItems() throws VendingMachinePersistenceException {
        service.loadData();
        view.displayGreetingMessage();
        List<Item> items = service.getAllItems();
        view.displayItems(items);
    }

    private BigDecimal acceptFund() {
        boolean hasError = false;
        do {
            String fundAmountString = view.displayMessageAndGetFund();

            try {
                BigDecimal fund = service.processFunding(fundAmountString);
                service.setRemainingCash(service.getRemainingCash().add(fund));
                hasError = false;
            } catch (VendingMachineInvalidCashValueException e) {
                hasError = true;
                // Take care of this. in a loop.
                view.displayWrongFund();
            }
        }
        while (hasError);
//            audit
        return service.getRemainingCash();
    }

    public void getSelectionAndSellItem(BigDecimal cashAmount) throws
            VendingMachinePersistenceException {
        // Catch and display exception messages in a loop. -- Validation is done in service layer.
        boolean selectItemAgain = false;
        int userSelection = 0;

        do {
            String selectedItemName = view.getItemSelection();
            Item selectedItem = service.getItem(selectedItemName);

            try {
                BigDecimal remainingCash = service.sellItem(selectedItemName, cashAmount);
                view.displaySuccessfulPurchaseMessage();

                // Dispense changes.
                Map changesToGive = service.calculateChangeToGive(remainingCash);
//                    audit // -- record pennies dispensed.
                view.displayReturnChanges(changesToGive);
                selectItemAgain = false;
            } catch (VendingMachineNoInventoryException e) {
                view.displayNoInventoryMessage();
                userSelection = view.getNoInventoryAction();
                switch (userSelection){
                    case 1:
                        selectItemAgain = true;
                        break;
                    case 2:
                        selectItemAgain = false;
                        break;
                }
            } catch (VendingMachineInsufficientFundException e) {
                view.displayNotEnoughFundMessage();
                userSelection = view.getInsufficientFundAction();
                switch (userSelection){
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

    private void shutdown() throws VendingMachinePersistenceException{
        service.writeData();
        view.displayGoodbyeMessage();
    }
}
