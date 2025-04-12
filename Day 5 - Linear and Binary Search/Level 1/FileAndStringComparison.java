import java.io.*;
import java.util.*;

public class FileAndStringComparison {

    public static void main(String[] args) {
        compareStringBuilders();
        compareFileReaders();
    }

    public static void compareStringBuilders() {
        List<String> stringList = Collections.nCopies(1000000, "hello");
        long startTime, endTime;
        double duration;

        startTime = System.nanoTime();
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : stringList) {
            stringBuilder.append(str);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000.0;
        System.out.println("StringBuilder: " + duration + " ms");

        startTime = System.nanoTime();
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : stringList) {
            stringBuffer.append(str);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000.0;
        System.out.println("StringBuffer: " + duration + " ms");
    }

    public static void compareFileReaders() {
        String fileName = "largeFile.txt";
        long startTime, endTime;
        double duration;
        long wordCount;

        createLargeFile(fileName, 100 * 1024 * 1024);

        startTime = System.nanoTime();
        wordCount = countWordsWithFileReader(fileName);
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000.0;
        System.out.println("FileReader: " + duration + " ms, Word Count: " + wordCount);

        startTime = System.nanoTime();
        wordCount = countWordsWithInputStreamReader(fileName);
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000.0;
        System.out.println("InputStreamReader: " + duration + " ms, Word Count: " + wordCount);

        new File(fileName).delete();
    }

    private static long countWordsWithFileReader(String fileName) {
        long count = 0;
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\\s+");
                count += words.length;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    private static long countWordsWithInputStreamReader(String fileName) {
        long count = 0;
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\\s+");
                count += words.length;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
    private static void createLargeFile(String fileName, long sizeInBytes) {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            byte[] buffer = "This is some sample text.  ".getBytes();
            long bytesWritten = 0;
            while (bytesWritten < sizeInBytes) {
                fos.write(buffer);
                bytesWritten += buffer.length;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}