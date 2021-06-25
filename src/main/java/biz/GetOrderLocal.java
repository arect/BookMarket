package biz;

import javax.ejb.Local;

@Local
public interface GetOrderLocal {
    public String getOrders(String u, boolean a);
    public String getOrders();
}
