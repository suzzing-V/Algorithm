
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] nmk = new String[3];
        nmk = bf.readLine().split(" ");
        int n = Integer.parseInt(nmk[0]);
        int m = Integer.parseInt(nmk[1]);
        int k = Integer.parseInt(nmk[2]);

        String[][] chess = new String[n + 1][m + 1];
        for(int i = 1; i <= n; i++) {
            String[] line = new String[m + 1];
            line = (" " + bf.readLine()).split("");
            chess[i] = line;
        }

        String[][] case1 = new String[n + 1][m + 1];
        String[][] case2 = new String[n + 1][m + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if((i % 2 == 1 && j % 2 == 1) || (i % 2 == 0 && j % 2 == 0)) {
                    case1[i][j] = "B";
                    case2[i][j] = "W";
                } else {
                    case1[i][j] = "W";
                    case2[i][j] = "B";
                }
            }
        }

        int[][] compare1 = new int[n + 1][m + 1];
        int[][] compare2 = new int[n + 1][m + 1];
        compareChess(compare1, case1, chess, n, m);
        compareChess(compare2, case2, chess, n, m);

        int result = Math.min(getAccumulateSum(compare1, k, n, m), getAccumulateSum(compare2, k, n, m));
        bw.write(Integer.toString(result));
        bw.close();
    }

    public static void compareChess(int[][] compare, String[][] cases, String[][] chess, int n, int m) {
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(cases[i][j].equals(chess[i][j])) { compare[i][j] = 0; }
                else { compare[i][j] = 1; }
            }
        }
    }

    public static int getAccumulateSum(int[][] compare, int k, int n, int m) {
        int min = 4000000;
        int[][] acc = new int[n + 1][m + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                acc[i][j] = acc[i][j - 1] + acc[i - 1][j] - acc[i - 1][j - 1] + compare[i][j];
            }
        }

        int sum = 0;
        for(int i = 1; i + (k - 1) <= n; i++) {
            for(int j = 1; j + (k - 1) <= m; j++) {
                sum = acc[i + (k - 1)][j + (k - 1)] - acc[i + (k - 1)][j - 1]
                    - acc[i - 1][j + (k - 1)] + acc[i - 1][j - 1];
                if(min > sum) { min = sum; }
            }
        }
        return min;
    }
}