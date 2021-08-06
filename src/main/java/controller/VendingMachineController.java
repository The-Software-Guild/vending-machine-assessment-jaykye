package controller;

import dao.VendingMachineAuditDao;
import service.VendingMachineService;
import ui.VendingMachineView;

public class VendingMachineController {
    VendingMachineService service;
    VendingMachineView view;

    public VendingMachineController() {
    }

    public VendingMachineController(VendingMachineService service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    public void run(){

    }
}
