package biz;

import javax.ejb.Local;

@Local
public interface AddCartLocal {
    public boolean add(String u, String i);
}
