import controller.VendingMachineController;
import dao.VendingMachineAuditDao;
import dao.VendingMachineAuditImpl;
import dao.VendingMachineDao;
import dao.VendingMachineDaoFileImpl;
import service.VendingMachineServiceImpl;
import ui.UserIO;
import ui.UserIOConsoleImpl;
import ui.VendingMachineView;

public class App {
    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        VendingMachineView view = new VendingMachineView(io);
        VendingMachineAuditDao auditDao = new VendingMachineAuditImpl();
        // Dao has hardcoded storage location.
        VendingMachineDao dao = new VendingMachineDaoFileImpl();
        VendingMachineServiceImpl service = new VendingMachineServiceImpl(dao, auditDao);
        // Controller needs service access.
        VendingMachineController controller = new VendingMachineController(service, view);

        controller.run();
    }
}
