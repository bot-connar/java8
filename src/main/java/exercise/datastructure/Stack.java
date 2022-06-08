package exercise.datastructure;

public interface Stack<E> {
    int getSize();

    boolean isEmpty();

    E peek();

    E pop();

    void push(E e);
}
