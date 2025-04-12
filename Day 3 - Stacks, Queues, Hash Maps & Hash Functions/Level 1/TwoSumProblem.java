public class TwoSumProblem {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = findTwoSum(nums, target);
        System.out.println(result[0] + " " + result[1]);
        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        int[] result2 = findTwoSum(nums2, target2);
        System.out.println(result2[0] + " " + result2[1]);
    }

    public static int[] findTwoSum(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {-1, -1};
    }
}