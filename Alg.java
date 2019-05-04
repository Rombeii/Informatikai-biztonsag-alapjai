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

        if(counter % 2 == 0){
            return new BigInteger[]{a, x.get(counter), y.get(counter).multiply(BigInteger.valueOf(-1))};
        } else {
            return new BigInteger[]{a, x.get(counter).multiply(BigInteger.valueOf(-1)), y.get(counter)};
        }
        
    }

    public static BigInteger FastExp(BigInteger n, BigInteger power, BigInteger modulo){
        String binary = power.toString(2);
        BigInteger temp = n;
        BigInteger returned = new BigInteger("1");

        for(int i = binary.length()-1; i > -1; i--){

            if(binary.charAt(i) == '1'){
                returned = returned.multiply(temp);
            }
            
            temp = temp.pow(2).mod(modulo);
        }

        return returned.mod(modulo);
    }

    public static boolean FermatTest(BigInteger a, BigInteger p){
        BigInteger temp[] = ExtendedEuc(a, p);
        if(!temp[0].equals(BigInteger.ONE)) 
            return false;
        
        if(FastExp(a, p.subtract(BigInteger.ONE), p).equals(BigInteger.ONE))
            return true;
        else
            return false;
    }

    public static boolean MillerRabin(BigInteger a, BigInteger power){
        int S = 0;
        BigInteger d = power.subtract(BigInteger.valueOf(1));

        while(d.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)){
            d = d.divide(BigInteger.valueOf(2));
            S++;
        }

        if(FastExp(a, d, power).equals(BigInteger.valueOf(1)))
            return true;
        
        int counter = 0;
        System.out.println(S);
        while(counter < S){
            System.out.println(a + " " + d.multiply(BigInteger.valueOf((int) Math.pow(2, counter))) + " " + power + " = " + power.subtract(BigInteger.valueOf(1)));
            if(FastExp(a, d.multiply(BigInteger.valueOf((int)Math.pow(2,counter))), power).equals(power.subtract(BigInteger.valueOf(1))))
                return true;
            counter++;
            System.out.println(FastExp(a, d.multiply(BigInteger.valueOf(2 ^ counter)), power));
        }
        return false;
    }
}


//https://www.tutorialspoint.com/java/math/biginteger_divideandremainder.htm