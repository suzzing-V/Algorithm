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

        int i, j, k;
        for(int a = 0; a < M; a++) {
            String[] ijk = new String[3];
            ijk = bf.readLine().split(" ");
            i = Integer.parseInt(ijk[0]);
            j = Integer.parseInt(ijk[1]);
            k = Integer.parseInt(ijk[2]);
            
            System.out.println(i + " " + j + " " + k);
            for(int b = i; b <= j; b++) {
                basket[b] = k;
            }
        }

        for(int a = 1; a <= N; a++) {
            bw.write(basket[a] + " ");
        }
        bw.close();
    }
}
