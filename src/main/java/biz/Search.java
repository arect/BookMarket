package biz;
import dao.GetData;
import entity.Book;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.sql.Date;

@Stateless(name = "Search")
public class Search implements SearchLocal {
    @PersistenceContext(unitName = "simpleJPA")
    private EntityManager m;
    public String search (Book b) {
        GetData gd = new GetData(m);
        return gd.GetAllBooks();
    }

    @Override
    public String search(Book b, Date start, Date end, double down, double up) {
        GetData gd = new GetData(m);
        return gd.GetBooks(b, start, end, down, up);
    }
}
