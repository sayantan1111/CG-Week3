import java.util.*;
class Cs {
    public static void sort(int[] arr) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[19];
        for (int i = 0; i < n; i++)
            count[arr[i]]++;
        for (int i = 1; i <= 18; i++)
            count[i] += count[i - 1];
        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }
        for (int i = 0; i < n; i++)
            arr[i] = output[i];
    }
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = s.nextInt();
        sort(arr);
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        s.close();
    }
}