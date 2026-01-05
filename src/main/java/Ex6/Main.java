package Ex6;

import Ex6.lowlevel.AES_CBC;
import Ex6.lowlevel.DES_CFB;

import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- DES CFB Example ---");
        byte[] desKey = Util.generateRandomBytes(8);
        byte[] desIv = Util.generateRandomBytes(8);
        String desPlainText = "This is a test for DES in CFB mode.";

        System.out.println("Original Text: " + desPlainText);
        System.out.println("Key: " + Util.bytesToHex(desKey));
        System.out.println("IV: " + Util.bytesToHex(desIv));

        DES_CFB desCfb = new DES_CFB(desKey);
        byte[] desCipherText = desCfb.encrypt(desPlainText.getBytes(StandardCharsets.UTF_8), desIv);
        System.out.println("Encrypted Text: " + Util.bytesToHex(desCipherText));

        byte[] desDecryptedText = desCfb.decrypt(desCipherText, desIv);
        System.out.println("Decrypted Text: " + new String(desDecryptedText, StandardCharsets.UTF_8));

        System.out.println("\n----------------------------------------\n");

        System.out.println("--- AES CBC Example ---");
        byte[] aesKey = Util.generateRandomBytes(16); // 128-bit key
        byte[] aesIv = Util.generateRandomBytes(16);
        String aesPlainText = "This is a test for AES in CBC mode.";

        System.out.println("Original Text: " + aesPlainText);
        System.out.println("Key: " + Util.bytesToHex(aesKey));
        System.out.println("IV: " + Util.bytesToHex(aesIv));

        AES_CBC aesCbc = new AES_CBC(aesKey);
        byte[] aesCipherText = aesCbc.encrypt(aesPlainText.getBytes(StandardCharsets.UTF_8), aesIv);
        System.out.println("Encrypted Text: " + Util.bytesToHex(aesCipherText));

        byte[] aesDecryptedText = aesCbc.decrypt(aesCipherText, aesIv);
        System.out.println("Decrypted Text: " + new String(aesDecryptedText, StandardCharsets.UTF_8).trim());
    }
}

