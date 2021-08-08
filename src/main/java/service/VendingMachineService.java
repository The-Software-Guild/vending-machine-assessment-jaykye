package service;

import dao.VendingMachinePersistenceException;
import dto.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface VendingMachineService {

    // Pass through methods from dao.

    void loadData() throws VendingMachinePersistenceException;

    /**
     * Dumps items map into the dao source file.
     * @throws VendingMachinePersistenceException
     */
    void writeData() throws VendingMachinePersistenceException ;

    Item createItem(String name, Item item)  throws
            VendingMachineDuplicateItemException,
            VendingMachineValidationException;

    Item removeItem(String name);

    void updateInventory(String name,  int newInventory);

    Item getItem(String name);

    List getAllItems();

    BigDecimal processFunding(String moneyValue) throws VendingMachineInvalidCashValueException;

    Map calculateChangeToGive(BigDecimal remainingCash) throws VendingMachinePersistenceException;

    /**
     * Sells item, but only allow the quantity in the inventory.
     * This one has calls to internal validation functions.
     * @param name
     * @return
     */
    BigDecimal sellItem(String name, BigDecimal cashAmount) throws VendingMachineInsufficientFundException,
            VendingMachineNoInventoryException,
            VendingMachinePersistenceException;

}
