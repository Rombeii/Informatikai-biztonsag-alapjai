import java.math.BigInteger;

public class Main{
    public static void main(String[] args){
        BigInteger a = new BigInteger("211");
        BigInteger b = new BigInteger("45");

        BigInteger extended[] = Alg.ExtendedEuc(a, b);
        System.out.println("lnko: " +  extended[0] + " tobbi: " + extended[1] + " " + extended[2]);
    }

}