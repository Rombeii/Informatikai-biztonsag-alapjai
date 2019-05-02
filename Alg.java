import java.math.BigInteger;
import java.util.ArrayList;

public class Alg{

    public static BigInteger[] ExtendedEuc(BigInteger a, BigInteger b){
        ArrayList<BigInteger> quotients = new ArrayList<>();
        quotients.add(null);                                 

        ArrayList<BigInteger> x = new ArrayList<>();
        x.add(BigInteger.ONE);
        x.add(BigInteger.ZERO);

        ArrayList<BigInteger> y = new ArrayList<>();
        y.add(BigInteger.ZERO);
        y.add(BigInteger.ONE);
        
        int counter = 0;
        while(!b.equals(BigInteger.valueOf(0))){
            BigInteger bi[] = a.divideAndRemainder(b);
            a = b;
            b = bi[1];
            quotients.add(bi[0]);

            if(counter > 1){
                x.add(quotients.get(counter - 1).multiply(x.get(counter - 1)).add(x.get(counter - 2)));
                y.add(quotients.get(counter - 1).multiply(y.get(counter - 1)).add(y.get(counter - 2)));
            }

            counter++;
        }

        x.add(quotients.get(counter - 1).multiply(x.get(counter - 1)).add(x.get(counter - 2)));
        y.add(quotients.get(counter - 1).multiply(y.get(counter - 1)).add(y.get(counter - 2)));

        return new BigInteger[]{a, x.get(counter), y.get(counter)};
    }
}

//https://www.tutorialspoint.com/java/math/biginteger_divideandremainder.htm