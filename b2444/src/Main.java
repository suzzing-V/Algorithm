import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(bf.readLine());
        
        int start = N;
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= 2 * N - 1; j++) {
                if(j >= start && j <= start + (i - 1) * 2) bw.write("*");
                else bw.write(" ");
            }
            bw.write("\n");
            start--;
        }

        start+=2;
        for(int i = N - 1; i >= 1; i--) {
            for(int j = 1; j <= 2*N - 1; j++) {
                if(j >= start && j <= start + (i - 1) * 2) bw.write("*");
                else bw.write(" ");
            }
            bw.write("\n");
            start++;
        }
        bw.close();
    }
}
