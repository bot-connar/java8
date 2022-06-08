package interview;

public class TryCatchFinallyReturnsDemo {
    public static void main(String[] args) {
        System.out.println(new TryCatchFinallyReturnsDemo().test(0));
    }
    public String test(int a)
    {
        try {
            System.out.println("inner try");
            if (a==0){
                throw new Exception("errorMessage!");
            }
            return "inner try";//正常返回的值可能会被抛弃，要看后面有没有返回
        }catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("inner catch");
            return "inner catch";
        }finally {
            System.out.println("inner finally");//不管什么时候都会执行
            return "inner finally";//finally里面有返回就返回finally里面的。//
        }
//        return "outer finally";//只要前面有返回，这里就会被抛弃
    }

}
