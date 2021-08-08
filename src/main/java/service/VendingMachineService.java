package service;

import dao.VendingMachinePersistenceException;
import dto.Item;

import java.math.BigDecimal;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;

public interface VendingMachineService {

    // Pass through methods from dao.

    void loadData() throws VendingMachinePersistenceException;

    void writeData() throws VendingMachinePersistenceException ;

    Item createItem(String name, Item item)  throws
            VendingMachineDuplicateItemException,
            VendingMachineValidationException;

    Item removeItem(String name);

    void updateInventory(String name,  int newInventory);

    Item getItem(String name);

    List getAllItems();

    BigDecimal acceptCurrency(String moneyValue) throws VendingMachineInvalidCashValueException;

    Map calculateChangeToGive(BigDecimal remainingCash);

    /**
     * Sells item, but only allow the quantity in the inventory.
     * @param name
     */
    void sellItem(String name);
}
