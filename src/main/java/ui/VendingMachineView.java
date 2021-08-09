package ui;

import dto.Changes;
import dto.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class VendingMachineView {
    private UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }


    /**
     * Print out all the items, prices and the inventory.
     * @return
     */
    public int printItemAndGetSelection() {
        io.print("Select one from the following items:");

        return io.readInt("Please select from the above choices.", 1, 5);
    }


    public void displayGreetingMessage() {
        io.print("Welcome to my Vending Machine.");
    }

    public void displayItems(List<Item> items) {
        io.print("======= Items ========");
        int i = 1;
        for (Item item: items){
            io.print(i++ + ". Name: " + item.getName()
                    + " | Price: " + item.getPrice()
                    + " | Inventory: " + item.getInventory()
            );
        }
        io.print(i + ". Exit");
    }

    public String displayMessageAndGetFund(){
        return io.readString("Please insert fund. Separate dollar and cents by '.' ");
    }

    public int getItemSelection(List allItemList){
        return io.readInt("Enter the item number.", 1, allItemList.size()+1);
    }

    public void displaySuccessfulPurchaseMessage(){
        io.print("Your purchase was successful.");
        io.print("");
    }

    public void displayReturnChanges(Map<Changes, Integer> changesToGive){
        int numOfCoins;

        io.print("Here are your changes:");
        for (Changes coin: changesToGive.keySet()){
            numOfCoins = changesToGive.get(coin);
            if (numOfCoins > 0) {
                io.print(coin.name() + " : " + numOfCoins);
            }
        }
    }

    public void displayWrongFund(){
        io.print("Enter valid number for funding.");
    }

    public void displayNoInventoryMessage(){
        io.print("Not enough inventory for the item.");
    }

    public void displayNotEnoughFundMessage(){
        io.print("Insufficient fund.");
    }

    public void displayGoodbyeMessage() {
        io.print("Thank you and goodbye.");
    }

    public void displayDataErrorMessage() {
        io.print("Data Not loaded successfully.");
    }

    public int getNoInventoryAction(){
        io.print("1. Choose another item.");
        io.print("2. Exit");
        return io.readInt("Select what action to take.", 1,2);
    }

    public int getInsufficientFundAction(){
        io.print("1. Choose another item.");
        io.print("2. Add more fund.");
        io.print("3. Exit");
        return io.readInt("Select what action to take.", 1,3);
    }

    public void displayRemainingFund(BigDecimal balance){
        io.print("Remaining Balance: $" +  balance);
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}

