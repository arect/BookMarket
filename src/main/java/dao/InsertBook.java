package dao;

import entity.Book;

import javax.persistence.EntityManager;

public class InsertBook {
    private EntityManager m;
    public InsertBook(EntityManager m_) {
        this.m = m_;
    }
    public boolean insertBook (Book b) {
        b.setId(b.getId().replace(" ", "").replace("-", ""));
        if (b.getId().length() != 13) {
            return false;
        }
        int a = 0;
        for (int i = 0; i < 12; i = i + 2) {
            a = a + b.getId().charAt(i) - '0';
        }
        for (int i = 1; i < 12; i = i + 2) {
            a = a + 3 * (b.getId().charAt(i) - '0');
        }
        a = 10 - a % 10;
        if (a != b.getId().charAt(12) - '0') {
            return false;
        }
        try {
            m.persist(b);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
