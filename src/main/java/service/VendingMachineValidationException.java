package service;

public class VendingMachineValidationException extends Exception{
    public VendingMachineValidationException(String message) {
        super(message);
    }

    public VendingMachineValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
