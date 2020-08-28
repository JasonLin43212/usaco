public class prime {

    public static boolean isPrime(int i) {
        // Base Cases
        if (i < 2) return false;
        if (i == 2) return true;

        // Check if all numbers below the square root of i divides i
        // If any of them do, then we know that this number is prime
        for (int n = 2; n * n <= i; n++) {
            if (i % n == 0) {
                return false;
            }
        }
        return true;
    }

    // Euclid's GCD Algorithm
    public static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    // Similar to Euclid's GCD algorithm but as a while loop.
    // In the end, it checks if the GCD is 1 to see if it is relatively prime.
    public static boolean relativelyPrime(int a, int b) {
        int r = a % b;
        while (r != 0) {
            a = b;
            b = r;
            r = a % b;
        }
        return (b == 1);
    }

    public static void main(String[] args) {
        
    }
}
