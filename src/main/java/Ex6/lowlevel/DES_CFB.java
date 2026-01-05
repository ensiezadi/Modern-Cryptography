package Ex6.lowlevel;

import java.util.Arrays;

public class DES_CFB {

    private DES des;

    public DES_CFB(byte[] key) {
        this.des = new DES(key);
    }

    public byte[] encrypt(byte[] plainText, byte[] iv) {
        int blockSize = 8;
        byte[] cipherText = new byte[plainText.length];
        byte[] feedback = iv;

        for (int i = 0; i < plainText.length; i += blockSize) {
            byte[] encryptedFeedback = des.encryptBlock(feedback);
            int remaining = plainText.length - i;
            int length = Math.min(blockSize, remaining);

            for (int j = 0; j < length; j++) {
                cipherText[i + j] = (byte) (plainText[i + j] ^ encryptedFeedback[j]);
            }
            feedback = Arrays.copyOfRange(cipherText, i, i + length);
        }
        return cipherText;
    }

    public byte[] decrypt(byte[] cipherText, byte[] iv) {
        int blockSize = 8;
        byte[] plainText = new byte[cipherText.length];
        byte[] feedback = iv;

        for (int i = 0; i < cipherText.length; i += blockSize) {
            byte[] encryptedFeedback = des.encryptBlock(feedback);
            int remaining = cipherText.length - i;
            int length = Math.min(blockSize, remaining);

            feedback = Arrays.copyOfRange(cipherText, i, i + length);
            for (int j = 0; j < length; j++) {
                plainText[i + j] = (byte) (cipherText[i + j] ^ encryptedFeedback[j]);
            }
        }
        return plainText;
    }
}

