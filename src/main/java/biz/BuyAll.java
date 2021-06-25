package biz;

import dao.InsertOrders;
import entity.Book;
import entity.Orders;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Stateless
public class BuyAll implements BuyAllLocal {
    @PersistenceContext(unitName = "simpleJPA")
    private EntityManager m;
    @Override
    public boolean buy(String u, String in) {
        boolean buyAny = false;
        try {
            Query qt = m.createNativeQuery("SELECT ORDER_ID, BOOK_ID FROM ORDERS WHERE SORT = false AND USER_ID = " + u);
            List<Map<String,Object>> list = qt.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
            for (Map<String, Object> stringObjectMap : list) {
                String bookid = (String) stringObjectMap.get("BOOK_ID");
                Book book = m.find(Book.class, bookid);
                if (book.getAmount() > 0) {
                    buyAny = true;
                    book.setAmount(book.getAmount() - 1);
                    m.merge(book);
                    m.createQuery("UPDATE Orders o SET o.sort = true WHERE o.order_id = " + stringObjectMap.get("ORDER_ID")).executeUpdate();
                    m.createQuery("UPDATE Orders o SET o.info = '" + in + "' WHERE o.order_id = " + stringObjectMap.get("ORDER_ID")).executeUpdate();
                }
            }
        } catch (Exception e) {
            return false;
        }
        return buyAny;
    }
}
