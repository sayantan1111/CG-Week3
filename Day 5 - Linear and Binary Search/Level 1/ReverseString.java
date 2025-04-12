import java.util.*;
class ReverseString {
    public static String reverseString(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.reverse();
        return sb.toString();
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        String reversed = reverseString(input);
        System.out.println(reversed);
        s.close();
    }
}