import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Week 2 Exercise — String analysis (implement TODO methods).
 *
 * Compile: javac TextAnalyzer.java
 * Run: java TextAnalyzer
 */
public class TextAnalyzer {

    public static int wordCount(String text) {
        String[] splitText = text.split("\\s+");
        System.out.println(splitText.length);
        return splitText.length;
    }

    public static boolean isPalindrome(String token) {
        token = token.replaceAll("[.,!? \\\\-]", ""); // Although not specified, this includes spaces.
        token = token.toLowerCase().trim();
        String reversedString = "";
        for (int i = token.length() - 1; i >= 0; i--) {
            reversedString = reversedString.concat(String.valueOf(token.charAt(i)));
        }
        // System.out.println("token: " + token + ", reversedString: " +
        // reversedString);
        return token.equals(reversedString);
    }

    public static int countOccurrences(String haystack, String needle) {
        int count = 0;
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            int last = i + needle.length();
            String currentSubstring = haystack.substring(i, last);
            // System.out.println(i + "-" + last + ": " + currentSubstring);
            if (currentSubstring.equals(needle)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        Path p = Path.of("sample.txt");
        String body = Files.readString(p);
        System.out.println("words=" + wordCount(body));
        System.out.println("palindrome(Radar)=" + isPalindrome("Radar"));
        System.out.println("occurrences of 'QA'=" + countOccurrences(body, "QA"));

        System.out.println("\nstretch goals:");
        String longerPalindrome = "A man, a plan, a canal, Panama.";
        System.out.println("palindrome(" + longerPalindrome + ")=" + isPalindrome(longerPalindrome));
        System.out.println("occurrences of \"aa\" in \"aaaa\": " + countOccurrences("aaaa", "aa"));
    }
}