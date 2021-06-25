package biz;
import javax.ejb.Local;

@Local
public interface LoginLocal {
    public Boolean login(String u, String p);
    public int identity(String u, String p);
    public int identity(String u);
}
