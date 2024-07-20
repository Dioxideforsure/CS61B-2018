import org.junit.Test;

public class RadixSortTester {

    @Test
    public void TestRadixSort() {
        String[] unsortedStrings = {"ha", "jk", "op", "np", "op", "ta", "yu", "pa", "hgg", "a"};
        String[] sortedStrings = RadixSort.sort(unsortedStrings);
        for (String s : sortedStrings) {
            System.out.println(s);
        }
    }
}
