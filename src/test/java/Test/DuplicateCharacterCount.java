package Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DuplicateCharacterCount {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a word: ");
        String word = scanner.nextLine().toLowerCase();
        scanner.close();

        Map<Character, Integer> charCountMap = new HashMap<>();

        // Count each character in the string
        for (char c : word.toCharArray()) {
            if (Character.isLetter(c)) {
                charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
            }
        }

        System.out.println("Duplicate characters in the given string: ");
        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println(entry.getKey() + " repeated " + entry.getValue() + " times");
            }
        }
    }
}
