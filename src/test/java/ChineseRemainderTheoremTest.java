import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChineseRemainderTheoremTest {

    @ParameterizedTest
    @CsvSource({"101, 103, 500, 3000"})
    public void testPowMethod(int p, int q, int x, int y) {
        BigInteger bigIntP = BigInteger.valueOf(p);
        BigInteger bigIntQ = BigInteger.valueOf(q);
        BigInteger bigIntX = BigInteger.valueOf(x);
        BigInteger bigIntY = BigInteger.valueOf(y);

        CRT crt = new CRT(bigIntP, bigIntQ);

        BigInteger actualResult = crt.pow(bigIntX, bigIntY);
        BigInteger expectedResult = BigInteger.valueOf(4546);

        assertEquals(actualResult, expectedResult,
                "Actual result of the pow method: " + actualResult + " should be the same as the expected result: "
                        + expectedResult);
    }

    @ParameterizedTest
    @CsvSource({"101, 103, 500, 3000"})
    public void testPowMethodWithModPow(int p, int q, int x, int y) {
        BigInteger bigIntP = BigInteger.valueOf(p);
        BigInteger bigIntQ = BigInteger.valueOf(q);
        BigInteger bigIntX = BigInteger.valueOf(x);
        BigInteger bigIntY = BigInteger.valueOf(y);

        CRT crt = new CRT(bigIntP, bigIntQ);

        BigInteger exponentWithPow = crt.pow(bigIntX, bigIntY);
        BigInteger exponentWithModPow = bigIntX.modPow(bigIntY, bigIntP.multiply(bigIntQ));

        assertEquals(exponentWithPow, exponentWithModPow,
                "The result of the pow method: " + exponentWithPow + " should be the same as the result of modPow method: "
                        + exponentWithModPow);
    }

}
