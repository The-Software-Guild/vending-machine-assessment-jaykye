package service;

import dao.VendingMachineAuditDao;
import dao.VendingMachineAuditImpl;
import dao.VendingMachineDao;
import dao.VendingMachineDaoFileImpl;
import dto.Changes;
import dto.Item;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineServiceImplTest {
    VendingMachineAuditDao auditDao = new VendingMachineAuditImpl();
    VendingMachineDao dao = new VendingMachineDaoFileImpl();  // this will have hardcoded storage location.
    VendingMachineServiceImpl service = new VendingMachineServiceImpl(dao, auditDao); // Service needs dao access.

    @Test
    void calculateChangeToGiveTest() {
        Changes[] changeArray = Changes.getAllCoins();

        Map<Changes, Integer> expectedMap = new HashMap<>();
        for (Changes c : changeArray){
            expectedMap.put(c, 0);
        }

        // Arrange
        BigDecimal remainingCash = new BigDecimal("0.44");
        expectedMap.put(Changes.QUARTER, 1);
        expectedMap.put(Changes.DIME, 1);
        expectedMap.put(Changes.NICKEL, 1);
        expectedMap.put(Changes.CENT, 4);

        // Act
        Map answer = null;
        answer = service.calculateChangeToGive(remainingCash);

        // Assert
        assertEquals(expectedMap, answer);
    }

    @Test
    void sellItemTest(){
        // Arrange
        String name1 = "Test name1";
        BigDecimal price1 = new BigDecimal("12.35");
        int inventory1 = 5;
        Item testItem1 = new Item(name1, price1, inventory1);
        dao.addItem(name1, testItem1);
    }
}