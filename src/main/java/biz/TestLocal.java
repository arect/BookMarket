package biz;
import javax.ejb.Local;

@Local
public interface TestLocal {
    public void test(int uid, String bid);
}
