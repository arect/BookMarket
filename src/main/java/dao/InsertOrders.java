package dao;

import entity.Orders;

import javax.persistence.EntityManager;

public class InsertOrders {
    private EntityManager m;
    public InsertOrders(EntityManager m_) {
        this.m = m_;
    }
    public boolean insertOrder(Orders o) {
        try {
            m.persist(o);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
