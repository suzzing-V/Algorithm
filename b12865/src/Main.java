import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] nm = new String[2];
        nm = bf.readLine().split(" ");
        int N = Integer.parseInt(nm[0]);
        int K = Integer.parseInt(nm[1]);

        int[] weight = new int[N + 1];
        int[] value = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            String[] wv = new String[2];
            wv = bf.readLine().split(" ");
            weight[i] = Integer.parseInt(wv[0]);
            value[i] = Integer.parseInt(wv[1]);
        }

        int[][] memo = new int[N + 1][K + 1];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= K; j++) {
                if(weight[i] > j) { memo[i][j] = memo[i - 1][j]; }
                else {
                    memo[i][j] = Math.max(memo[i - 1][j], memo[i - 1][j - weight[i]] + value[i]);
                }
            }
        }

        bw.write(Integer.toString(memo[N][K]));
        bw.close();
    }
}
