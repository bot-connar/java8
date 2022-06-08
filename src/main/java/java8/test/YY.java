package java8.test;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

class CompletableFutureDemo{
    public static void main(String[] args) {

        /*ExecutorService executor= Executors.newCachedThreadPool();
        Future<Double> future = executor.submit(YY::doSomeComputation);
        doSomethingElse();
        try{
            Double res = future.get(1, TimeUnit.SECONDS);
            System.out.println("the price is "+res);
            executor.shutdown();
        }catch (InterruptedException | TimeoutException e){
            System.out.println("compute timeout!");
        } catch (ExecutionException e) {
            System.out.println("current thread interrupted !");
        }*/


        /*try {
            System.out.println("begin");
            Shop shop=new Shop();
            Future<Double> price = shop.getPriceAsync2("ss");

            System.out.println("doSomething else!");
            System.out.println(price.get(1,TimeUnit.SECONDS));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            System.out.println("出错！");
            System.out.println(e);
        }
        System.out.println("end");*/


        List<Shop> shops= Arrays.asList(
                new Shop("one"),
                new Shop("two"),
                new Shop("three"),
                new Shop("four"),
                new Shop("five"),
                new Shop("six"),
                new Shop("seven"),
                new Shop("eight"),
                new Shop("nine"),
                new Shop("ten"),
                new Shop("eleven"),
                new Shop("twelve"),
                new Shop("thirteen"),
                new Shop("fourteen"),
                new Shop("fifteen"),
                new Shop("sixteen")
        );
        long start=System.nanoTime();
        //List<String> strings=shops.stream().map(shop -> String.format("%s price: %.2f", shop.getName(), shop.getPrice(shop.getName()))).collect(Collectors.toList());
        //List<String> strings=shops.parallelStream().map(shop -> String.format("%s price: %.2f", shop.getName(), shop.getPrice(shop.getName()))).collect(Collectors.toList());
        /*List<CompletableFuture<String>> str2 = shops.stream().map(shop->
                CompletableFuture.supplyAsync(
                        ()->String.format("%s price: %.2f", shop.getName(), shop.getPrice(shop.getName())))).collect(Collectors.toList());
        System.out.println(str2.stream().map(CompletableFuture::join).collect(Collectors.toList()));*/




        System.out.println("availableProcessors: "+Runtime.getRuntime().availableProcessors());



        final Executor executor=Executors.newFixedThreadPool(Math.min(shops.size(),100),(r)->{
            Thread t=new Thread(r);
            t.setDaemon(true);
            return t;
        });
        /*
        List<CompletableFuture<String>> futures = shops.stream().map(shop ->
                CompletableFuture.supplyAsync(() ->
                        String.format("%s price: %.2f", shop.getName(), shop.getPrice(shop.getName())),executor))
                .collect(Collectors.toList());

        System.out.println(futures.stream().map(CompletableFuture::join).collect(Collectors.toList()));*/



        /*List<String> strings=shops.stream()
                .map(shop -> shop.getPrice2("hhhh"))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
        System.out.println(strings);*/


        /*List<CompletableFuture<String>> hhhh = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice2("hhhh"), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(
                        quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)
                ))
                .collect(Collectors.toList());
        System.out.println(hhhh.stream().map(CompletableFuture::join).collect(Collectors.toList()));*/


        /*CompletableFuture[] hhhhs = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice2("hhhh"), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(
                        quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)
                                .thenCombine(CompletableFuture.supplyAsync(Bank::getRate), (final_price, rate) -> final_price + ", rate :" + rate)
                )).map(f -> f.thenAccept(System.out::println))
                .toArray(CompletableFuture[]::new);
        //System.out.println(hhhh.stream().map(CompletableFuture::join).collect(Collectors.toList()));
        CompletableFuture.allOf(hhhhs).join();*/


        List<CompletableFuture<Void>> hhhh = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice2("hhhh"), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(
                        quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)
                                .thenCombine(CompletableFuture.supplyAsync(Bank::getRate), (final_price, rate) -> final_price + ", rate :" + rate)
                ).thenAccept(System.out::println)).collect(Collectors.toList());

        long end=System.nanoTime();
        System.out.println((end-start)/1000000);

    }

    public static double doSomeComputation() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return 3+4.5;
    }

    public static void doSomethingElse() {
        System.out.println("do something else!");
    }


}
@AllArgsConstructor @Data
class Shop{
    private final String name;


    public Future<Double> getPriceAsync(String product){
        CompletableFuture<Double> futurePrice=new CompletableFuture<>();
        new Thread(()->futurePrice.complete(getPrice(product))).start();
        return futurePrice;
    }

    public Future<Double> getPriceAsync2(String product){
        return CompletableFuture.supplyAsync(()->getPrice(product));
    }

    public double getPrice(String product) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("current thread interrupted !");
        }
        return new Random().nextDouble()*product.charAt(0)+product.charAt(1);
    }

    public String getPrice2(String product) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("current thread interrupted !");
        }
        Double price = new Random().nextDouble()* product.charAt(0) + product.charAt(1);
        Discount.Code code= Discount.Code.values()[new Random().nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s",name,price,code);
    }
}

class Discount{
    @AllArgsConstructor
    enum Code{
        NONE(0),
        SILVER(5),
        GOLD(10),
        PLATINUM(15),
        DIAMOND(20);
        private final int value;
    }

    public static double apply(double price,Code code){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("current thread interrupted !");
        }
        return price * (100 - code.value) / 100;
    }

    public static String applyDiscount(Quote quote){
        return quote.getShopName() + "price :" + Discount.apply(quote.getPrice() ,quote.getCode());
    }
}
@AllArgsConstructor @Data
class Quote{
    private final String shopName;
    private final double price;
    private final Discount.Code code;

    public static Quote parse(String s){
        String[] strings=s.split(":");
        return new Quote(strings[0],Double.parseDouble(strings[1]),Discount.Code.valueOf(strings[2]));
    }
}

class Bank{
    public static double getRate(){
        return new Random().nextDouble();
    }
    public static double getPriceFromBank(double price,double rate){
        return price*rate;
    }
}