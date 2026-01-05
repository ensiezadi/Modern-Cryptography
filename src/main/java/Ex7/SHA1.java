package Ex7;

public class SHA1 {

    private int H0 = 0x67452301;
    private int H1 = 0xEFCDAB89;
    private int H2 = 0x98BADCFE;
    private int H3 = 0x10325476;
    private int H4 = 0xC3D2E1F0;

    private int[] W = new int[80];

    private int leftrotate(int x, int c) {
        return (x << c) | (x >>> (32 - c));
    }

    public byte[] hash(byte[] message) {
        int messageLenBits = message.length * 8;
        int paddingLen = (448 - (messageLenBits % 512) + 512) % 512;
        if (paddingLen == 0) paddingLen = 512;
        int totalLen = message.length + paddingLen / 8 + 8;
        byte[] paddedMessage = new byte[totalLen];

        System.arraycopy(message, 0, paddedMessage, 0, message.length);
        paddedMessage[message.length] = (byte) 0x80;

        for (int i = 0; i < 8; i++) {
            paddedMessage[totalLen - 8 + i] = (byte) (messageLenBits >>> (56 - i * 8));
        }

        for (int i = 0; i < totalLen / 64; i++) {
            for (int j = 0; j < 16; j++) {
                W[j] = (paddedMessage[i * 64 + j * 4] & 0xFF) << 24 |
                       (paddedMessage[i * 64 + j * 4 + 1] & 0xFF) << 16 |
                       (paddedMessage[i * 64 + j * 4 + 2] & 0xFF) << 8 |
                       (paddedMessage[i * 64 + j * 4 + 3] & 0xFF);
            }

            for (int j = 16; j < 80; j++) {
                W[j] = leftrotate(W[j - 3] ^ W[j - 8] ^ W[j - 14] ^ W[j - 16], 1);
            }

            int a = H0;
            int b = H1;
            int c = H2;
            int d = H3;
            int e = H4;

            for (int j = 0; j < 80; j++) {
                int f, k;
                if (j < 20) {
                    f = (b & c) | ((~b) & d);
                    k = 0x5A827999;
                } else if (j < 40) {
                    f = b ^ c ^ d;
                    k = 0x6ED9EBA1;
                } else if (j < 60) {
                    f = (b & c) | (b & d) | (c & d);
                    k = 0x8F1BBCDC;
                } else {
                    f = b ^ c ^ d;
                    k = 0xCA62C1D6;
                }

                int temp = leftrotate(a, 5) + f + e + k + W[j];
                e = d;
                d = c;
                c = leftrotate(b, 30);
                b = a;
                a = temp;
            }

            H0 += a;
            H1 += b;
            H2 += c;
            H3 += d;
            H4 += e;
        }

        byte[] hash = new byte[20];
        for (int i = 0; i < 4; i++) {
            hash[i] = (byte) (H0 >>> (24 - i * 8));
            hash[i + 4] = (byte) (H1 >>> (24 - i * 8));
            hash[i + 8] = (byte) (H2 >>> (24 - i * 8));
            hash[i + 12] = (byte) (H3 >>> (24 - i * 8));
            hash[i + 16] = (byte) (H4 >>> (24 - i * 8));
        }
        return hash;
    }
}

