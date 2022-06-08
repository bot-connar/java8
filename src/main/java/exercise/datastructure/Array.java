package exercise.datastructure;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Arrays;

public class Array<E> {
    private int size;
    private E[] data;

    public Array(int capacity){
        data = (E[])new Object[capacity];
        size=0;
    }

    public Array() {
        this(10);
    }

    public int getCapacity() {
        return data.length;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    //add a element to the data array.
    public void addLast(E e){
        insert(e,size);
    }
    //add a element to the data array.
    public void addFirst(E e){
        insert(e,0);
    }

    public E get(int index) {
        if (index<0||index>=size)
            throw new ArrayIndexOutOfBoundsException("out of bound!");
        return data[index];
    }

    public E remove(int index){
        if (index<0||index>=size)
            throw new ArrayIndexOutOfBoundsException("out of bound!");
        E res = data[index];
        for (int i = index; i <size-1 ; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size]= null;
        if (size== data.length/4&&data.length!=0)
            resize(data.length/2);
        return res;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public boolean removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;

    }



    public void set(int index,E e) {
        if (index<0||index>=size)
            throw new ArrayIndexOutOfBoundsException("out of bound!");
        data[index]=e;
    }

    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e)
                return true;
        }
        return false;
    }


    int find(E e) {
        for (int i = 0; i <= size-1; i++) {
            if (data[i]==e)
                return i;
        }
        return -1;
    }


    public void insert(E e, int index){
        //throw exception when the data array is full


        //to make sure the array is contiguous
        if (index<0||index>size)
            throw new IllegalArgumentException("add failed. require index>0 and index<size");
        if (data.length==size)
            resize(data.length*2);
//            throw new IllegalArgumentException("add failed. array is already full!");

        for (int i = size-1; i >=index ; i--) {
            data[i + 1] = data[i];
        }
        data[index]=e;
        size++;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("Array's capacity is "+getCapacity());
        stringBuilder.append("[");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(data[i]);
            if (i!=size-1)
                stringBuilder.append(", ");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    void resize(int newCapacity) {
        E[] newData=(E[])new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data=newData;

    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }
}

@AllArgsConstructor
@ToString
class Student {
    private String name;
    private int age;
}
