public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        char[] wordList = word.toCharArray();
        Deque<Character> charList = new LinkedListDeque<>();
        for (char c:
             wordList) {
            charList.addLast(c);
        }
        return charList;
    }

    public boolean isPalindrome(String word) {
        if (word.isEmpty() || word.length() == 1) {
            return true;
        }
        Deque<Character> wordList = wordToDeque(word);
        if (wordList.removeFirst() == wordList.removeLast()) {
            return isPalindrome(word.substring(1, word.length() - 1));
        }
        return false;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.isEmpty() || word.length() == 1) {
            return true;
        }
        Deque<Character> wordList = wordToDeque(word);
        if (cc.equalChars(wordList.removeFirst(), wordList.removeLast())) {
            return isPalindrome(word.substring(1, word.length() - 1), cc);
        }
        return false;
    }
}
