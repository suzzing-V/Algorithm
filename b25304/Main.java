package b25304;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int n = sc.nextInt();
        int total = 0;
        
        for(int i = 0; i < n; i++) {
            total += sc.nextInt() * sc.nextInt();
        }

        if (total == x) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        sc.close();
    }
}
