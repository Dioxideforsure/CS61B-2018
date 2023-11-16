public class ArrayDeque<T> {
    private T[] arrayDeque;
    private int size;
    private int head;
    private int tail;
    private boolean isIn = false;
    private int cap;
    public ArrayDeque() {
        int capacity = 8;
        cap = capacity;
        arrayDeque = (T[]) new Object[capacity];
        size = 0;
        head = 0;
        tail = 1;
    }

    public void addFirst(T item) {
        if (isNotFull()) {
            arrayDeque[head] = item;
            if (head - 1 < 0) {
                head = arrayDeque.length - 1;
            } else {
                head--;
            }
        } else {
            resize();
            arrayDeque[head] = item;
            head--;
        }
        isIn = true;
        size++;
    }

    public void addLast(T item) {
        if (isNotFull()) {
            arrayDeque[tail] = item;
            tail = (tail + 1) % (arrayDeque.length - 1);
        } else {
            resize();
            arrayDeque[tail] = item;
            tail++;
        }
        isIn = true;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        if (size == 0) {
            System.out.println("Deque is empty!");
            return;
        }
        for (T elem:
             arrayDeque) {
            if (elem != null) {
                System.out.print(elem + " ");
            }
        }
        System.out.println();
    }

    public int size() {
        return size;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        head = (head + 1) % (arrayDeque.length);
        T item = arrayDeque[head];
        arrayDeque[head] = null;
        size--;
        isIn = false;
        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (tail - 1 < 0) {
            tail = arrayDeque.length - 1;
        } else {
            tail--;
        }
        size--;
        T item = arrayDeque[tail];
        arrayDeque[tail] = null;
        isIn = false;
        return item;
    }

    public T get(int index) {
        if (index >= tail || index < head) {
            return null;
        }
        return arrayDeque[index];
    }

    private boolean isNotFull() {
        return head != tail || !isIn;
    }

    private void resize() {
        int newCapacity = cap + cap / 2;
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(arrayDeque, 0, newArray, 0, tail);
        System.arraycopy(arrayDeque, head, newArray, newCapacity + head - cap, arrayDeque.length - tail);
        head = newCapacity + head - cap;
        cap = newCapacity;
        arrayDeque = newArray;
    }
}
