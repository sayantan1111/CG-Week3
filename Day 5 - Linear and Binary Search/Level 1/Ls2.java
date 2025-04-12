import java.util.Scanner;

class Ls2 {
    public static String findSentence(String[] sentences, String word) {
        for (String sentence : sentences) {
            if (sentence.contains(word)) {
                return sentence;
            }
        }
        return "Not Found";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of sentences:");
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] sentences = new String[n];
        System.out.println("Enter the sentences:");
        for (int i = 0; i < n; i++) {
            sentences[i] = scanner.nextLine();
        }
        System.out.println("Enter the word to search for:");
        String word = scanner.nextLine();
        String result = findSentence(sentences, word);
        System.out.println(result);
        scanner.close();
    }
}