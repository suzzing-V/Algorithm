package b9020;

import java.util.Scanner;

public class Main {
    public static boolean is_prime(int a) {
        for (int i = 2; i <= Math.sqrt(a); i++) {
            if(a % i == 0) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        int n;
        int a = 0;
        int b = 0;

        for (int i = 0; i < t; i++) {
            n = sc.nextInt();
            for (int j = 2; j <= n / 2; j++) {
                if (is_prime(j) && is_prime(n - j)) {
                    a = j;
                    b = n - j;
                }
            }
            System.out.println(a + " " + b);
        }

        sc.close();
    }
}
