import java.io.*;
import java.util.*;

public class CountWordOccurrences {
    public static void main(String[] args) {
        String fileName = "myFile.txt";
        String targetWord = "the";
        int wordCount = 0;

        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (word.equalsIgnoreCase(targetWord)) {
                        wordCount++;
                    }
                }
            }
            System.out.println("The word \"" + targetWord + "\" appears " + wordCount + " times in the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}