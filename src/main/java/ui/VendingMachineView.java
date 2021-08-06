package ui;

import java.math.BigDecimal;
import java.util.List;

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


    public String acceptCurrency() {
        return io.readString("Please insert cash amount. Separate dollar and cents by '.' ");
    }

}

