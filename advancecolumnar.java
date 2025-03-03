import java.util.Scanner;

public class advancecolumnar {

    // Function to encrypt plaintext
    public static String encrypt(String plaintext, String key) {
        int cols = key.length();
        int rows = (int) Math.ceil((double) plaintext.length() / cols);
        char[][] grid = new char[rows][cols];

        // Fill the grid row by row
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (index < plaintext.length()) {
                    grid[i][j] = plaintext.charAt(index++);
                } else {
                    grid[i][j] = 'X'; // Add padding
                }
            }
        }

        // Read the columns in order of the key
        StringBuilder ciphertext = new StringBuilder();
        for (char ch : key.toCharArray()) {
            int col = ch - 'A'; // Determine column based on key
            for (int row = 0; row < rows; row++) {
                ciphertext.append(grid[row][col]);
            }
        }
        return ciphertext.toString();
    }

    // Function to decrypt ciphertext
    public static String decrypt(String ciphertext, String key) {
        int cols = key.length();
        int rows = ciphertext.length() / cols;
        char[][] grid = new char[rows][cols];

        // Fill the grid column by column using the key
        int index = 0;
        for (char ch : key.toCharArray()) {
            int col = ch - 'A';
            for (int row = 0; row < rows; row++) {
                grid[row][col] = ciphertext.charAt(index++);
            }
        }

        // Read the grid row by row to reconstruct plaintext
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                plaintext.append(grid[i][j]);
            }
        }
        return plaintext.toString().replace("X", ""); // Remove padding
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = scanner.nextLine().replace(" ", "").toUpperCase();

        System.out.print("Enter key (e.g., ABCD): ");
        String key = scanner.nextLine().toUpperCase();

        String encrypted = encrypt(plaintext, key);
        System.out.println("Encrypted Text: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted Text: " + decrypted);

        scanner.close();
    }
}
