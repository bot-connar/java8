package interview;

public class InstructionsRearrangement {
    private static int x=0,y=0,a=0,b=0;
    public static void main(String[] args) throws InterruptedException {
        /*int a = 1;
        int b = 2;
        try {
            a = 3;           //A
            b = 1 / 0;       //B
        } catch (Exception e) {
            System.out.println("error");
        } finally {
            System.out.println("a = " + a);
        }*/

        for (int i = 0;; i++) {
            x=0;y=0;a=0;b=0;
            Thread t1=new Thread(()->{
                b=1;y=a;
            });
            Thread t2=new Thread(()->{
                a=1;x=b;
            });

            t1.start();
            t2.start();
            t1.join();
            t2.join();

            if (x==0&y==0){
                System.out.println("InstructionsRearrangement happens at "+i);
                break;
            }else if (x==0&&y==1){
                System.out.println("t2");
            }else if (x==1&&y==0){
                System.out.println("t1");
            }else if (x==1&&y==1){
                System.out.println("t1 t2 run at the same time.");
            }
        }

    }
}
