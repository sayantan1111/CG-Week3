import java.util.*;

class RemoveDuplicates {
    public static String removeDuplicateChars(String str) {
        StringBuilder sb = new StringBuilder();
        HashSet<Character> charSet = new HashSet<>();
        for (char c : str.toCharArray()) {
            if (!charSet.contains(c)) {
                sb.append(c);
                charSet.add(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        String result = removeDuplicateChars(input);
        System.out.println(result);
        s.close();
    }
}