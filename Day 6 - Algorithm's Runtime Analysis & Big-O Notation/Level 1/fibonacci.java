class fibonacci {
    public static int fibonacciRecursive(int n) {
        if (n <= 1) return n;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static int fibonacciIterative(int n) {
        if (n <= 1) return n;
        int a = 0;
        int b = 1;
        int sum;
        for (int i = 2; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    public static void main(String[] args) {
        int[] nValues = {10, 30, 40};

        System.out.println("Fibonacci (N)\tRecursive (ms)\tIterative (ms)");

        for (int n : nValues) {
            long startTime = System.nanoTime();
            fibonacciRecursive(n);
            long endTime = System.nanoTime();
            long recursiveTime = (endTime - startTime) / 1000000;

            startTime = System.nanoTime();
            fibonacciIterative(n);
            endTime = System.nanoTime();
            long iterativeTime = (endTime - startTime) / 1000000;

            System.out.println(n + "\t\t\t" + recursiveTime + "\t\t\t" + iterativeTime);
        }
    }
}