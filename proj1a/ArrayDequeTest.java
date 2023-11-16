import org.junit.Test;

public class ArrayDequeTest {
    @Test
    public void Test() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.addFirst(1);
        arrayDeque.addLast(2);
        arrayDeque.addFirst(10);
        arrayDeque.addLast(5);
        arrayDeque.addFirst(20);
        arrayDeque.addLast(13);
        arrayDeque.addFirst(26);
        arrayDeque.addLast(139);
        arrayDeque.addFirst(6);
        arrayDeque.addLast(39);
        arrayDeque.addFirst(61);
        arrayDeque.addLast(391);
        arrayDeque.printDeque();
        arrayDeque.removeLast();
        arrayDeque.removeFirst();
        arrayDeque.removeLast();
        arrayDeque.removeFirst();
        arrayDeque.removeLast();
        arrayDeque.removeFirst();
        arrayDeque.printDeque();
        arrayDeque.removeLast();
        arrayDeque.removeFirst();
        arrayDeque.removeLast();
        arrayDeque.removeFirst();
        arrayDeque.removeFirst();
        arrayDeque.removeLast();
        arrayDeque.printDeque();
    }
}
