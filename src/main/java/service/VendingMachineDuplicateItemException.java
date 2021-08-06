package service;

public class VendingMachineDuplicateItemException extends Exception{
    public VendingMachineDuplicateItemException(String message) {
        super(message);
    }

    public VendingMachineDuplicateItemException(String message, Throwable cause) {
        super(message, cause);
    }
}
