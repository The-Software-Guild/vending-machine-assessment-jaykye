package dao;

public interface VendingMachineAuditDao {
    /**
     * If anything is changed on VendingMachineDao, the change is logged via this method.
     * @param entry: Text I want to add to the log file.
     */
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException;
}
