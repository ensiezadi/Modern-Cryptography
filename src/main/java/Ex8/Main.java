package Ex8;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
//        System.out.println("--- RSA Example ---");

        // 1. Key Generation
        RSA rsa = new RSA(1024); // Using 1024-bit keys for demonstration

        // 2. Message to be encrypted
        String originalMessage = "This is a secret message for RSA encryption.";
        BigInteger messageBigInt = new BigInteger(originalMessage.getBytes(StandardCharsets.UTF_8));

        System.out.println("Original Message: " + originalMessage);
        System.out.println("Public Key (e): " + rsa.getPublicKey());
        System.out.println("Modulus (n): " + rsa.getModulus());

        // 3. Encryption
        BigInteger encryptedMessage = rsa.encrypt(messageBigInt);
        System.out.println("Encrypted Message: " + encryptedMessage);

        // 4. Decryption
        BigInteger decryptedMessageBigInt = rsa.decrypt(encryptedMessage);
        String decryptedMessage = new String(decryptedMessageBigInt.toByteArray(), StandardCharsets.UTF_8);

        System.out.println("Decrypted Message: " + decryptedMessage);

        System.out.println("\n--- Verification ---");
        if (originalMessage.equals(decryptedMessage)) {
            System.out.println("Success: The decrypted message matches the original message.");
        } else {
            System.out.println("Failure: The decrypted message does not match the original message.");
        }
    }
}

