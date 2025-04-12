import java.io.*;

public class ConsoleInputToFile {
    public static void main(String[] args) {
        try (InputStreamReader inputStreamReader = new InputStreamReader(System.in);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
             FileWriter fileWriter = new FileWriter("consoleInput.txt")) {

            String userInput;
            System.out.println("Enter text (type 'exit' to finish):");
            while (!(userInput = bufferedReader.readLine()).equalsIgnoreCase("exit")) {
                fileWriter.write(userInput + System.lineSeparator());
            }
            System.out.println("Input finished. Data written to consoleInput.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}