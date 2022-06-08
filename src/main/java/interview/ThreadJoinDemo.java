package interview;

import java.util.concurrent.TimeUnit;

public class ThreadJoinDemo {
    private Thread t1;

    public static void main(String[] args) throws Throwable {
        Thread t1=new Thread(()->{
            System.out.println(Thread.currentThread().getName()+" is Over");
        },"t1");
        Thread t2=new Thread(()->{
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" is Over");
        },"t2");
        Thread t3=new Thread(()->{
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" is Over");
        },"t3");

        t3.start();
        t2.start();
        t1.start();
        new ThreadJoinDemo().finalize();


        TimeUnit.SECONDS.sleep(1);
        System.out.println("main is over");
        Thread.currentThread().join();
    }
}
