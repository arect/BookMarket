package biz;
import entity.User;

import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless(name = "Sign")
public class Sign implements SignLocal {
    @PersistenceContext(unitName = "simpleJPA")
    private EntityManager m;
    @Override
    public int sign(User u) {
        try {
            if (u.getId().equals("")) {
                return -21;
            }
            else if (u.getPassword().equals("")) {
                return -22;
            }
            User temp = m.find(User.class, u.getId());
            if (temp != null) {
                return -1;
            }
            m.persist(u);
        } catch (PersistenceException e) {
            return -2;
        }
        return 0;
    }
}
