import java.util.Scanner;

public class ColumnarTranspo {

    // Function to encrypt the plaintext
    public static String encrypt(String text, String key) {
        int[] keyOrder = getKeyOrder(key);
        int cols = key.length();
        int rows = (int) Math.ceil((double) text.length() / cols);

        char[][] grid = new char[rows][cols];
        int textIndex = 0;

        // Fill the grid row by row
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (textIndex < text.length()) {
                    grid[i][j] = text.charAt(textIndex++);
                } else {
                    grid[i][j] = 'X'; // Fill empty cells with 'X'
                }
            }
        }

        // Read columns in the order of the key to create ciphertext
        StringBuilder cipherText = new StringBuilder();
        for (int col : keyOrder) {
            for (int row = 0; row < rows; row++) {
                cipherText.append(grid[row][col]);
            }
        }

        return cipherText.toString();
    }

    // Function to determine the order of the columns based on the key
    private static int[] getKeyOrder(String key) {
        char[] keyChars = key.toCharArray();
        int[] order = new int[keyChars.length];
        boolean[] used = new boolean[keyChars.length];

        for (int i = 0; i < keyChars.length; i++) {
            int minIndex = -1;
            char minChar = Character.MAX_VALUE;

            for (int j = 0; j < keyChars.length; j++) {
                if (!used[j] && keyChars[j] < minChar) {
                    minChar = keyChars[j];
                    minIndex = j;
                }
            }

            used[minIndex] = true;
            order[i] = minIndex;
        }

        return order;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine().replaceAll("\\s", "").toUpperCase();

        System.out.print("Enter the key: ");
        String key = scanner.nextLine().toUpperCase();

        String encryptedText = encrypt(plaintext, key);

        System.out.println("Encrypted Text: " + encryptedText);
    }
}
