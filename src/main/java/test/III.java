package test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class III implements Runnable{
    private static final List<Shop> shops= Arrays.asList(
            new Shop("one"),
            new Shop("two"),
            new Shop("three"),
            new Shop("four"),
            new Shop("five"),
            new Shop("six"),
            new Shop("seven"),
            new Shop("eight"),
            new Shop("nine")
            /*new Shop("ten"),
            new Shop("eleven"),
            new Shop("twelve"),
            new Shop("thirteen"),
            new Shop("fourteen"),
            new Shop("fifteen"),
            new Shop("sixteen")*/
    );
    public static void main(String[] args) throws InterruptedException {
        long start=System.nanoTime();


        //这种是串行流，可以使用并行流处理
        /*List<String> collect = shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price: %.2f", shop.getName(), shop.getPrice(shop.getName()))))
                .map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(collect);*/

        /*List<CompletableFuture<String>> collect = shops.parallelStream().map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price: %.2f", shop.getName(), shop.getPrice(shop.getName()))))
                .collect(Collectors.toList());
        System.out.println(collect.stream().map(CompletableFuture::join).collect(Collectors.toList()));

        long end=System.nanoTime();
        System.out.println((end-start)/1000000);
        System.out.println("availableProcessors: "+Runtime.getRuntime().availableProcessors());*/

        Thread t = new Thread(new III());
        t.start();
        t.join(1000);
        System.out.println("Main thread is over!");

    }

    @Override
    public void run() {
        synchronized (Thread.currentThread()){
            IntStream.rangeClosed(1,5).forEach(o->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("doing . . . "+o);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }
}
