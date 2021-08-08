package controller;

import service.VendingMachineServiceImpl;
import ui.VendingMachineView;

public class VendingMachineController {
    VendingMachineServiceImpl service;
    VendingMachineView view;

    public VendingMachineController() {
    }

    public VendingMachineController(VendingMachineServiceImpl service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

//    public void run() {
//        boolean keepGoing = true;
//        int menuSelection = 0;
//        try {
//            while (keepGoing) {
//
//                menuSelection = getMenuSelection();
//
//                switch (menuSelection) {
//                    case 1:
//                        listStudents();
//                        break;
//                    case 5:
//                        keepGoing = false;
//                        break;
//                    default:
//                        unknownCommand();
//                }
//            }
//            exitMessage();
//        // Catching exits the program.
//        } catch (ClassRosterPersistenceException
//                | ClassRosterDuplicateIdException
//                | ClassRosterDataValidationException e) {
//            view.displayErrorMessage(e.getMessage());
//        }
//    }
    public void run() {
        /*
        boolean imDone = false

        try {
            while (! imDone) {
                1. Display menu
                service.loadData() -- first load data.
                view.greetingMessage()
                items = dao.getAllItems
                view.displayItemMenu(items)  // displays item name, price, inventory -- users can avoid service exception.

                -- only prompt it when it is required. -- i.e. when no cash
                if (service.remainingCash.compareTo(new BigDecimal("0"))){
                    2. Accept cash
                    view.displayInsertCash() -- controller has access to view.
                    service.acceptCash() service has variable (BigDecimal remaining) to store the cash amount.
                    audit
                }

                3. GetItemSelection -- only allowed to choose one.
                view.displaySelectItem()
                int userSelection = view.getSelection()
                Item selectedItem = service.getItem(userSelection)

                // getItemSelection can catch errors if not enough cash -> prompt


                // Maybe sellItem can throw noInventoryException, notEnoughFundException
                try {
                    service.sellItem(); -- which updates the remainingCash and inventory.
                    view.displaySuccessfulPurchaseMessage();
                    audit -- record total change amount
                    Map changesToGive = service.calculateChangesToGive (Map)
                    audit -- record pennies dispensed.
                    view.displayReturnChanges(changesToGive);
                    imDone = true;
                }
                catch (noInventory e) {
                    view.displayNoInventoryMessage();
                    audit
                     --  can do two things
                            1. let the user choose another item.
                            2. Fail - check

                }
                catch (notEnoughFund e) {
                    view.displayNotEnoughFundMessage();
                    audit
                     --  can do two things
                            1. let the user choose another item.
                            2. let the user add fund.
                            3. Fail - check
                }
                catch (auditFileException e){
                    audit file related action.
                }
            }
            // Program shuts down.
            service.saveData();
            view.displayGoodbyeMessage();
        }

        catch (VendingMachineDaoPersistenceException e){
            file was not able to persist.
        }

        */
    }
}
