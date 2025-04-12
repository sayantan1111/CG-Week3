public class StringConcatPerformance {

    public static void main(String[] args) {
        int numberOfStrings = 1000000;
        String baseString = "hello";

        long startTime, endTime;
        double duration;

        StringBuffer stringBuffer = new StringBuffer();
        startTime = System.nanoTime();
        for (int i = 0; i < numberOfStrings; i++) {
            stringBuffer.append(baseString);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000.0;
        System.out.println("StringBuffer: " + duration + " ms");

        StringBuilder stringBuilder = new StringBuilder();
        startTime = System.nanoTime();
        for (int i = 0; i < numberOfStrings; i++) {
            stringBuilder.append(baseString);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000.0;
        System.out.println("StringBuilder: " + duration + " ms");
    }
}