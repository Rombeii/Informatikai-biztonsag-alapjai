import java.math.BigInteger;

public class Main{
    public static void main(String[] args){
        BigInteger a = new BigInteger("127");
        BigInteger b = new BigInteger("75");
        BigInteger c = new BigInteger("215");

        System.out.println(Alg.FastExp(a, b, c));
    }

}