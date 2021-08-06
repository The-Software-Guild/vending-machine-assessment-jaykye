package ui;

import java.util.List;

public class VendingMachineView {
    private UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public void printGreetingMessage(){
        io.print("Welcome!");
    }

    public int printMenuAndGetSelection() {
        io.print("Select one from the following items:");
        io.print("1. List Students");
        io.print("2. Create New Student");
        io.print("3. View a Student");
        io.print("4. Remove a Student");
        io.print("5. Exit");
        return io.readInt("Please select from the above choices.", 1, 5);
    }


}

