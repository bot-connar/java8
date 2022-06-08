package exercise.datastructure;

import java.util.stream.IntStream;

public class ArrayQueue<E> implements Queue<E>{
    private Array<E> array;

    public ArrayQueue(int capacity) {
        array=new Array<>(capacity);
    }
    public ArrayQueue() {
        array=new Array<>();
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
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue :").append("front [");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i!=array.getSize()-1)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        IntStream.rangeClosed(1, 11).forEach(queue::enqueue);
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
        System.out.println(queue.getSize());
    }
}
