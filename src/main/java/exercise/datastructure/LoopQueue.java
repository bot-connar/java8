package exercise.datastructure;

import org.apache.groovy.internal.util.Supplier;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LoopQueue<E> implements Queue<E>{

    private E[] data;
    private int front,tail;
    private int size;

    public LoopQueue(int capacity) {
        //这里浪费一个空间
        data = (E[])new Object[capacity+1];
        size=0;
        front = 0;
        tail = 0;
    }

    public LoopQueue() {
        this(10);
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return tail==front;
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("queue is Empty.");
        E e=data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size --;
        if (size==getCapacity()/4 && getCapacity()/2!=0)
            resize(getCapacity()/2);
        return e;
    }

    @Override
    public void enqueue(E e) {
        if (isFull()) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size ++;
    }


    @Override
    public E getFront() {
        return data[front];
    }

    public boolean isFull() {
        return (tail + 1) % data.length == front;
    }

    public void resize(int newCapacity) {
        E[] newData=(E[])new Object[newCapacity+1];
        for (int i = 0; i < size; i++) {
            newData[i]=data[(i+front)% data.length];
        }
        front=0;
        tail = size;
        data = newData;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue : size= %d , capacity= %d ,",size,getCapacity())).append("front [");
        for (int i = 0; i < size; i++) {
            res.append(data[(front+i)% data.length]);
            if (((front+i)% data.length+1)!=tail)
                res.append(", ");
        }
        /*for (int i = front; i!=tail; i=(i+1)% data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail) {
                res.append(", ");
            }
        }*/
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        double v1 = testQueue(new ArrayQueue<>(), 10000);
        double v2 = testQueue(new LoopQueue<>(), 10000);
        System.out.println(v1);
        System.out.println(v2);
    }

    public static double testQueue(Queue<Integer> queue,int counts){
        long start_time = System.currentTimeMillis();
        Random random = new Random();
        for (int i = 0; i < counts; i++) {
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < counts; i++) {
            queue.dequeue();
        }
        long end_time = System.currentTimeMillis();
        return (end_time-start_time)/1000.0;
    }
}
