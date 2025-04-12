import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

class SearchArray {
    public static boolean searchArray(List<Integer> array, int target) {
        for (int element : array) {
            if (element == target) {
                return true;
            }
        }
        return false;
    }

    public static boolean searchHashSet(Set<Integer> hashSet, int target) {
        return hashSet.contains(target);
    }

    public static boolean searchTreeSet(Set<Integer> treeSet, int target) {
        return treeSet.contains(target);
    }

    public static void main(String[] args) {
        int[] sizes = {1000, 100000, 1000000};
        Random random = new Random();

        System.out.println("Dataset Size (N)\tArray Search (ms)\tHashSet Search (ms)\tTreeSet Search (ms)");

        for (int n : sizes) {
            List<Integer> array = new ArrayList<>();
            Set<Integer> hashSet = new HashSet<>();
            Set<Integer> treeSet = new TreeSet<>();
            for (int i = 0; i < n; i++) {
                int val = random.nextInt(2 * n);
                array.add(val);
                hashSet.add(val);
                treeSet.add(val);
            }
            int target = random.nextInt(2 * n);

            long startTime = System.nanoTime();
            searchArray(array, target);
            long endTime = System.nanoTime();
            long arrayTime = (endTime - startTime) / 1000000;

            startTime = System.nanoTime();
            searchHashSet(hashSet, target);
            endTime = System.nanoTime();
            long hashSetTime = (endTime - startTime) / 1000000;

            startTime = System.nanoTime();
            searchTreeSet(treeSet, target);
            endTime = System.nanoTime();
            long treeSetTime = (endTime - startTime) / 1000000;

            System.out.println(n + "\t\t\t" + arrayTime + "\t\t\t" + hashSetTime + "\t\t\t" + treeSetTime);
        }
    }
}