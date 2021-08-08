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

    public VendingMachineController(VendingMachineService service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean imDone = false;

        try {
            // 1. Display menu
            displayItems();

            // 2. Accept cash
            BigDecimal cashAmount = acceptFund();

            // 3. GetItemSelection -- only allowed to choose one.
            getSelectionAndSellItem(cashAmount);

            // Program shuts down.
            shutdown();
        }

        catch (VendingMachinePersistenceException e){
//            file was not able to persist.
        }

    }

    private void displayItems() throws VendingMachinePersistenceException {
        service.loadData();
        view.displayGreetingMessage();
        List<Item> items = service.getAllItems();
        view.displayItems(items);
    }

    private BigDecimal acceptFund() {
        BigDecimal cashAmount = new BigDecimal("0");
        boolean hasError = false;
        do {
            String fundAmountString = view.displayMessageAndGetFund();
            try {
                cashAmount = service.processFunding(fundAmountString); // service has variable (BigDecimal remaining) to store the cash amount.
                hasError = false;
            } catch (VendingMachineInvalidCashValueException e) {
                hasError = true;
                // Take care of this. in a loop.
                view.displayWrongFund();
            }
        }
        while (hasError);
//            audit
        return cashAmount;
    }

    public void getSelectionAndSellItem(BigDecimal cashAmount) throws
            VendingMachinePersistenceException {
        // Catch and display exception messages in a loop. -- Validation is done in service layer.
        boolean hasError = false;
        do {
            String selectedItemName = view.getItemSelection();
            Item selectedItem = service.getItem(selectedItemName);
            // getItemSelection can catch errors if not enough cash -> prompt
            try {
                BigDecimal remainingCash = service.sellItem(selectedItemName, cashAmount);
                view.displaySuccessfulPurchaseMessage();

                // Dispense changes.
                Map changesToGive = service.calculateChangeToGive(remainingCash);
//                    audit // -- record pennies dispensed.
                view.displayReturnChanges(changesToGive);
                hasError = false;
            } catch (VendingMachineNoInventoryException e) {
                view.displayNoInventoryMessage();
                hasError = true;
            } catch (VendingMachineInsufficientFundException e) {
                view.displayNotEnoughFundMessage();
                hasError = true;
            }
        }
        while (hasError);
    }

    private void shutdown() throws VendingMachinePersistenceException{
        service.writeData();
        view.displayGoodbyeMessage();
    }
}
