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

    public void printGreetingMessage(){
        io.print("Welcome!");
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
        for (Item item: items){
            io.print("Name: " + item.getName()
                    + " Price: " + item.getPrice()
                    + " Inventory: " + item.getInventory()
            );
            io.print("");
        }
    }

    public String displayMessageAndGetFund(){
        return io.readString("Please insert cash amount. Separate dollar and cents by '.' ");
    }

    public String getItemSelection(){
        return io.readString("Enter the item name.");
    }

    public void displaySuccessfulPurchaseMessage(){
        io.print("Your purchase was successful.");

    }

    public void displayReturnChanges(Map<Changes, Integer> changesToGive){
        int numOfCoins;

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
}

