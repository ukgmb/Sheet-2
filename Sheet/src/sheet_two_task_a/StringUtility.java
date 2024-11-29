package sheet_two_task_a;

public class StringUtility {

    public static void main(String[] args) {
        System.out.println(removeVowels("Hello World"));
        System.out.println(replaceSubstring("Hello WorldWorld", "World", "Java "));
        System.out.println(wordCount("  Hello there,     World! "));
        System.out.println(rotateString("abcdef", 2));
        System.out.println(removeNonLetters("H3llo W4rl-d"));
        System.out.println(isRotation("waterbottle", "erbottlewat"));
    }

    public static String removeVowels(String word) {
        char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u'};
        String lowWord = word.toLowerCase();
        String newWord = "";

        for (int i = 0; i < word.length(); i++) {
            Boolean isVowel = false;
            for (int m = 0; m < 5; m++) {
                if (lowWord.charAt(i) == vowels[m]) {
                    isVowel = true;
                }
            }
            if (!isVowel) {
                Character index = word.charAt(i);
                newWord = newWord + index.toString();
            }
        }

        return newWord;
    }

    public static String replaceSubstring(String word, String target, String replacement) {
        return word.replaceAll(target, replacement);
    }

    public static int wordCountEasy(String sentence) {
        int sum = 0;
        for (int i = 0; i < (sentence.length() - 2); i++) {
            Character a = sentence.charAt(i);
            Character b = sentence.charAt(i + 1);
            Character c = sentence.charAt(i + 2);
            if (!(a.equals(' ')) && b.equals(' ') && !(c.equals(' '))) {
                sum++;
            }
        }
        if (sum > 0) {
            sum++;
        }

        return sum;
    }

    public static int wordCount(String sentence) {
        int sum = 0;
        int length = sentence.length();

        for (int i = 0; i < length; i++) {
            Character a = sentence.charAt(i);
            if (a.equals(' ')) {
                String cut = sentence.substring(0, i);
                sentence = sentence.substring(i + 1);
                i = -1;
                length = sentence.length();
                if (!(cut.equals(" ")) && !(cut.isEmpty())) {
                    sum++;
                }
            }
        }

        if (!(sentence.isEmpty()) && sum > 0) {
            sum++;
        }

        return sum;
    }

    public static String rotateString(String word, int positions) {
        word = word.substring(word.length() - positions) + word.substring(0, (word.length() - positions));

        return word;
    }

    public static String removeNonLetters(String sentence) {
        String newWord = "";

        for (int i = 0; i < sentence.length(); i++) {
            Boolean isLetter = false;
            Character check = sentence.charAt(i);
            if (Character.isLetter(check)) {
                isLetter = true;
            }
            if (isLetter) {
                newWord = newWord + check.toString();
            }
        }

        return newWord;
    }

    public static boolean isRotation(String word, String rotation) {
        for (int i = 0; i < word.length(); i++) {
            if (rotation.equals(rotateString(word, i))) {
                return true;
            }
        }
        return false;
    }

}


