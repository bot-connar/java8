package exercise;

import java.lang.reflect.Proxy;

public class ProxyDemo {
    public static void main(String[] argss) {
        Vehicle car = new Car();
        Vehicle vehicle = (Vehicle) Proxy.newProxyInstance(
                car.getClass().getClassLoader(),
                Car.class.getInterfaces(),
                (proxy, method, args) -> {
                    String name = method.getName();
                    if (name.equalsIgnoreCase("run")) {
                        System.out.println("---------before-------");
                        Object invoke = method.invoke(car, args);
                        System.out.println("---------after-------");
                        return invoke;
                    }
                    return method.invoke(car, args);
                });
        vehicle.run();
        System.out.println();
        vehicle.shout();
    }
}

interface Vehicle{
    void run();
    void shout();
}

class Car implements Vehicle{
    @Override
    public void run() {
        System.out.println("Car is running!");
    }

    @Override
    public void shout() {
        System.out.println("Car is shouting!");
    }
}


