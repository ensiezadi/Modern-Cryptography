package Ex7;

import java.util.Arrays;

public class HMAC_SHA1 {

    private static final int BLOCK_SIZE = 64;

    public static byte[] hmac(byte[] key, byte[] message) {
        SHA1 sha1 = new SHA1();
        byte[] keyBytes = new byte[BLOCK_SIZE];

        if (key.length > BLOCK_SIZE) {
            keyBytes = sha1.hash(key);
        } else {
            System.arraycopy(key, 0, keyBytes, 0, key.length);
        }

        byte[] o_key_pad = new byte[BLOCK_SIZE];
        byte[] i_key_pad = new byte[BLOCK_SIZE];

        for (int i = 0; i < BLOCK_SIZE; i++) {
            o_key_pad[i] = (byte) (keyBytes[i] ^ 0x5c);
            i_key_pad[i] = (byte) (keyBytes[i] ^ 0x36);
        }

        byte[] inner_hash_message = new byte[i_key_pad.length + message.length];
        System.arraycopy(i_key_pad, 0, inner_hash_message, 0, i_key_pad.length);
        System.arraycopy(message, 0, inner_hash_message, i_key_pad.length, message.length);
        byte[] inner_hash = sha1.hash(inner_hash_message);

        byte[] outer_hash_message = new byte[o_key_pad.length + inner_hash.length];
        System.arraycopy(o_key_pad, 0, outer_hash_message, 0, o_key_pad.length);
        System.arraycopy(inner_hash, 0, outer_hash_message, o_key_pad.length, inner_hash.length);

        return sha1.hash(outer_hash_message);
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}

