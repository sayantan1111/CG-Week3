import java.util.Arrays;
import java.util.Random;

class SearchTwo {
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
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
        int[] sizes = {1000, 10000, 1000000};
        Random random = new Random();

        System.out.println("Dataset Size (N)\tLinear Search (ms)\tBinary Search (ms)");

        for (int n : sizes) {
            int[] dataLinear = new int[n];
            int[] dataBinary = new int[n];
            for (int i = 0; i < n; i++) {
                dataLinear[i] = random.nextInt(2 * n);
                dataBinary[i] = dataLinear[i];
            }
            Arrays.sort(dataBinary);
            int target = random.nextInt(2 * n);

            long startTime = System.nanoTime();
            linearSearch(dataLinear, target);
            long endTime = System.nanoTime();
            long linearTime = (endTime - startTime) / 1000000;

            startTime = System.nanoTime();
            binarySearch(dataBinary, target);
            endTime = System.nanoTime();
            long binaryTime = (endTime - startTime) / 1000000;

            System.out.println(n + "\t\t\t" + linearTime + "\t\t\t" + binaryTime);
        }
    }
}