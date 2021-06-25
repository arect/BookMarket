package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;

// 图书名称、图书书号、作者、出版时间、价格、类别、数量。

@Entity
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String title;
    private String author;
    private Date publish;
    private float price;
    private String sort;
    private int amount;
    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "b")
    private Collection<Orders> orders = new ArrayList<>();

    public Book() {
    }

    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        id = id.replace(" ", "").replace("-", "");
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    public Date getPublish() {
        return publish;
    }
    public void setPublish(Date publish) {
        this.publish = publish;
    }

    public String getSort() {
        return sort;
    }
    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<Orders> getOrders() {
        return this.orders;
    }
    public void setOrders(Collection<Orders> o) {
        this.orders = o;
    }

    @Override
    public String toString() {
        return "{" +
                "\"isbn\":\"" + id + '\"' +
                ",\"bookname\":\"" + title + '\"' +
                ",\"author\":\"" + author + '\"' +
                ",\"date\": \"" + publish + "\"" +
                ",\"price\":" + price +
                ",\"sort\":\"" + sort + '\"' +
                ",\"amount\":" + amount +
                '}';
    }
}