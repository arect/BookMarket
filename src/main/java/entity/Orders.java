package entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@IdClass(OrdersPK.class)
@Table(name="ORDERS")
public class Orders {
    private static final long serialVersionUID = 1L;
    @ManyToOne(cascade = { CascadeType.ALL }, optional = false)
    @JoinColumn(name = "BOOK", referencedColumnName = "ID", nullable = false, updatable = false, insertable = false)
    private Book b;
    @ManyToOne(cascade = { CascadeType.ALL }, optional = false)
    @JoinColumn(name = "USER", referencedColumnName = "ID", nullable = false, updatable = false, insertable = false)
    private User u;
    @Id
    private int order_id;
    private String book_id;
    private String user_id;
    private boolean sort; // true 订单 false 购物车

    private String order_date;
    private String info = "";

    public Orders(Book _b, User _u) {
        b = _b;
        u = _u;
        book_id = _b.getId();
        user_id = _u.getId();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        order_date = formatter.format(date);
    }

    public Orders(Book _b, User _u, boolean _s) {
        b = _b;
        u = _u;
        sort = _s;
        book_id = _b.getId();
        user_id = _u.getId();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        order_date = formatter.format(date);
    }

    public Orders(String _b, String _u, boolean _s) {
        book_id = _b;
        user_id = _u;
        sort = _s;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        order_date = formatter.format(date);
    }

    public Orders() {};

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }
    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }
    public void setSort(boolean sort) {
        this.sort = sort;
    }
    public void setB(Book b) {
        this.b = b;
    }
    public void setU(User u) {
        this.u = u;
    }
    public void setInfo(String info) {
        this.info = info;
    }

    public int getOrder_id() {
        return order_id;
    }
    public String getUser_id() {
        return user_id;
    }
    public String getBook_id() {
        return book_id;
    }
    public String getOrder_date() {
        return order_date;
    }
    public boolean isSort() {
        return sort;
    }
    public String getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "{" +
                "\"bookname\":\""   + b.getTitle()  + "\"," +
                "\"isbn\":\""       + b.getId()     + "\"," +
                "\"price\":\""      + b.getPrice()  + "\"," +
                "\"author\":\""     + b.getAuthor() + "\"," +
                "\"sort\":\""       + b.getSort()   + "\"," +
                "\"order_id\":\""   + order_id      + "\"," +
                "\"order_date\":\"" + order_date    + "\"," +
                "\"user_id\":\""    + u.getName()   + "\"," +
                "\"info\":\""       + info          + "\"" +
                "}";
    }
}

