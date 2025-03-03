import java.util.Scanner;

public class DynamicHillCipher {
    static int[][] keyMatrix = new int[2][2];
    static int[][] inverseKeyMatrix = new int[2][2];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input Key Matrix
        System.out.println("Enter 2x2 key matrix (numbers from 0 to 25):");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                keyMatrix[i][j] = scanner.nextInt();
            }
        }

        // Compute inverse key matrix
        if (!computeInverseKeyMatrix()) {
            System.out.println("Error: Key matrix is not invertible (mod 26). Try another matrix.");
            return;
        }

        scanner.nextLine(); // Consume newline
        System.out.println("Enter the plaintext (uppercase, no spaces): ");
        String plaintext = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "");

        if (plaintext.length() % 2 != 0) {
            plaintext += "X"; // Padding with 'X' for even length
        }

        String ciphertext = encrypt(plaintext);
        System.out.println("Ciphertext: " + ciphertext);

        String decryptedText = decrypt(ciphertext);
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }

    // Encrypt function
    private static String encrypt(String text) {
        return processText(text, keyMatrix);
    }

    // Decrypt function
    private static String decrypt(String text) {
        return processText(text, inverseKeyMatrix);
    }

    // Function to perform matrix-vector multiplication (mod 26)
    private static String processText(String text, int[][] matrix) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            int x = text.charAt(i) - 'A';
            int y = text.charAt(i + 1) - 'A';

            int newX = (matrix[0][0] * x + matrix[0][1] * y) % 26;
            int newY = (matrix[1][0] * x + matrix[1][1] * y) % 26;

            result.append((char) (mod26(newX) + 'A'));
            result.append((char) (mod26(newY) + 'A'));
        }

        return result.toString();
    }

    // Compute inverse key matrix dynamically
    private static boolean computeInverseKeyMatrix() {
        int det = keyMatrix[0][0] * keyMatrix[1][1] - keyMatrix[0][1] * keyMatrix[1][0];
        int detInverse = modInverse(det, 26);

        if (detInverse == -1) return false; // No inverse exists

        inverseKeyMatrix[0][0] = keyMatrix[1][1] * detInverse % 26;
        inverseKeyMatrix[0][1] = -keyMatrix[0][1] * detInverse % 26;
        inverseKeyMatrix[1][0] = -keyMatrix[1][0] * detInverse % 26;
        inverseKeyMatrix[1][1] = keyMatrix[0][0] * detInverse % 26;

        // Ensure all values are positive mod 26
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                inverseKeyMatrix[i][j] = mod26(inverseKeyMatrix[i][j]);
            }
        }
        return true;
    }

    // Compute modular inverse (Extended Euclidean Algorithm)
    private static int modInverse(int a, int m) {
        a = mod26(a);
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) return x;
        }
        return -1; // No modular inverse exists
    }

    // Ensure positive mod 26
    private static int mod26(int num) {
        return (num % 26 + 26) % 26;
    }
}
