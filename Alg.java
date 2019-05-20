import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

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
        if(counter != 1){
            x.add(quotients.get(counter - 1).multiply(x.get(counter - 1)).add(x.get(counter - 2)));
            y.add(quotients.get(counter - 1).multiply(y.get(counter - 1)).add(y.get(counter - 2)));
        }



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

    public static boolean MillerRabin(BigInteger a, BigInteger base){
        int S = 0;
        BigInteger d = a.subtract(BigInteger.valueOf(1));

        while(d.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)){
            d = d.divide(BigInteger.valueOf(2));
            S++;
        }
        if(FastExp(base, d, a).equals(BigInteger.valueOf(1)))
            return true;
        int counter = 0;
        while(counter < S){     
            if(FastExp(base, d.multiply(BigInteger.valueOf((int)Math.pow(2,counter))), a).equals(a.subtract(BigInteger.valueOf(1))))
                return true;
            counter++;
        }
        return false;
    }

    private static BigInteger GenerateBigInteger(int numberOfBits){
        SecureRandom rnd = new SecureRandom();
        BigInteger returned = new BigInteger(numberOfBits, rnd);
        if(returned.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO))
            return returned.add(BigInteger.valueOf(1));
        else
            return returned;

    }

    public static BigInteger GeneratePrime(){

        while(true){
            BigInteger prime = GenerateBigInteger(512);
            boolean isPrime = true;
            Random rand = new Random();
            for(int i = 0; i < 5; i++){
                int random = rand.nextInt(100) + 2;
                if(!MillerRabin(prime, BigInteger.valueOf(random)))
                    isPrime = false;
            }
            if(isPrime)
                return prime;

        }
    }

    public static BigInteger GenerateRelativePrime(BigInteger prime){
        while(true){
            int temp = new Random().nextInt(1000);
            BigInteger n = BigInteger.valueOf(temp);
            BigInteger[] test = ExtendedEuc(prime, n);
            if(test[0].equals(BigInteger.ONE))
                return n;
        }

    }

    public static BigInteger RSAEncrypt(BigInteger msg, BigInteger e, BigInteger n){
        return FastExp(msg, e, n);
    }

    public static BigInteger RSADecrypt(BigInteger c, BigInteger d, BigInteger n){
        return FastExp(c, d, n);
    }
}
