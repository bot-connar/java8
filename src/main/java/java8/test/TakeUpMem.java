package java8.test;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TakeUpMem {
    static List<TakeUp> list = new ArrayList<>();
    static int h_m=1024*1024;
    public static void main(String[] args) {
        int count = 1;
        Runtime rt=Runtime.getRuntime();
        long total=rt.totalMemory();
        long free=rt.freeMemory();
        long use=total-free;
        System.out.println("JVM已用的空间为："+use/h_m+" MB");
        System.out.println("JVM内存的空闲空间为："+free/h_m+" MB");
        System.out.println("JVM总内存空间为："+total/h_m+" MB");

        /*OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        long physicalFree=osmxb.getFreePhysicalMemorySize()/h_m;
        long physicalTotal=osmxb.getTotalPhysicalMemorySize()/h_m;
        long physicalUse=physicalTotal-physicalFree;
        System.out.println("getProcessCpuLoad"+osmxb.getProcessCpuLoad());
        System.out.println("getSystemCpuLoad"+osmxb.getSystemCpuLoad());
        System.out.println("系统物理内存空闲空间为："+physicalFree+" MB");
        System.out.println("系统物理内存已用空间为："+physicalUse+" MB");
        System.out.println("总物理内存：" + physicalTotal + " MB");*/
//        System.exit(0);
        while (true){
            showJVMHeapInfo();
            try {
                if (notEnough()){
                    TakeUp takeUp = new TakeUp();
                    takeUp.name = count+++"";
                    list.add(takeUp);
                }else {
                    if (list.size()>0) {
                        list.remove(0);
                        System.out.println("list.size() is "+list.size());
//                        System.gc();
                    }
                }
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static boolean notEnough(){
        MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        int h_m=1024*1024;
        return heapMemoryUsage.getUsed()/h_m<110;
    }


    static void showJVMHeapInfo(){
        MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        int h_m=1024*1024;
        System.out.println("jvm.heap.used is " + (heapMemoryUsage.getUsed())/h_m);
        System.out.println("jvm.heap.max is " + (heapMemoryUsage.getMax())/h_m);
        System.out.println();
    }


}
class TakeUp{
    int[] takeUp=new int[1024 * 1024];
    String name;
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println(this.name +" was disposed.");
    }
}
