import java.math.BigInteger;

public class Main{
    public static void main(String[] args){
        BigInteger prim = Alg.GeneratePrime();
        System.out.println("prim1:" + prim);
        BigInteger prim2 = Alg.GeneratePrime();
        System.out.println("prim2:" + prim2);

        BigInteger n = prim.multiply(prim2);
        System.out.println("n:" + n);

        BigInteger n2 = prim.subtract(BigInteger.ONE).multiply(prim2.subtract(BigInteger.ONE));
        System.out.println("n2:" + n2);

        BigInteger e = Alg.GenerateRelativePrime(n2);
        System.out.println("e:" + e);

        BigInteger temp[] = Alg.ExtendedEuc(e, n2);

        System.out.println("x:" + temp[1] + " " + "y:" + temp[2]);
        BigInteger d = temp[1].mod(n2);
        if(d.compareTo(BigInteger.ZERO) == -1)
            d = d.add(n2);
        System.out.println("d:" + d);

        BigInteger msg = BigInteger.valueOf(20);
        System.out.println("msg:" + msg);

        BigInteger c = Alg.RSAEncrypt(msg, e, n);
        System.out.println("c:" + c);

        BigInteger m = Alg.RSADecrypt(c, d, n);
        System.out.println("m:" + m);

        
    }

}