package biz;
import entity.Administrator;
import entity.User;

import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless(name = "Login")
public class Login implements LoginLocal {
    @PersistenceContext(unitName = "simpleJPA")
    private EntityManager m;
    @Override
    public Boolean login(String u, String p) {
        return identity(u, p) != 0;
    }

    @Override
    public int identity(String u, String p) {
        if (u == null) {
            return 0;
        }
        User temp = m.find(User.class, u);
        if (temp != null) {
            if (temp.getPassword().equals(p)) {
                return 1;
            }
            return 0;
        }
        else {
            Administrator a = m.find(Administrator.class, u);
            if (a != null) {
                if (a.getPassword().equals(p)) {
                    return 2;
                }
                return 0;
            }
        }
        return 0;
    }

    @Override
    public int identity(String u) {
        if (u == null) {
            return 0;
        }
        User temp = m.find(User.class, u);
        if (temp != null) {
            return 1;
        }
        else {
            Administrator a = m.find(Administrator.class, u);
            if (a != null) {
                return 2;
            }
        }
        return 0;
    }
}
