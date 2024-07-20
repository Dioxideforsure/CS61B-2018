/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        String[] copyAsciis = new String[asciis.length];
        int msd = Integer.MIN_VALUE;
        for (int i = 0; i < asciis.length; i++) {
            copyAsciis[i] = asciis[i];
            msd = asciis[i].length() > msd ? asciis[i].length() : msd;
        }
        for (int i = 0; i < msd; i++) {
            sortHelperLSD(copyAsciis, i);
        }
        return copyAsciis;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort

        int max = Character.MIN_VALUE;
        char[] singleDigitOnWords = new char[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            if (index <= asciis[i].length() - 1) {
                singleDigitOnWords[i] = asciis[i].charAt(asciis[i].length() - index - 1);
            } else {
                singleDigitOnWords[i] = 0;
            }
            max = max > singleDigitOnWords[i] ? max : singleDigitOnWords[i];
        }

        int[] counts = new int[max + 1];
        for (char c : singleDigitOnWords) {
            counts[c]++;
        }


        int[] starts = new int[max + 1];
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        String[] sorted2 = new String[asciis.length];
        for (int i = 0; i < asciis.length; i += 1) {
            int item = singleDigitOnWords[i];
            int place = starts[item];
            sorted2[place] = asciis[i];
            starts[item] += 1;
        }

        for (int i = 0; i < asciis.length; i++) {
            asciis[i] = sorted2[i];
        }

    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort

        return;
    }
}
