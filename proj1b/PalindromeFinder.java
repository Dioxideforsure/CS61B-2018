/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    public static void main(String[] args) {
        int minLength = 4;
        In in = new In("D:\\TextBook & HomeWork\\CS61b-2018\\CS61B-2018\\library-sp18\\data\\words.txt");
        Palindrome palindrome = new Palindrome();

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, new OffByN(25))) {
                System.out.println(word);
            }
        }
    }
//    Uncomment this class once you've written isPalindrome.
}
