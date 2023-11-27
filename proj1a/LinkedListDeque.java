public class LinkedListDeque<T> {
    private class LinkedNode {
        private LinkedNode prev;
        private LinkedNode next;
        private final T item;

        public LinkedNode(LinkedNode prev ,T item, LinkedNode next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private int size;
    private LinkedNode sentinel;

    public LinkedListDeque() {
        sentinel = new LinkedNode(null, (T) new Object(), null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        LinkedNode newNode = new LinkedNode(sentinel, item, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    public void addLast(T item) {
        LinkedNode newNode = new LinkedNode(sentinel.prev, item, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
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
        for (LinkedNode p = sentinel.next; p != sentinel; p = p.next) {
            if (p.next == sentinel) {
                System.out.println(p.item);
                break;
            }
            System.out.print(p.item + " ");
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return item;
    }

    public T get(int index) {
        if (index > size) {
            return null;
        }
        LinkedNode p = sentinel;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    private T getRecursive(LinkedListDeque<T>.LinkedNode p, int index) {
        if (index > size) {
            return null;
        }
        if (index == 0) {
            return p.item;
        }
        return getRecursive(p.next, index - 1);
    }
}
