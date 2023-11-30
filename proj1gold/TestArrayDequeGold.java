import org.junit.Test;

import static org.junit.Assert.*;

public class TestArrayDequeGold {

    @Test
    public void testDeque() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        int holder = 0;
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            double prob = StdRandom.uniform();
            if (prob < 0.25) {
                sad.addFirst(i);
                ads.addFirst(i);
                holder++;
                msg.append("addFirst ").append("(").append(i).append(")").append(" \n");
                assertEquals(msg.toString(), ads.get(0), sad.get(0));
            } else if (prob < 0.5) {
                sad.addLast(i);
                ads.addLast(i);
                holder++;
                msg.append("addLast ").append("(").append(i).append(")").append(" \n");
                assertEquals(msg.toString(), ads.get(holder - 1), sad.get(holder - 1));
            } else if (prob < 0.75) {
                if (ads.isEmpty()) {
                    msg.append("isEmpty()\n");
                    assertTrue(msg.toString(), sad.isEmpty());
                    continue;
                }
                holder--;
                msg.append("removeFirst ").append("(").append(")").append(" \n");
                assertEquals(msg.toString(), ads.removeFirst(), sad.removeFirst());
            } else {
                if (ads.isEmpty()) {
                    msg.append("isEmpty()\n");
                    assertTrue(msg.toString(), sad.isEmpty());
                    continue;
                }
                holder--;
                msg.append("removeLast ").append("(").append(")").append(" \n");
                assertEquals(msg.toString(), ads.removeLast(), sad.removeLast());
            }
        }
    }
}
