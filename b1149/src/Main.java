import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());
        int[][] house = new int[n][3];
        for(int i = 0; i < n; i++) {
            String[] line = new String[3];
            line = bf.readLine().split(" ");
            for(int j = 0; j < 3; j++) {
                house[i][j] = Integer.parseInt(line[j]);
            }
        }
        int[][] memo = new int[n][3];
        memo[0][0] = house[0][0]; memo[0][1] = house[0][1]; memo[0][2] = house[0][2];
        for(int i = 1; i < n; i++) {
            memo[i][0] = Math.min(memo[i - 1][1], memo[i - 1][2]) + house[i][0];
            memo[i][1] = Math.min(memo[i - 1][0], memo[i - 1][2]) + house[i][1];
            memo[i][2] = Math.min(memo[i - 1][0], memo[i - 1][1]) + house[i][2];
        }

        int min = memo[n - 1][0] < memo[n - 1][1] ? memo[n - 1][0] : memo[n - 1][1];
        min = memo[n - 1][2] < min ? memo[n - 1][2] : min;
        bw.write(Integer.toString(min));
        bw.close();
    }
}
