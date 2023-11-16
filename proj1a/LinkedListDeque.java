public class LinkedListDeque<T> {
    private class LinkedNode {
        private LinkedNode next;
        private T item;

        public LinkedNode(T item, LinkedNode next) {
            this.item = item;
            this.next = next;
        }
    }
    private int size;
    private LinkedNode head;
    private LinkedNode tail;
    public LinkedListDeque() {
        head = tail = new LinkedNode(null, null);
        size = 0;
    }

    private LinkedListDeque(T item) {
        head = new LinkedNode(null, null);
        head.next = tail = new LinkedNode(item, null) ;
        size = 1;
    }

    public void addFirst(T item) {
        LinkedNode p = head;
        p.next = new LinkedNode(item, head.next);
        if (size == 0)
            tail = p.next;
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
        LinkedNode p = head.next;
        while (p != null) {
            System.out.print(p.item.toString());
            System.out.print(" ");
            p = p.next;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = head.next.item;
        head = head.next;
        size--;
        return item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        LinkedNode p = head;
        T item;
        if (size == 1) {
            item = tail.item;
            head = tail = new LinkedNode(null, null);
            size = 0;
        } else {
            while (p.next.next != null) {
                p = p.next;
            }
            item = p.item;
            tail = p;
            size--;
        }
        return item;
    }

    public T get(int index) {
        if (index > size) {
            return null;
        }
        LinkedNode p = head;
        for (int i = 0; i <= index; i++) {
            p = p.next;
        }
        return p.item;
    }
}
