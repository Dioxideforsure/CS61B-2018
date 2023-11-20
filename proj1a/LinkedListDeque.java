public class LinkedListDeque<T> {
    private class LinkedNode {
        private LinkedNode next;
        private final T item;

        public LinkedNode(T item, LinkedNode next) {
            this.item = item;
            this.next = next;
        }
    }

    private int size;
    private LinkedNode head;
    private LinkedNode tail;
    private LinkedNode sentinel;

    public LinkedListDeque() {
        head = tail = sentinel = new LinkedNode(null, null);
        size = 0;
    }

    public void addFirst(T item) {
        LinkedNode p = head;
        p.next = new LinkedNode(item, head.next);
        if (size == 0) {
            tail = p.next;
        }
        size++;
    }

    public void addLast(T item) {
        tail.next = new LinkedNode(item, null);
        tail = tail.next;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (isEmpty()) {
            System.out.println("Deque is empty!");
            return;
        }
        LinkedNode p = head.next;
        while (p != null) {
            System.out.print(p.item.toString());
            System.out.print(" ");
            p = p.next;
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = head.next.item;
        head.next = head.next.next;
        size--;
        return item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        LinkedNode p = sentinel;
        T item;
        if (size == 1) {
            item = tail.item;
            head = tail = new LinkedNode(null, null);
            size = 0;
        } else {
            while (p.next.next != null) {
                p = p.next;
            }
            item = p.next.item;
            tail = p;
            tail.next = null;
            size--;
        }
        return item;
    }

    public T get(int index) {
        if (index > size) {
            return null;
        }
        LinkedNode p = head;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        if (index > size) {
            return null;
        }
        if (index == 0) {
            return sentinel.item;
        }
        sentinel = sentinel.next;
        return getRecursive(index - 1);
    }
}
