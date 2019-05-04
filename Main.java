import java.math.BigInteger;

public class Main{
    public static void main(String[] args){
        BigInteger a = new BigInteger("11");
        BigInteger b = new BigInteger("243");
        BigInteger c = new BigInteger("215");

        if(Alg.MillerRabin(a, b))
            System.out.println("prim");
        else
            System.out.println("nem prim");
     

    }

}