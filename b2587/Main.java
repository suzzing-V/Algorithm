package b2587;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int total = 0;
        int[] arr = new int[5];
        int temp;

        for (int i = 0; i < 5; i++) {
            arr[i] = sc.nextInt();
            total += arr[i];
        }
        
        for (int i = 0; i + 1 < 5; i++) {
            for (int j = 0; j + 1 < 5; j++) {
                if (arr[j] >= arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        
        System.out.println(total / 5);
        System.out.println(arr[2]);

        sc.close();
    }
}
