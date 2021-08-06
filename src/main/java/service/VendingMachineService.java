package service;

import dao.VendingMachineAuditDao;
import dao.VendingMachineDao;

public class VendingMachineService {
    VendingMachineDao dao;
    VendingMachineAuditDao auditDao;

    public VendingMachineService(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    public VendingMachineService() {
    }
}
