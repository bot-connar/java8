package exercise.datastructure;

public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> list;
    public LinkedListStack() {
        list=new LinkedList<>();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public E peek() {
        //将第一个元素看作栈顶元素
        return list.getFirst();
    }

    @Override
    public E pop() {
        //将第一个元素看作栈顶元素
        return list.removeFirst();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: top ").append(list);
        return res.toString();
    }

    public static void main(String[] args) {
        /*LinkedListStack<Integer> list=new LinkedListStack<>();
        for (int i = 0; i < 5; i++) {
            list.push(i);
            System.out.println(list);
        }
        list.pop();
        System.out.println(list);*/
        int counts=100000;
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        LinkedListStack<Integer> listStack = new LinkedListStack<>();
        System.out.println(testStack(arrayStack,counts));
        System.out.println(testStack(listStack,counts));

    }

    public static double testStack(Stack<Integer> stack, int counts) {
        long start_time = System.nanoTime();
        for (int i = 0; i < counts; i++) {
            stack.push(i);
        }
        for (int i = 0; i < counts; i++) {
            stack.pop();
        }
        long end_time = System.nanoTime();
        return (end_time-start_time)/10000000.0;
    }
}
