package biz;
import entity.Book;

import java.sql.Date;
import javax.ejb.Local;

@Local
public interface SearchLocal {
    public String search (Book b);
    public String search (Book b, Date start, Date end, double down, double up);
}
