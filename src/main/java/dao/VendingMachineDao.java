package dao;

import dto.Item;

import java.util.List;

public interface VendingMachineDao {

    /**
     * Adds item when new item is added by admin.
     * @param name name of the product to add.
     * @param item the item object of the product.
     * @return returns Item object of the product if the new item already exists in the system.
     */
    Item addItem(String name, Item item);

    /**
     * Removes item when admin wants to. -- only used by admin!!
     * @param name name of the product to remove.
     * @return The item object of the item removed.
     */
    Item removeItem(String name);

    /**
     * Get all the items in the in memory items data.
     * @return The list of items.
     */
    List<Item> getAllItems();

    /**
     * Get a specific item and display its name, price, inventory
     * @param name  the name of the item.
     * @return The item object associated with the name.
     */
    Item getItem(String name);

    /**
     * When selling an item, its inventory needs to be updated.
     * @param name the name of the item to be updated.
     * @param newInventory new value for the item inventory.
     */
    void updateItemInventory(String name, int newInventory);

    /**
     * Load data from source. -- depends on the implementation.
     */
    void loadData() throws VendingMachinePersistenceException;

    /**
     * Write data to source. -- depends on the implementation.
     */
    void writeData()  throws VendingMachinePersistenceException;
}
