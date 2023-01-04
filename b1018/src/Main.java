import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] mnstr = new String[2];
        mnstr = bf.readLine().split(" ");

        int n = Integer.parseInt(mnstr[0]);
        int m = Integer.parseInt(mnstr[1]);
        String[][] chess = new String[n][m];
        for(int i = 0; i < n; i++) {
            String[] chessLine = new String[m];
            chessLine = bf.readLine().split("");
            for(int j = 0; j < m; j++) {
                chess[i][j] = chessLine[j];
            }
        }

        int count;
        int min = 64;
        for(int i = 0; i <= m - 8; i++) {
            for(int j = 0; j <= n - 8; j++) {
                count = 0;
                if(chess[i][j] == "b") {
                    for(int k = i; k <= i + 7; k+=2) {
                        for(int l = j; l <= j + 7; l+=2) {
                            if(chess[k][l] == "w") {
                                count++;
                            }
                        }
                    }

                    for(int k = i + 1; k <= i + 7; k+=2) {
                        for(int l = j + 1; l <= j + 7; l+=2) {
                            if(chess[k][l] == "w") {
                                count++;
                            }
                        }
                    }
                } else {

                }
            }
        }
    }
}
