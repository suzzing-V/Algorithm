import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int min = Math.min(a, b);

        int maxCom = 1;
        while(true) {
            boolean flag = true;
            for(int i = min; i >= 2; i--) {
                if(a % i == 0 && b % i == 0) {
                    a /= i;
                    b /= i;
                    maxCom *= i;
                    min = Math.min(a, b);
                    flag = false;
                    break;
                }
            }
            if(flag) {
                break;
            }
        }
        int minCom = maxCom * a * b;
        System.out.println(maxCom);
        System.out.println(minCom);
    }
}
