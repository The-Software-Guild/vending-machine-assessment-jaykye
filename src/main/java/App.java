import controller.VendingMachineController;
import dao.VendingMachineAuditDao;
import dao.VendingMachineAuditImpl;
import dao.VendingMachineDao;
import dao.VendingMachineDaoFileImpl;
import service.VendingMachineService;
import ui.UserIO;
import ui.UserIOConsoleImpl;
import ui.VendingMachineView;

public class App {
    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        VendingMachineView view = new VendingMachineView(io);  // View needs access to io.
        VendingMachineAuditDao auditDao = new VendingMachineAuditImpl();
        VendingMachineDao dao = new VendingMachineDaoFileImpl();  // this will have hardcoded storage location.
        VendingMachineService service = new VendingMachineService(dao, auditDao); // Service needs dao access.
        VendingMachineController controller = new VendingMachineController(service, view); // Controller needs service access.

        controller.run();
    }
}
