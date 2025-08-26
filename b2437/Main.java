import java.io.*;
import java.util.*;

public class Main {

    private static int[] arr;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        if(arr[0] != 1) {
            System.out.println("1");
            return;
        }

        int min = 1;
        for(int i = 0; i < n; i++) {
            if(arr[i] > min) {
                break;
            }

            min += arr[i];
        }

        System.out.println(min);
    }
}
