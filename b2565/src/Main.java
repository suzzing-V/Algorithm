import java.io.*;
import java.util.Comparator;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());
        String[] str = new String[2];

        int[][] electric = new int[n + 1][2];
        for(int i = 1; i < n + 1; i++) {
            str = bf.readLine().split(" ");
            electric[i][0] = Integer.parseInt(str[0]);
            electric[i][1] = Integer.parseInt(str[1]);
        }
        
        Arrays.sort(electric, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0]; //앞에게 더 작으면 음수
            }
        });

        int ascend = findAscend(electric, n);
        bw.write(Integer.toString(n - ascend));
        bw.close();
    }

    public static int findAscend(int[][] electric, int n) {
        int[] memo = new int[n + 1];
        memo[1] = 1;
        int max;
        for(int i = 2; i <= n; i++) {
            max = 0;
            for(int j = 1; j < i; j++) {
                if(electric[j][1] < electric[i][1] && max < memo[j]) {
                    max = memo[j];
                }
            }
            memo[i] = max + 1;
        }
        return memo[n];
    }
}
