package Ex7;

public class Main {
    public static void main(String[] args) {
        String key = "my-secret-key";
        String message = "This is a test message for HMAC-SHA1.";

        System.out.println("Message: " + message);
        System.out.println("Key: " + key);

        byte[] hmac = HMAC_SHA1.hmac(key.getBytes(), message.getBytes());

        System.out.println("HMAC-SHA1: " + HMAC_SHA1.bytesToHex(hmac));
    }
}

