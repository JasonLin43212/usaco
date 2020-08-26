/*
ID: jasonli7
LANG: JAVA
TASK: frac1
*/
import java.io.*;
import java.util.*;

class Fraction {
    public int numer;
    public int denom;

    public Fraction(int n, int d) {
        this.numer = n;
        this.denom = d;
    }

    public int compareTo(Fraction other) {
        int thisVal = this.numer * other.denom;
        int otherVal = this.denom * other.numer;
        return thisVal - otherVal;
    }

    public String toString() {
        return numer + "/" + denom;
    }
}

public class frac1 {

    public static int gcd(int a, int b) { // Euclid's GCD Algorithm
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    // Can also use a relatively prime algorithm
    public static boolean relativelyPrime(int a, int b) {
        int r = a % b;
        while (r != 0) {
            a = b;
            b = r;
            r = a % b;
        }
        return (b == 1);
    }

    public static void main (String [] args) throws IOException {
        String fileName = "frac1";
        BufferedReader br = new BufferedReader(new FileReader(fileName + ".in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));

        int N = Integer.parseInt(br.readLine());

        ArrayList<Fraction> fractions = new ArrayList<>();
        // Every answer has these two
        fractions.add(new Fraction(0, 1));
        fractions.add(new Fraction(1, 1));

        if (N > 1) {
            for (int n = 1; n <= N-1; n++) {
                for (int d = n + 1; d <= N; d++) {
                    if (relativelyPrime(d, n)) {
                        fractions.add(new Fraction(n, d));
                    }
                }
            }
        }

        fractions.sort((a, b) -> a.compareTo(b));

        for (Fraction f: fractions){
            pw.println(f);
        }
        pw.close();
    }
}

// NOTE:
/*
    You can also do this problem by starting with the endpoints 0/0 and 0/1
    and then recursively generate midpoints by adding num and denom;
*/
