package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;

@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String name;
    private String mail;
    private String password;
    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "u")
    private Collection<Orders> orders = new ArrayList<>();

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getMail() {
        return mail;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setOrders(Collection<Orders> o) {
        this.orders = o;
    }
    public Collection<Orders> getOrders() {
        return this.orders;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
