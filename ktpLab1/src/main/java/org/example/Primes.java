package org.example;

public class Primes {
    public static void main(String[] args) {
        String response;
        for (int i = 2; i <= 100; i++) {
            if (isPrime(i)) { response = " простое"; } else { response = " не является простым"; }
            System.out.println("Число: "+i+response);
        }
    }
    public static boolean isPrime(int n)
    {
        for (int i = 2; i < n; i++) {
                if (n % i == 0) { return(false); }
        }
        return(true);
    }
}
