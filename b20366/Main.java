import java.util.*;
import java.io.*;

public class Main {

    private static int[] arr;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int min = Integer.MAX_VALUE;
        Arrays.sort(arr);
        for(int i = 0; i <= n - 4; i++) {
            for(int j = i + 3; j < n; j++) {
                int snowman1 = arr[i] + arr[j];

                int left = i + 1;
                int right = j - 1;
                while(left < right) {
                    int snowman2 = arr[left] + arr[right];
                    min = Math.min(Math.abs(snowman1 - snowman2), min);

                    if(snowman1 < snowman2) { // snowman2를 줄여야됨
                        right --;
                    } else {
                        left ++;
                    }
                }
            }
        }

        bw.write(String.valueOf(min));
        bw.close();
    }
}
