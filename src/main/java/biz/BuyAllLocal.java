package biz;

import javax.ejb.Local;

@Local
public interface BuyAllLocal {
    public boolean buy(String u, String in);
}
