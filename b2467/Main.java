import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] values;
    static int bigResult = 0;
    static int smallResult = 0;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(bf.readLine());
        values = new int[n];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = n - 1;

        while(left < right) {
            int sum = values[left] + values[right];
            if(sum == 0) {
                min = 0;
                bigResult = values[right];
                smallResult = values[left];
                break;
            }
            int gap = Math.abs(sum);
            if(min > gap) {
                min = gap;
                bigResult = values[right];
                smallResult = values[left];
            }

            if(sum < 0) {
                left ++;
            } else {
                right --;
            }
        }

        System.out.println(smallResult + " " + bigResult);
    }
}
