package exercise;

public class ShiftOperation {
    public static void main(String[] args) {
        System.out.println(1<<4);
        System.out.println(-1<<4);
        //带符号右移
        System.out.println(128>>4);
        System.out.println(-128>>4);

        //无符号右移
        System.out.println(-128>>>4);

        System.out.println(1&2);
        System.out.println(1^3);
        System.out.println(1|3);
        System.out.println(4);

        int n=17;
        n|=n>>>1;
        n|=n>>>2;
        n|=n>>>4;
        n|=n>>>8;
        n|=n>>>16;
        //n变为大于n的2的最小次幂数
        System.out.println(n);
    }
}
