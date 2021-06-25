package biz;

import dao.InsertBook;
import entity.Book;

import java.util.ArrayList;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
public class AddBook implements AddBookLocal {
    @PersistenceContext(unitName = "simpleJPA")
    private EntityManager m;
    ArrayList<Book> myBooks = new ArrayList<>();
    @Override
    public void add(Book b) {
        myBooks.add(b);
    }

    @Override
    public ArrayList<Integer> insert() {
        ArrayList<Integer> ia = new ArrayList<>();
        InsertBook ib = new InsertBook(m);
        for (int i = 0; i < myBooks.size(); i++) {
            if (!ib.insertBook(myBooks.get(i))) {
                ia.add(i);
            }
        }
        myBooks.clear();
        return ia;
    }
}
