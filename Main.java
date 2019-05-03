import java.math.BigInteger;

public class Main{
    public static void main(String[] args){
        BigInteger a = new BigInteger("5");
        BigInteger b = new BigInteger("122");
        BigInteger c = new BigInteger("215");

        if(Alg.FermatTest(a, b))
            System.out.println("prim");
        else
            System.out.println("nem prim");
        
    }

}