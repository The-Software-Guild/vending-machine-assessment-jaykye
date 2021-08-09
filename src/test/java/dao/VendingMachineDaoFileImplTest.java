package dao;

import static org.junit.jupiter.api.Assertions.*;

import dto.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

class VendingMachineDaoFileImplTest {
    VendingMachineDao testDao;

    @BeforeEach
    void setUp() {
        String testFile = "testitems.txt";
        testDao = new VendingMachineDaoFileImpl((testFile));
    }

    @Test
    void addAndGetItemTest(){
        // Arrange
        String name1 = "Test name1";
        BigDecimal price1 = new BigDecimal("12.35");
        int inventory1 = 5;
        Item testItem1 = new Item(name1, price1, inventory1);

        // Act
        testDao.addItem(name1, testItem1);
        Item retrievedItem = testDao.getItem(name1);

        // Assert
        assertEquals(retrievedItem.getName(), testItem1.getName());
        assertEquals(retrievedItem.getPrice(), testItem1.getPrice());
        assertEquals(retrievedItem.getInventory(), testItem1.getInventory());
        assertEquals(retrievedItem, testItem1);
    }

    @Test
    void getAllItems() {
        // Arrange
        String name1 = "Test name1";
        BigDecimal price1 = new BigDecimal("12.35");
        int inventory1 = 5;
        Item testItem1 = new Item(name1, price1, inventory1);

        String name2 = "Test name2";
        BigDecimal price2 = new BigDecimal("52.35");
        int inventory2 = 15;
        Item testItem2 = new Item(name2, price2, inventory2);
        testDao.addItem(name1, testItem1);
        testDao.addItem(name2, testItem2);

        // Act
        List allItems = testDao.getAllItems();

        // Assert
        assertNotNull(allItems, "Returned item cannot be null.");
        assertEquals(2, allItems.size(), "Size must be 2");
        assertTrue(allItems.contains(testItem1), "Must contain first item.");
        assertTrue(allItems.contains(testItem2), "Must contain second item.");
    }

    @Test
    void updateItemInventoryTest() {
// Arrange
        String name1 = "Test name1";
        BigDecimal price1 = new BigDecimal("12.35");
        int inventory1 = 5;
        Item testItem1 = new Item(name1, price1, inventory1);

        String name2 = "Test name2";
        BigDecimal price2 = new BigDecimal("52.35");
        int inventory2 = 15;
        Item testItem2 = new Item(name2, price2, inventory2);
        testDao.addItem(name1, testItem1);
        testDao.addItem(name2, testItem2);

        // Act
        Item removedItem = testDao.removeItem(name1);

        // Assert
        assertEquals(removedItem, testItem1, "Removed item must be the one requested.");
        assertEquals(removedItem, testItem1, "Removed item must be the one requested.");
    }

    @Test
    void removeItemTest(){
        // Arrange
        String name1 = "Test name1";
        BigDecimal price1 = new BigDecimal("12.35");
        int inventory1 = 5;
        Item testItem1 = new Item(name1, price1, inventory1);
        testDao.addItem(name1, testItem1);

        int newInventory = 123;

        // Act
        testDao.updateItemInventory(name1, newInventory);
        Item retrievedItem = testDao.getItem(name1);

        // Assert
        assertNotEquals(inventory1, retrievedItem.getInventory());
        assertEquals(retrievedItem.getInventory(), newInventory, "Must contain first item.");
    }




}