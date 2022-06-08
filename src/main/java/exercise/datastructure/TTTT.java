package exercise.datastructure;

import exercise.datastructure.Array;

public class TTTT {
    public static void main(String[] args) {
        Array<Integer> array=new Array<>(10);
        for (int i = 0; i < 10; i++) {
            array.addLast(i);
        }
        System.out.println(array);
        array.insert(63,1);
        System.out.println(array);
        array.addFirst(1212);
        System.out.println(array);

        System.out.println(array.get(2));
        System.out.println(array.get(11));

        array.remove(1);
        System.out.println(array);

        array.removeFirst();
        System.out.println(array);

        array.removeLast();
        System.out.println(array);

        System.out.println(array.removeElement(0));
        System.out.println(array.removeElement(1));
        System.out.println(array);
        array.set(2, 2);
        System.out.println(array);
        System.out.println(array.contains(0));
    }
}
