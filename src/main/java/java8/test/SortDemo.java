package java8.test;

import java.util.Arrays;

public class SortDemo {
    public static void main(String[] args) {
        int[] ints={8,5,6,3,0,1,9,2,4,7};
        new BubbleSort().sort(ints);
        Arrays.stream(ints).forEach(System.out::print);
    }
}
class BubbleSort{
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j <arr.length ; j++) {
                int current = arr[j];
                if (current<arr[i]){
                    int temp=arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;
                }
            }
        }
    }
}
