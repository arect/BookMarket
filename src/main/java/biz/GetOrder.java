package biz;

import dao.GetData;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class GetOrder implements GetOrderLocal {
    @PersistenceContext(unitName = "simpleJPA")
    private EntityManager m;
    @Override
    public String getOrders(String u, boolean a) {
        if (u == null) {
            return "[]";
        }
        GetData gd = new GetData(m);
        if (a) {
            return gd.GetOrders(u);
        }
        else {
            return gd.GetCarts(u);
        }
    }
    @Override
    public String getOrders() {
        GetData gd = new GetData(m);
        return gd.GetAllOrders();
    }
}
