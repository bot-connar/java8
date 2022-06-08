package exercise.datastructure;

import java.util.stream.IntStream;

public class LinkedList<E> {
    private class Node{
        Node next;
        E e;
        public Node(Node next, E e) {
            this.next = next;
            this.e = e;
        }
        public Node(E e) {
            this(null, e);
        }
        public Node(){
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node dummyHead;
    private int size;
    public LinkedList(){
        dummyHead = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E e){
        /*Node node = new Node(e);
        node.next = head;
        head = node;*/
        /*head = new Node(head, e);
        size++;*/
        add(0,e);
    }


    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index not illegal!");
        }
        //对于add方法，需要判断index=0的情况。可以使用dummyHead的方式来统一add方法，从而不要判断index=0的情况

        /*if (size == 0) {
            addFirst(e);
        }else {
            Node prev = null;
            for (int i = 0; i < index-1; i++) {
                prev = prev.next;
            }
            prev.next = new Node(prev.next, e);
            *//*Node node = new Node(e);
            node.next=prev.next;
            prev.next = node;*//*
            size++;
        }*/

        Node prev=dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        prev.next = new Node(prev.next, e);
        size++;
    }

    public void addLast(E e) {
        add(size,e);
    }
    public E get(int index){
        Node curr = dummyHead.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size-1);
    }

    public boolean contains(E e) {
        Node curr = dummyHead.next;
        while (curr!=null) {
            if (curr.e.equals(e)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public void set(int index, E e) {
        Node curr = dummyHead.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        curr.e = e;
    }



    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("LinkedList: size %d ,[ ",size));
        Node curr = dummyHead.next;
        while (curr != null) {
            res.append(curr.e).append("-->");
            curr = curr.next;
        }
        res.append("NULL ]");
        return res.toString();
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed! Index is illegal!");
        }
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;
        size--;
        return delNode.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        IntStream.rangeClosed(1, 10).forEach(list::addLast);
        System.out.println(list);
        System.out.println(list.get(2));
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        System.out.println(list.contains(5));
        list.addFirst(22);
        System.out.println(list);
        list.set(1, 11);
        System.out.println(list);
        list.add(2,333);
        System.out.println(list);
        list.removeFirst();
        System.out.println(list);
        list.removeLast();
        System.out.println(list);
        list.remove(2);
        System.out.println(list);


    }
}
