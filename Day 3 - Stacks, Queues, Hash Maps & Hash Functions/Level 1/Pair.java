public class Pair{
    public static void main(String[] args) {
        int[] arr = {1, 4, 45, 6, 10, 8};
        int sum = 16;
        boolean found = findPair(arr, sum);
        System.out.println("Pair with sum " + sum + " exists: " + found);
        int[] arr2 = {1, 2, 3, 4, 5};
        int sum2 = 10;
        boolean found2 = findPair(arr2, sum2);
        System.out.println("Pair with sum " + sum2 + " exists: " + found2);
    }

    public static boolean findPair(int[] arr, int sum) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] + arr[j] == sum) {
                    return true;
                }
            }
        }
        return false;
    }
}