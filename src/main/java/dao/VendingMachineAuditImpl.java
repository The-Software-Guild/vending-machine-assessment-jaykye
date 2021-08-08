package dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class VendingMachineAuditImpl implements VendingMachineAuditDao{
    public static final String AUDIT_FILE = "audit.txt";

    public VendingMachineAuditImpl() {
    }

    @Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not persist audit information.", e);
        }

        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }
}
