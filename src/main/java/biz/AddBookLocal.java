package biz;
import entity.Book;

import javax.ejb.Local;
import java.util.ArrayList;

@Local
public interface AddBookLocal {
    public void add(Book b);
    public ArrayList<Integer> insert();
}
