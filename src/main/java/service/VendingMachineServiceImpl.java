package service;

import dao.VendingMachineAuditDao;
import dao.VendingMachineDao;
import dao.VendingMachinePersistenceException;
import dto.Changes;
import dto.Item;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachineServiceImpl implements VendingMachineService {
    VendingMachineDao dao;
    VendingMachineAuditDao auditDao;
    BigDecimal remainingCash = new BigDecimal("0");

    public VendingMachineServiceImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    public VendingMachineServiceImpl() {
    }

    // Pass through methods from dao.
    @Override
    public void loadData() throws VendingMachinePersistenceException {
        dao.loadData();
    }

    @Override
    public void writeData() throws VendingMachinePersistenceException {
        dao.writeData();
    }

    @Override
    public Item createItem(String name, Item item) throws
            VendingMachineDuplicateItemException,
            VendingMachineValidationException{

        checkIfAlreadyExist(name);
        validateItem(item);

        return dao.addItem(name, item);
    }

    @Override
    public Item removeItem(String name){
        return dao.removeItem(name);
    }

    @Override
    public void updateInventory(String name, int newInventory){
        dao.updateItemInventory(name, newInventory);
    }

    @Override
    public Item getItem(String name) {
        return dao.getItem(name);
    }

    @Override
    public List getAllItems() {
        return dao.getAllItems();
    }

    @Override
    public BigDecimal processFunding(String moneyValue) throws VendingMachineInvalidCashValueException {
        String[] tokens = moneyValue.split(".");
        if (tokens[1].length() > 2){
            throw new VendingMachineInvalidCashValueException("Cash value cannot have more than 2 decimals.");
        }
        try {
            Integer.parseInt(tokens[0]);
            Integer.parseInt(tokens[1]);
        }
        catch (NumberFormatException e){
            throw new VendingMachineInvalidCashValueException("Invalid cash value", e);
        }
        return new BigDecimal(moneyValue);
    }

    @Override
    public Map calculateChangeToGive(BigDecimal remainingCash) throws
            VendingMachinePersistenceException {
        Map<Changes, Integer> changeMap = new HashMap<>();
        for (Changes c : Changes.getAllCoins()){
            changeMap.put(c, 0);
        }

        for (Changes change: Changes.getAllCoins()){
            while (remainingCash.compareTo(change.getValue()) >= 0){
                changeMap.put(change, changeMap.get(change)+1);
                remainingCash = remainingCash.subtract(change.getValue());
            }
        }

        auditDao.writeAuditEntry("Changes gave: ");

        return changeMap;
    }

    @Override
    public BigDecimal sellItem(String name, BigDecimal cashAmount) throws
            VendingMachineInsufficientFundException,
            VendingMachineNoInventoryException,
            VendingMachinePersistenceException {

        Item currItem = dao.getItem(name);
        int currInventory = currItem.getInventory();
        checkInventory(currItem);
        checkFund(currItem, cashAmount);

        // Now passed.
        dao.updateItemInventory(name, currInventory-1);
        auditDao.writeAuditEntry("Sold item: " + name);
        BigDecimal cashBalance = cashAmount.subtract(currItem.getPrice());
        return cashBalance;
    }

    private void checkInventory(Item item) throws
            VendingMachineNoInventoryException {
        if (item.getInventory() <=0) {
            throw new VendingMachineNoInventoryException("Not enough inventory.");
        }
    }

    private void checkFund(Item item, BigDecimal cashAmount) throws
            VendingMachineInsufficientFundException {
        if (cashAmount.compareTo(item.getPrice()) < 0){
            throw new VendingMachineInsufficientFundException("Insufficient fund.");
        }
    }

    // This is for Admin menu.
    private void validateItem(Item item) throws VendingMachineValidationException {
        // Some rules for item object.

        if (item.getName() == null // can't be triggered because item constructor mandates all values
                || item.getPrice() == null
                || item.getName().trim().length() == 0) {
            throw new VendingMachineValidationException("All fields are required.");
        }
        if (item.getPrice().compareTo(new BigDecimal("0")) <= 0 ) {
            throw new VendingMachineValidationException("Price cannot be 0 or less.");
        }
        if (item.getInventory() < 0) {
            throw new VendingMachineValidationException("Inventory cannot be negative.");
        }
    }

    private void checkIfAlreadyExist(String name) throws VendingMachineDuplicateItemException{
        if (dao.getItem(name) != null){
            throw new VendingMachineDuplicateItemException("Item already exits. Cannot add duplicate.");
        }
    }

    public void writeAuditEntry(String message) throws
            VendingMachinePersistenceException{
    }


}
