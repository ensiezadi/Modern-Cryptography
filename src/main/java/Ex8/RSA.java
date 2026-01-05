package Ex8;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
    private BigInteger privateKey;
    private BigInteger publicKey;
    private BigInteger modulus;

    /**
     * Constructor to generate a new RSA key pair.
     * @param bitLength The desired bit length of the prime numbers.
     */
    public RSA(int bitLength) {
        SecureRandom rand = new SecureRandom();
        BigInteger p = new BigInteger(bitLength, 100, rand);
        BigInteger q = new BigInteger(bitLength, 100, rand);
        modulus = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        publicKey = new BigInteger("65537"); // Common public exponent

        // Ensure that publicKey is coprime to phi
        while (!phi.gcd(publicKey).equals(BigInteger.ONE)) {
            publicKey = publicKey.add(BigInteger.TWO);
        }

        privateKey = publicKey.modInverse(phi);
    }

    /**
     * Encrypts a message using the public key.
     * @param message The message to encrypt.
     * @return The encrypted message as a BigInteger.
     */
    public BigInteger encrypt(BigInteger message) {
        return Util.modPow(message, publicKey, modulus);
    }

    /**
     * Decrypts a ciphertext using the private key.
     * @param encryptedMessage The ciphertext to decrypt.
     * @return The original message as a BigInteger.
     */
    public BigInteger decrypt(BigInteger encryptedMessage) {
        return Util.modPow(encryptedMessage, privateKey, modulus);
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }

    public BigInteger getModulus() {
        return modulus;
    }
}

