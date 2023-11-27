import org.junit.Test;

import static org.junit.Assert.*;

public class TestArrayDequeGold {

    @Test
    public void testDeque() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();

        for (int i = 0; i < 1000; i++) {
            Integer numberBetweenZeroAndHundred = StdRandom.uniform(1000);
            Integer numberBetweenZeroAndHundred1 = StdRandom.uniform(1000);
            sad.addLast(numberBetweenZeroAndHundred);
            ads.addLast(numberBetweenZeroAndHundred);
            sad.addFirst(numberBetweenZeroAndHundred1);
            ads.addFirst(numberBetweenZeroAndHundred1);
            Integer resultLastSad = sad.removeLast();
            Integer resultLastAds = ads.removeLast();
            Integer resultFirstSad = sad.removeFirst();
            Integer resultFirstAds = ads.removeFirst();
            assertEquals(numberBetweenZeroAndHundred, resultLastSad);
            assertEquals(numberBetweenZeroAndHundred, resultLastAds);
            assertEquals(numberBetweenZeroAndHundred1, resultFirstSad);
            assertEquals(numberBetweenZeroAndHundred1, resultFirstAds);

        }
    }
}
