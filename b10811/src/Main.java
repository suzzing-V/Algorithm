import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] nm = new String[2];
        nm = bf.readLine().split(" ");
        int N = Integer.parseInt(nm[0]);
        int M = Integer.parseInt(nm[1]);

        int[] basket = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            basket[i] = i;
        }
        
        int i, j, tmp, c;
        for(int a = 1; a <= M; a++) {
            String[] ij = new String[2];
            ij = bf.readLine().split(" ");
            i = Integer.parseInt(ij[0]);
            j = Integer.parseInt(ij[1]);
            c = j;
            for(int b = i; b <= (i + j) / 2; b++) {
                tmp = basket[b];
                basket[b] = basket[c];
                basket[c] = tmp;
                c--;
            }
        }

        for(int a = 1; a <= N; a++) {
            bw.write(Integer.toString(basket[a]) + " ");
        }
        bw.close();
    }
}
