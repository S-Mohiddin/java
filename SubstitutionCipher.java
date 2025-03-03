import java.util.Scanner;

public class SubstitutionCipher {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    public static String encrypt(String text, String key) {
        text = text.toUpperCase();
        StringBuilder encryptedText = new StringBuilder();
        
        for (char c : text.toCharArray()) {
            int index = ALPHABET.indexOf(c);
            if (index != -1) {
                encryptedText.append(key.charAt(index));
            } else {
                encryptedText.append(c); // Keep non-alphabet characters unchanged
            }
        }
        return encryptedText.toString();
    }
    
    public static String decrypt(String text, String key) {
        text = text.toUpperCase();
        StringBuilder decryptedText = new StringBuilder();
        
        for (char c : text.toCharArray()) {
            int index = key.indexOf(c);
            if (index != -1) {
                decryptedText.append(ALPHABET.charAt(index));
            } else {
                decryptedText.append(c); // Keep non-alphabet characters unchanged
            }
        }
        return decryptedText.toString();
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String key = "QWERTYUIOPASDFGHJKLZXCVBNM"; // Example substitution key
        
        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();
        
        String encryptedText = encrypt(plaintext, key);
        System.out.println("Encrypted: " + encryptedText);
        
        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted: " + decryptedText);
        
        scanner.close();
    }
}
