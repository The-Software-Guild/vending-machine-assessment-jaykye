package service;

public class VendingMachineInvalidCashValueException extends Exception{
    public VendingMachineInvalidCashValueException(String message) {
        super(message);
    }

    public VendingMachineInvalidCashValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
