package exercise.datastructure;

import java.util.stream.IntStream;

public class ArrayStack<E> implements Stack<E>{
    Array<E> array;

    public ArrayStack(int capacity){
        array = new Array<>(capacity);
    }

    public ArrayStack() {
        array = new Array<>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack :").append("[");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i!=array.getSize()-1)
                res.append(", ");
        }
        res.append("] top");
        return res.toString();
    }


    public static void main(String[] args) {
        Stack<Integer> stack = new ArrayStack<>();
        IntStream.rangeClosed(1, 5).forEach(stack::push);
        stack.push(1);
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);
        System.out.println(stack.getSize());
        System.out.println(stack.isEmpty());
    }
}
