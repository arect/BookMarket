package entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Administrator implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String password;

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
