import java.math.BigInteger;

class CRT {

    private BigInteger p;
    private BigInteger q;
    private BigInteger z;

   public CRT(BigInteger p, BigInteger q, BigInteger z){
       this.p = p;
       this.q = q;
       this.z = z;
   }

   BigInteger pow(BigInteger basis, BigInteger exponent){
       BigInteger a = basis.pow(exponent.mod(p.subtract(BigInteger.ONE)).intValue()).mod(p);
       BigInteger b = basis.pow(exponent.mod(q.subtract(BigInteger.ONE)).intValue()).mod(q);

       BigInteger v = a.subtract(b).multiply(z).mod(p);
       return q.multiply(v).add(b);
   }


    public static void main(String args[])
    {
        CRT crt = new CRT(BigInteger.valueOf(101), BigInteger.valueOf(103), BigInteger.valueOf(51));
        BigInteger potenzResult = crt.pow(BigInteger.valueOf(500), BigInteger.valueOf(3000));
        System.out.println("potenz is equal to: " + potenzResult);
    }
}
