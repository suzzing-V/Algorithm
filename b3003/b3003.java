package b3003;

import java.util.Scanner;

public class b3003 {
    public static void main(String[] args) {
        int[] chess = {0, 0, 0, 0, 0, 0};
        Scanner sc = new Scanner(System.in);

        chess[0] = 1 - sc.nextInt();
        chess[1] = 1 - sc.nextInt();
        chess[2] = 2 - sc.nextInt();
        chess[3] = 2 - sc.nextInt();
        chess[4] = 2 - sc.nextInt();
        chess[5] = 8 - sc.nextInt();
        for (int i = 0; i < 6; i++) {
            System.out.print(chess[i] + " ");
        }
        
        sc.close();
    }
}