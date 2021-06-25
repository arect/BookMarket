package biz;

import dao.InsertOrders;
import entity.Orders;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AddCart implements AddCartLocal {
    @PersistenceContext(unitName = "simpleJPA")
    private EntityManager m;
    @Override
    public boolean add(String u, String i) {
        if (u == null || i == null) {
            return false;
        }
        Orders o = new Orders(i, u, false);
        InsertOrders io = new InsertOrders(m);
        return io.insertOrder(o);
    }
}
