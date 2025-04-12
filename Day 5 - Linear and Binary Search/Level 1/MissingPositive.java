import java.util.Arrays;
import java.util.Scanner;

class MissingPositive {

    public static int findMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0 && nums[i] <= n) {
                int pos = nums[i] - 1;
                if (nums[pos] != nums[i]) {
                    int temp = nums[pos];
                    nums[pos] = nums[i];
                    nums[i] = temp;
                    i--;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of elements for the first array:");
        int n1 = scanner.nextInt();
        int[] nums = new int[n1];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n1; i++) {
            nums[i] = scanner.nextInt();
        }
        int missingPositive = findMissingPositive(nums);
        System.out.println("First missing positive integer: " + missingPositive);

        System.out.println("Enter the number of elements for the second array:");
        int n2 = scanner.nextInt();
        int[] arr = new int[n2];
        System.out.println("Enter the elements of the sorted array:");
        for (int i = 0; i < n2; i++) {
            arr[i] = scanner.nextInt();
        }
        System.out.println("Enter the target number for binary search:");
        int target = scanner.nextInt();
        int targetIndex = binarySearch(arr, target);
        System.out.println("Index of the target number: " + targetIndex);
        scanner.close();
    }
}