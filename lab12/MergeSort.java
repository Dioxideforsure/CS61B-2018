import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        Queue<Queue<Item>> singleItemQueue = new Queue<>();
        for (Item i : items) {
            Queue<Item> singleItem = new Queue<>();
            singleItem.enqueue(i);
            singleItemQueue.enqueue(singleItem);
        }
        return singleItemQueue;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        // Your code here!
        Queue<Item> sortedQueue = new Queue<>();
        while (!(q1.isEmpty() || q2.isEmpty())) {
            Item first = q1.peek();
            Item last = q2.peek();
            if (first.compareTo(last) < 0) {
                sortedQueue.enqueue(q1.dequeue());
            } else if (first.compareTo(last) == 0) {
                sortedQueue.enqueue(q1.dequeue());
            } else {
                sortedQueue.enqueue(q2.dequeue());
            }
        }
        if (!q1.isEmpty()) {
            for (Item i : q1) {
                sortedQueue.enqueue(i);
            }
        }
        if (!q2.isEmpty()) {
            for (Item i : q2) {
                sortedQueue.enqueue(i);
            }
        }
        return sortedQueue;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        if (items.isEmpty() || items.size() == 1) {
            return items;
        }
        Queue<Queue<Item>> singleItemQueue = MergeSort.makeSingleItemQueues(items);
        while (singleItemQueue.size() != 1) {
            Queue<Item> first = singleItemQueue.dequeue();
            Queue<Item> last = singleItemQueue.dequeue();
            singleItemQueue.enqueue(MergeSort.mergeSortedQueues(first, last));
        }
        return singleItemQueue.dequeue();
    }

   /* public static void main(String[] args) {
        Queue<String> students = new Queue<>();
        students.enqueue("Alice");
        students.enqueue("Ha");
        students.enqueue("Gjd");
        students.enqueue("Ldk");
        students.enqueue("Uhs");
        students.enqueue("KLP");
        students.enqueue("Ldk");
        students.enqueue("Ethan");
        System.out.println(students);
        Queue<String> sortedStudents = MergeSort.mergeSort(students);
        System.out.println(sortedStudents);
    }*/
}
