package biz;
import entity.Book;
import entity.Orders;
import entity.User;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.sql.Date;

@Stateless(name = "Test")
public class Test implements TestLocal {
    @PersistenceContext(unitName = "simpleJPA")
    private EntityManager m;
    @Override
    public void test(int uid, String bid) {
        User u = new User();
        u.setId("11111111111");
        u.setName("123");
        u.setMail("mail@example.com");
        u.setPassword("123456");

        Book b = new Book();
        b.setId(bid);
        b.setAuthor("None");
        b.setAmount(10);
        b.setPrice(100.5F);
        b.setPublish(Date.valueOf("2021-06-08"));

        //m.persist(u);
        //m.persist(b);

        Orders o = new Orders(b, u, true);
        m.persist(o);
    }
}
