import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class SlidingWindowMaximum {
    public static int[] findMaxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        int resultIndex = 0;

        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i >= k - 1) {
                result[resultIndex++] = nums[deque.peekFirst()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] maxes = findMaxSlidingWindow(nums, k);
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("Window Size: " + k);
        System.out.println("Maximums in Sliding Windows: " + Arrays.toString(maxes));

        int[] nums2 = {1, 2, 3, 1, 4, 5, 2, 3, 6};
        int k2 = 3;
        int[] maxes2 = findMaxSlidingWindow(nums2, k2);
        System.out.println("Array: " + Arrays.toString(nums2));
        System.out.println("Window Size: " + k2);
        System.out.println("Maximums in Sliding Windows: " + Arrays.toString(maxes2));
    }
}