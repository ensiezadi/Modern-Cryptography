package Ex6.lowlevel;

import java.util.Arrays;

public class AES_CBC {

    private AES aes;

    public AES_CBC(byte[] key) {
        this.aes = new AES(key);
    }

    public byte[] encrypt(byte[] plainText, byte[] iv) {
        int blockSize = 16;
        byte[] paddedPlainText = pkcs7Pad(plainText, blockSize);
        byte[] cipherText = new byte[paddedPlainText.length];
        byte[] feedback = iv;

        for (int i = 0; i < paddedPlainText.length; i += blockSize) {
            byte[] block = Arrays.copyOfRange(paddedPlainText, i, i + blockSize);
            for (int j = 0; j < blockSize; j++) {
                block[j] ^= feedback[j];
            }
            byte[] encryptedBlock = aes.encryptBlock(block);
            System.arraycopy(encryptedBlock, 0, cipherText, i, blockSize);
            feedback = encryptedBlock;
        }
        return cipherText;
    }

    public byte[] decrypt(byte[] cipherText, byte[] iv) {
        int blockSize = 16;
        byte[] plainText = new byte[cipherText.length];
        byte[] feedback = iv;

        for (int i = 0; i < cipherText.length; i += blockSize) {
            byte[] block = Arrays.copyOfRange(cipherText, i, i + blockSize);
            byte[] decryptedBlock = aes.decryptBlock(block);
            for (int j = 0; j < blockSize; j++) {
                decryptedBlock[j] ^= feedback[j];
            }
            System.arraycopy(decryptedBlock, 0, plainText, i, blockSize);
            feedback = block;
        }
        return pkcs7Unpad(plainText);
    }

    private byte[] pkcs7Pad(byte[] data, int blockSize) {
        int padding = blockSize - (data.length % blockSize);
        byte[] paddedData = new byte[data.length + padding];
        System.arraycopy(data, 0, paddedData, 0, data.length);
        for (int i = data.length; i < paddedData.length; i++) {
            paddedData[i] = (byte) padding;
        }
        return paddedData;
    }

    private byte[] pkcs7Unpad(byte[] data) {
        int padding = data[data.length - 1] & 0xFF;
        return Arrays.copyOfRange(data, 0, data.length - padding);
    }
}

