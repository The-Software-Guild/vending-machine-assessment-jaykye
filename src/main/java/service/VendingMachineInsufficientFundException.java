package service;

public class VendingMachineInsufficientFundException extends Exception {
    public VendingMachineInsufficientFundException(String message) {
        super(message);
    }

    public VendingMachineInsufficientFundException(String message, Throwable cause) {
        super(message, cause);
    }
}
