import java.util.*;
import java.io.*;

public class Main {

    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());

        for(int i = 0; i < n; i++) {
            for(int a = 0; a < i; a ++) {
                System.out.print(" ");
            }
            for(int a = i; a < n; a++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
