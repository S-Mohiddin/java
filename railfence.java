import java.util.Scanner;

public class railfence {
    public static String encrypt(String text, int key) {
        if (key <= 1) return text;

        StringBuilder[] rails = new StringBuilder[key];
        for (int i = 0; i < key; i++) {
            rails[i] = new StringBuilder();
        }

        int direction = 1; // 1 for down, -1 for up
        int row = 0;

        for (char c : text.toCharArray()) {
            rails[row].append(c);
            row += direction;

            if (row == 0 || row == key - 1) {
                direction = -direction;
            }
        }

        StringBuilder encrypted = new StringBuilder();
        for (StringBuilder rail : rails) {
            encrypted.append(rail);
        }
        return encrypted.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the text to encrypt: ");
        String text = scanner.nextLine();

        System.out.print("Enter the number of rails (key): ");
        int key = scanner.nextInt();

        String encryptedText = encrypt(text, key);
        System.out.println("Encrypted Text: " + encryptedText);
    }
}
