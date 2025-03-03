// Source code is decompiled from a .class file using FernFlower decompiler.
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class DES {
   private static final String UNICODE_FORMAT = "UTF8";
   private static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
   private Cipher cipher = Cipher.getInstance("DESede");
   private SecretKey key;
   private String myEncryptionKey = "ThisIsSecretEncryptionKey";

   public DES() throws Exception {
      byte[] var1 = this.myEncryptionKey.getBytes("UTF8");
      DESedeKeySpec var2 = new DESedeKeySpec(var1);
      SecretKeyFactory var3 = SecretKeyFactory.getInstance("DESede");
      this.key = var3.generateSecret(var2);
   }

   public String encrypt(String var1) {
      try {
         this.cipher.init(1, this.key);
         byte[] var2 = var1.getBytes("UTF8");
         byte[] var3 = this.cipher.doFinal(var2);
         return Base64.getEncoder().encodeToString(var3);
      } catch (Exception var4) {
         var4.printStackTrace();
         return null;
      }
   }

   public String decrypt(String var1) {
      try {
         this.cipher.init(2, this.key);
         byte[] var2 = Base64.getDecoder().decode(var1);
         byte[] var3 = this.cipher.doFinal(var2);
         return new String(var3, "UTF8");
      } catch (Exception var4) {
         var4.printStackTrace();
         return null;
      }
   }

   public static void main(String[] var0) throws Exception {
      DES var1 = new DES();
      BufferedReader var2 = new BufferedReader(new InputStreamReader(System.in));
      System.out.print("Enter the string to encrypt: ");
      String var3 = var2.readLine();
      String var4 = var1.encrypt(var3);
      System.out.println("Encrypted String: " + var4);
      String var5 = var1.decrypt(var4);
      System.out.println("Decrypted String: " + var5);
   }
}