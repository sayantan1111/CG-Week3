import java.util.*;
class StringConcatenation {
    public static String concatenateStrings(String[] strArray) {
        StringBuffer sb = new StringBuffer();
        for (String str : strArray) {
            sb.append(str);
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.nextLine();
        String[] strArray = new String[n];
        for (int i = 0; i < n; i++) {
            strArray[i] = s.nextLine();
        }
        String result = concatenateStrings(strArray);
        System.out.println(result);
        s.close();
    }
}