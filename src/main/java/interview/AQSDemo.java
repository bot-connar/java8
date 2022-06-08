package interview;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AQSDemo {

    public static void main(String[] args) throws InterruptedException {
        new HashMap<String, String>().put("1", "1");
        AtomicInteger atomicInteger = new AtomicInteger(4);
        atomicInteger.addAndGet(1);
        CountDownLatch latch = new CountDownLatch(1);
        Semaphore semaphore = new Semaphore(1);
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(1);
        Lock lock=new ReentrantLock();


        Runnable r2 = () -> {
            // 中断当前线程
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+" is running!");
                TimeUnit.MINUTES.sleep(2);
                System.out.println(Thread.currentThread().getName()+" is over!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        };

        new Thread(r2,"threadA").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(r2,"threadB").start();


//        new Thread(r2,"threadC").start();

    }

}
