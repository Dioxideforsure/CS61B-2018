import java.util.Arrays;

public class ArrayDeque<T> {
    private T[] ArrayDeque;
    private int size;
    private int head;
    private int tail;
    private int capacity = 2000;
    public ArrayDeque() {
        ArrayDeque = (T[]) new Object[capacity];
        size = 0;
        head = tail = 0;
    }

    public void addFirst(T item) {
        if (head == 0) {
            ArrayDeque[head] = item;
        } else {
            T[] mid = (T[]) new Object[capacity * 2];
            System.arraycopy(ArrayDeque, 0, mid, 1, size);
            mid[0] = item;
            ArrayDeque = mid;
        }
        tail++;
        size++;
    }

    public void addLast(T item) {
        if (tail < capacity - 1) {
            ArrayDeque[tail] = item;
        } else {
            T[] mid = (T[]) new Object[capacity * 2];
            System.arraycopy(ArrayDeque, 0, mid, 0, size);
            mid[tail] = item;
            ArrayDeque = mid;
        }
        tail++;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        for (int i = head; i < tail; i++) {
            System.out.print(ArrayDeque[i]);
            System.out.print(" ");
        }
    }

    public int size() {
        return size;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = ArrayDeque[head];
        ArrayDeque[head] = null;;
        head++;
        size--;
        return item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        tail--;
        size--;
        T item = ArrayDeque[tail];
        ArrayDeque[tail] = null;
        return item;
    }

    public T get(int index) {
        if (index >= tail || index < head) {
            return null;
        }
        return ArrayDeque[index];
    }
}
