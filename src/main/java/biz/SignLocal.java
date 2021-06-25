package biz;
import entity.User;

import javax.ejb.Local;

@Local
public interface SignLocal {
    public int sign (User u);
}
