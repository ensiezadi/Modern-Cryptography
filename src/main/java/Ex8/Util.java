package Ex8;

import java.math.BigInteger;

public class Util {

    /**
     * Performs modular exponentiation using the right-to-left binary method.
     * This is also known as fast exponentiation.
     * @param base The base.
     * @param exponent The exponent.
     * @param modulus The modulus.
     * @return (base^exponent) % modulus
     */
    public static BigInteger modPow(BigInteger base, BigInteger exponent, BigInteger modulus) {
        BigInteger result = BigInteger.ONE;
        base = base.mod(modulus);
        while (exponent.compareTo(BigInteger.ZERO) > 0) {
            if (exponent.testBit(0)) { // if exponent is odd
                result = result.multiply(base).mod(modulus);
            }
            exponent = exponent.shiftRight(1); // exponent = exponent / 2
            base = base.multiply(base).mod(modulus); // base = base^2 % modulus
        }
        return result;
    }
}

