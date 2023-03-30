import java.io.*;

public class Main {
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());
        int[] tile = new int[n];
        getTileCount(tile, n, 0);
        bw.write(Integer.toString(count % 15746));
        bw.close();
    }

    public static void getTileCount(int[] tile, int n, int t) {
        if(t == n) {
            count ++;
            if(count == 15746) {
                count = 0;
            }
            return;
        }

        if(t != n - 1) {
            tile[t] = 0;
            getTileCount(tile, n, t + 2);
        }
        tile[t] = 1;
        getTileCount(tile, n, t + 1);
    }
}
