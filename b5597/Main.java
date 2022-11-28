package b5597;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] num = new int[31];

        for (int i = 0; i < 28; i++) {
            num[sc.nextInt()]++;
        }
        for (int i = 1; i < 31; i++) {
            if (num[i] == 0) {
                System.out.print(i + " ");
            }
        }

        sc.close();
    }
}
