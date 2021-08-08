package service;

import controller.VendingMachineController;
import dao.VendingMachineAuditDao;
import dao.VendingMachineAuditImpl;
import dao.VendingMachineDao;
import dao.VendingMachineDaoFileImpl;
import dto.Changes;
import org.junit.jupiter.api.Test;
import ui.UserIO;
import ui.UserIOConsoleImpl;
import ui.VendingMachineView;

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
        Map answer = service.calculateChangeToGive(remainingCash);

        // Assert
        assertEquals(expectedMap, answer);
    }
}