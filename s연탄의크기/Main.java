import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] stove;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        int maxStove = 0;
        StringTokenizer st = new StringTokenizer(bf.readLine());
        stove = new int[n];
        for(int i = 0; i < n; i++) {
            stove[i] = Integer.parseInt(st.nextToken());
            maxStove = Math.max(maxStove, stove[i]);
        }

        bw.write(String.valueOf(getMaxHouseCount(maxStove)));
        bw.close();
    }

    private static int getMaxHouseCount(int maxStove) {
        int result = 0;
        for(int i = maxStove; i > 1; i--) {
            int cnt = 0;
            for(int j = 0; j < n; j ++) {
                if(stove[j] % i == 0) {
                    cnt ++;
                }
            }
            result = Math.max(result, cnt);
        }
        return result;
    }
}

