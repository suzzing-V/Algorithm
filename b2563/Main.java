package b2563;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] square = new int[100][100];
        int n = sc.nextInt();
        int a;
        int b;
        int width = 0;

        for (int i = 0; i < n; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            for (int y = 0; y < 10; y++) {
                for(int x = 0; x < 10; x++) {
                    if (square[a+y][b+x] == 0) {
                        square[a + y][b + x]++;
                    }
                }
            }
        }

        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                width += square[i][j];
            }
        }

        System.out.println(width);
        sc.close();
    }
}
