package controller;

import service.VendingMachineServiceImpl;
import ui.VendingMachineView;

public class VendingMachineController {
    VendingMachineServiceImpl service;
    VendingMachineView view;

    public VendingMachineController() {
    }

    public VendingMachineController(VendingMachineServiceImpl service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    public void run(){

    }
}
