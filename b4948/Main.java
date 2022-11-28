package b4948;

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

        int n;
        int p;

        while (true) {
            n = sc.nextInt();
            if (n == 0)
                break;
            p = 0;
            for (int i = n + 1; i <= n * 2; i++) {
                if(is_prime(i)) {
                    p++;
                }
            }
            System.out.println(p);
        }

        sc.close();
    }
}
