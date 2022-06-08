package exercise.datastructure;

import java.util.Random;

public class LinkedListQueue<E> implements Queue<E> {



    private class Node{
        private Node next;
        private E e;

        public Node(Node next,E e) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(null, e);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }
    private Node head, tail;

    private int size;
    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty!");
        Node curr=head;
        head = head.next;
        curr.next = null;
        size--;
        return curr.e;
    }

    @Override
    public void enqueue(E e) {
        if (head==null&& tail==null) {
            head=tail=new Node(e);
        }else {
            Node node=new Node(e);
            tail.next = node;
            tail = node;
        }
        size++;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty!");
        }
        return head.e;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: ").append(String.format("size: %d head ", size));
        Node curr = head;
        while (curr != null) {
            if (curr != tail) {
                res.append(curr.e).append("-->");
            }
            curr = curr.next;
        }
        res.append(tail.e).append("-->NULL tail");
        return res.toString();
    }

    public static void main(String[] args) {
        int counts = 100000;
        Queue<Integer> arrayQueue = new ArrayQueue<>();
        System.out.println("arrayQueue: " + testQueue(counts,arrayQueue));
        Queue<Integer> loopQueue = new LoopQueue<>();
        System.out.println("loopQueue: " +testQueue(counts,loopQueue));
        Queue<Integer> linkedListQueue = new LinkedListQueue<>();
        System.out.println("linkedListQueue: " + testQueue(counts,linkedListQueue));

    }

    public static double testQueue(int counts, Queue<Integer> queue) {
        Random random = new Random();
        long start_time = System.nanoTime();
        for (int i = 0; i < counts; i++) {
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < counts; i++) {
            queue.dequeue();
        }
        long end_time = System.nanoTime();
        return (end_time-start_time)/10000000.0;
    }
}
