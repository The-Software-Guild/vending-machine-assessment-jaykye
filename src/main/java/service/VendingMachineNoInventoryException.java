package service;

public class VendingMachineNoInventoryException extends Exception{
    public VendingMachineNoInventoryException(String message) {
        super(message);
    }

    public VendingMachineNoInventoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
