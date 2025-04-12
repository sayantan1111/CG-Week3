import java.util.Arrays;
import java.util.Random;

class SortTwo {

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    public static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; i++) {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[m + 1 + j];
        }
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        while (i < n1) {
            arr[k++] = L[i++];
        }
        while (j < n2) {
            arr[k++] = R[j++];
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 1000000};
        Random random = new Random();

        System.out.println("Dataset Size (N)\tBubble Sort (ms)\tMerge Sort (ms)\tQuick Sort (ms)");

        for (int n : sizes) {
            int[] dataBubble = new int[n];
            int[] dataMerge = new int[n];
            int[] dataQuick = new int[n];
            for (int i = 0; i < n; i++) {
                int val = random.nextInt(2 * n);
                dataBubble[i] = val;
                dataMerge[i] = val;
                dataQuick[i] = val;
            }

            long startTime = System.nanoTime();
            bubbleSort(dataBubble);
            long endTime = System.nanoTime();
            long bubbleTime = (endTime - startTime) / 1000000;

            startTime = System.nanoTime();
            mergeSort(dataMerge, 0, dataMerge.length - 1);
            endTime = System.nanoTime();
            long mergeTime = (endTime - startTime) / 1000000;

            startTime = System.nanoTime();
            quickSort(dataQuick, 0, dataQuick.length - 1);
            endTime = System.nanoTime();
            long quickTime = (endTime - startTime) / 1000000;

            System.out.println(n + "\t\t\t" + bubbleTime + "\t\t\t" + mergeTime + "\t\t\t" + quickTime);
        }
    }
}