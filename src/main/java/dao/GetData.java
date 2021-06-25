package dao;

import entity.Book;
import entity.Orders;
import entity.User;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public class GetData {
    private EntityManager m;
    public GetData(EntityManager m_) {
        this.m = m_;
    }
    public String GetAllBooks() {
        Query q = m.createQuery("SELECT b FROM Book b");
        List<Book> list = (List<Book>) q.getResultList();
        StringBuilder result = new StringBuilder("[");
        if (list.size() > 0) {
            result.append(list.get(0));
            for (int i = 1; i < list.size(); i++) {
                result.append(",").append(list.get(i));
            }
        }
        result.append("]");
        return result.toString();
    }

    public String GetBooks(Book b, Date start, Date end, double down, double up) {
        String rule = "WHERE ";
        boolean hasRule = false;
        if (b.getId() != null && !b.getId().equals("")) {
            hasRule = true;
            rule = rule + "b.id = '" + b.getId() + "' ";
        }
        if (b.getTitle() != null && !b.getTitle().equals("")) {
            if (hasRule) {
                rule = rule + "AND ";
            }
            hasRule = true;
            rule = rule + "b.title = '" + b.getTitle() + "' ";
        }
        if (b.getAuthor() != null && !b.getAuthor().equals("")) {
            if (hasRule) {
                rule = rule + "AND ";
            }
            hasRule = true;
            rule = rule + "b.author = '" + b.getAuthor() + "' ";
        }
        if (b.getSort() != null && !b.getSort().equals("")) {
            if (hasRule) {
                rule = rule + "AND ";
            }
            hasRule = true;
            rule = rule + "b.sort = '" + b.getSort() + "' ";
        }
        if (start != null) {
            if (hasRule) {
                rule = rule + "AND ";
            }
            hasRule = true;
            rule = rule + "b.publish >= '" + start + "' ";
        }
        if (end != null) {
            if (hasRule) {
                rule = rule + "AND ";
            }
            hasRule = true;
            rule = rule + "b.publish <= '" + end + "' ";
        }
        if (0 <= down && down <= up) {
            if (hasRule) {
                rule = rule + "AND ";
            }
            hasRule = true;
            rule = rule + "b.price >= '" + down + "' ";
        }
        if (0 <= up && down <= up) {
            if (hasRule) {
                rule = rule + "AND ";
            }
            hasRule = true;
            rule = rule + "b.price <= '" + up + "' ";
        }
        if (hasRule) {
            rule = "SELECT b FROM Book b " + rule;
        }
        else {
            rule = "SELECT b FROM Book b ";
        }
        Query q = m.createQuery(rule);
        List<Book> list = (List<Book>) q.getResultList();
        StringBuilder result = new StringBuilder("[");
        if (list.size() > 0) {
            result.append(list.get(0));
            for (int i = 1; i < list.size(); i++) {
                result.append(",").append(list.get(i));
            }
        }
        result.append("]");
        return result.toString();
    }

    public String GetCarts(String u) {
        if (u == null) {
            return "[]";
        }
        Query qt = m.createNativeQuery("SELECT * FROM orders WHERE sort = false AND user_id = " + u);
        List<Map<String,Object>> list = qt.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        StringBuilder result = new StringBuilder("[");
        if (list.size() > 0) {
            Orders o = new Orders((String)list.get(0).get("BOOK_ID"), (String)list.get(0).get("USER_ID"), false);
            o.setOrder_date((String)list.get(0).get("ORDER_DATE"));
            o.setOrder_id((int)list.get(0).get("ORDER_ID"));
            o.setB(m.find(Book.class, o.getBook_id()));
            o.setU(m.find(User.class, o.getUser_id()));
            result.append(o);
            for (int i = 1; i < list.size(); i++) {
                Orders o_ = new Orders((String)list.get(i).get("BOOK_ID"), (String)list.get(i).get("USER_ID"), false);
                o_.setOrder_date((String)list.get(i).get("ORDER_DATE"));
                o_.setOrder_id((int)list.get(i).get("ORDER_ID"));
                o_.setB(m.find(Book.class, o_.getBook_id()));
                o_.setU(m.find(User.class, o_.getUser_id()));
                result.append(",").append(o_);
            }
        }
        result.append("]");
        return result.toString();
    }

    public String GetOrders(String u) {
        if (u == null) {
            return "[]";
        }
        Query qt = m.createNativeQuery("SELECT * FROM orders WHERE sort = true AND user_id = " + u);
        List<Map<String,Object>> list = qt.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        StringBuilder result = new StringBuilder("[");
        if (list.size() > 0) {
            Orders o = new Orders((String)list.get(0).get("BOOK_ID"), (String)list.get(0).get("USER_ID"), false);
            o.setOrder_date((String)list.get(0).get("ORDER_DATE"));
            o.setOrder_id((int)list.get(0).get("ORDER_ID"));
            o.setInfo((String) list.get(0).get("INFO"));
            o.setB(m.find(Book.class, o.getBook_id()));
            o.setU(m.find(User.class, o.getUser_id()));
            result.append(o);
            for (int i = 1; i < list.size(); i++) {
                Orders o_ = new Orders((String)list.get(i).get("BOOK_ID"), (String)list.get(i).get("USER_ID"), false);
                o_.setOrder_date((String)list.get(i).get("ORDER_DATE"));
                o_.setOrder_id((int)list.get(i).get("ORDER_ID"));
                o_.setInfo((String) list.get(i).get("INFO"));
                o_.setB(m.find(Book.class, o_.getBook_id()));
                o_.setU(m.find(User.class, o_.getUser_id()));
                result.append(",").append(o_);
            }
        }
        result.append("]");
        return result.toString();
    }

    public String GetAllOrders() {
        Query qt = m.createNativeQuery("SELECT * FROM orders WHERE sort = true");
        List<Map<String,Object>> list = qt.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        StringBuilder result = new StringBuilder("[");
        if (list.size() > 0) {
            Orders o = new Orders((String)list.get(0).get("BOOK_ID"), (String)list.get(0).get("USER_ID"), false);
            o.setOrder_date((String)list.get(0).get("ORDER_DATE"));
            o.setOrder_id((int)list.get(0).get("ORDER_ID"));
            o.setB(m.find(Book.class, o.getBook_id()));
            o.setU(m.find(User.class, o.getUser_id()));
            result.append(o);
            for (int i = 1; i < list.size(); i++) {
                Orders o_ = new Orders((String)list.get(i).get("BOOK_ID"), (String)list.get(i).get("USER_ID"), false);
                o_.setOrder_date((String)list.get(i).get("ORDER_DATE"));
                o_.setOrder_id((int)list.get(i).get("ORDER_ID"));
                o_.setB(m.find(Book.class, o_.getBook_id()));
                o_.setU(m.find(User.class, o_.getUser_id()));
                result.append(",").append(o_);
            }
        }
        result.append("]");
        return result.toString();
    }
}
