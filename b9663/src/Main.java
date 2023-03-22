import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());
        boolean[][] chess = new boolean[n][n];

        Queen(chess, 0, n);
        bw.write(Integer.toString(count));
        bw.close();
    }

    public static void Queen(boolean[][] chess, int i, int n) {
        if(i == n) {
            //System.out.println("완성");
            count++;
            return ;
        }

        for(int j = 0; j < n; j++) {
            if(!isDiagonal(chess, n, i, j) && !isColumn(chess, n, i, j)) {
                //System.out.println(i + " " + j + "넣음");
                chess[i][j] = true;
                Queen(chess, i + 1, n);
                chess[i][j] = false;
            }
        }
    }

    public static boolean isDiagonal(boolean[][] chess, int n, int i, int j) {
        int a = i - 1;
        int b = j - 1;
        while(a >= 0 && b >= 0) {
            if(chess[a][b] == true) {
                return true;
            }
            a--; b--;
        }

        a = i - 1; b = j + 1;
        while(a >= 0 && b < n) {
            if(chess[a][b] == true) {
                return true;
            }
            a--; b++;
        }
        
        return false;
    }

    public static boolean isColumn(boolean[][] chess, int n, int i, int j) {
        while(i - 1 >= 0) {
            if(chess[i - 1][j] == true) {
                return true;
            }
            i--;
        }
        return false;
    }
}
