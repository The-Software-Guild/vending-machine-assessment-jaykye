package service;

public class VendingMachineInvalidPurchaceQuantityException extends Exception{
    public VendingMachineInvalidPurchaceQuantityException(String message) {
        super(message);
    }

    public VendingMachineInvalidPurchaceQuantityException(String message, Throwable cause) {
        super(message, cause);
    }
}
