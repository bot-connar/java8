package interview;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AQSDemo {

    public static void main(String[] args) {
        Lock lock=new ReentrantLock();
        lock.lock();
        lock.unlock();
        System.out.println(new AQSDemo().test());

    }

    public String test()
    {
        try {
            System.out.println("inner try");
            return "inner try";
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }finally {
            System.out.println("inner finally");
        }
        return "outer method";
    }

}
