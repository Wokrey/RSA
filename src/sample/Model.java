package sample;

import java.math.BigInteger;
import java.util.Random;

public class Model {
    int bit;
    private BigInteger p;
    private BigInteger q;
    private BigInteger d;
    public BigInteger n, e;
    private BigInteger fEuler;
    Random random = new Random();

//    public RSA(int bitCount){
//        this.bit = bitCount;
//    }
    public void generateP(){
        setP(BigInteger.probablePrime(bit, random));
    }
    public void generateQ(){
        setQ(BigInteger.probablePrime(bit, random));
    }
    public void calcN(){
        n = getP().multiply(getQ());
    }
    public void calcFEuler(){
        fEuler = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
    }

    public void generateE(){
        e = BigInteger.probablePrime(64, random);
        while (hasMultiplier(e, fEuler) || e.compareTo(fEuler)!=-1 ){
            e = e.nextProbablePrime();
        }
        // e. = new BigInteger();
    }

    public void calcD(){
        d = solve(e, fEuler);
    }

    public BigInteger crypt(int i){
        BigInteger j = BigInteger.valueOf(i);
        return j.modPow(e, n);
    }

    public BigInteger decrypt(BigInteger code){
        return code.modPow(d,n);
    }

    public boolean hasMultiplier(BigInteger n1, BigInteger n2){
        if (!n1.gcd(n2).equals(BigInteger.ONE)){
            return true;
        }else {
            return false;
        }
    }
    private BigInteger solve(BigInteger a, BigInteger b)
    {
        BigInteger euler = b;
        BigInteger x = BigInteger.ZERO, y = BigInteger.ONE, lastx = BigInteger.ONE, lasty = BigInteger.ZERO, temp;
        while (!b.equals(BigInteger.ZERO))
        {
            BigInteger q = a.divide(b);
            BigInteger r = a.mod(b) ;

            a = b;
            b = r;

            temp = x;
            x = lastx.subtract(q.multiply(x));
            lastx = temp;

            temp = y;
            y = lasty.subtract(q.multiply(y));
            lasty = temp;
        }
        while (lastx.compareTo(BigInteger.ZERO)==-1){
            lastx=lastx.add(euler);
        }
        System.out.println( new BigInteger("5655").toString() );
        return lastx;
    }







    ///////////////////////////////set get/////////////////////////
    public BigInteger getP() {
        return p;
    }

    public void setP(BigInteger p) {
        this.p = p;
    }

    public BigInteger getQ() {
        return q;
    }

    public void setQ(BigInteger q) {
        this.q = q;
    }

    public BigInteger getD() {
        return d;
    }

    public void setD(BigInteger d) {
        this.d = d;
    }

    public BigInteger getfEuler() {
        return fEuler;
    }

    public void setfEuler(BigInteger fEuler) {
        this.fEuler = fEuler;
    }
}
