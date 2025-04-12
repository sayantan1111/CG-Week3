public class Find {
    public static void main(String[] args) {
        int[] arr = {4, 2, -3, 1, 6};
        findSubarraysWithZeroSum(arr);
        int[] arr2 = {1, -1, 3, -2, -2};
        findSubarraysWithZeroSum(arr2);
        int[] arr3 = {0, 0, 0};
        findSubarraysWithZeroSum(arr3);
    }

    public static void findSubarraysWithZeroSum(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr[j];
                if (sum == 0) {
                    printSubarray(arr, i, j);
                }
            }
        }
    }

    public static void printSubarray(int[] arr, int start, int end) {
        System.out.print("Subarray found from index " + start + " to " + end + ": ");
        for (int k = start; k <= end; k++) {
            System.out.print(arr[k] + " ");
        }
        System.out.println();
    }
}