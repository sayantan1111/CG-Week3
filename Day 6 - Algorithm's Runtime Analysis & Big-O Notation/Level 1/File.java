import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

class File {
    private static final String LARGE_FILE = "large_file.txt";
    private static final long FILE_SIZE = 500 * 1024 * 1024;

    public static void main(String[] args) {
        createLargeFile();

        long startTime;
        long endTime;

        startTime = System.currentTimeMillis();
        try (FileReader fr = new FileReader(LARGE_FILE);
             BufferedReader br = new BufferedReader(fr)) {
            while (br.read() != -1) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
        System.out.println("FileReader time: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        try (FileInputStream fis = new FileInputStream(LARGE_FILE);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)) {
            while (br.read() != -1) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
        System.out.println("InputStreamReader time: " + (endTime - startTime) + " ms");
    }

    private static void createLargeFile() {
        try (java.io.FileWriter fw = new java.io.FileWriter(LARGE_FILE)) {
            for (int i = 0; i < FILE_SIZE / 2; i++) {
                fw.write('a');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}