import java.math.BigInteger;
import java.util.Random;

class CRT {

    private BigInteger p;
    private BigInteger q;

    public CRT(BigInteger p, BigInteger q) {
        this.p = p;
        this.q = q;
    }

    BigInteger pow(BigInteger basis, BigInteger exponent) {
        BigInteger z = q.modInverse(p);
        BigInteger a = basis.modPow(exponent, p);
        BigInteger b = basis.modPow(exponent, q);

        BigInteger v = a.subtract(b).multiply(z).mod(p);
        return q.multiply(v).add(b);
    }

    public int[] generateRandomParams(BigInteger n) {
        Random random = new Random();
        int x = random.ints(1, 2, n.subtract(BigInteger.ONE).intValue()).findFirst().getAsInt();
        int y = random.ints(1, 2, n.subtract(BigInteger.ONE).intValue()).findFirst().getAsInt();
        return new int[]{x, y};
    }

    public static void main(String args[]) {
        CRT crt = new CRT(BigInteger.valueOf(101), BigInteger.valueOf(103));

        int[] arrayXy = crt.generateRandomParams(crt.p.multiply(crt.q));
        BigInteger x = BigInteger.valueOf(arrayXy[0]);
        BigInteger y = BigInteger.valueOf(arrayXy[1]);
        System.out.println("random x: " + x);
        System.out.println("random y: " + y);

        // pow method
        long startTime1 = System.nanoTime();
        BigInteger powResult = crt.pow(x, y);
        long finishTime1 = System.nanoTime();
        System.out.println("Pow method with the result = " + powResult + " took: " + (finishTime1 - startTime1) + " nanoseconds");

        // modPow method
        long startTime2 = System.nanoTime();
        BigInteger modPowResult = x.modPow(y, crt.p.multiply(crt.q));
        long finishTime2 = System.nanoTime();
        System.out.println("ModPow method with the result = " + modPowResult + " took: " + (finishTime2 - startTime2) + " nanoseconds");
    }
}
