package interview;

public class StringPool58Demo {
    public static void main(String[] args) {
        String str1=new StringBuilder("58").append("tongcheng").toString();
        System.out.println(str1);
        System.out.println(str1.intern());
        System.out.println(str1==str1.intern());

        System.out.println("_____________");

        String str2=new StringBuilder("ja").append("va").toString();
        System.out.println(str2);
        System.out.println(str2.intern());
        //intern方法会首先在字符串常量池中找java字符串，java字符串已经在JVM初始化的时候创建好了
        //intern()方法会返回常量池中的java的引用，肯定和现在创建的这个不相同
        System.out.println(str2==str2.intern());
    }
}
