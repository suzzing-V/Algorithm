import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(bf.readLine());
        
        int blank = N - 1;
        int star = 1;
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= blank; j++) {
                bw.write(" ");
            }
            for(int j = 1; j <= star; j++) {
                bw.write("*");
            }
            bw.write("\n");
            blank--;
            star += 2;
        }

        blank = 1;
        star -= 4;
        for(int i = 1; i < N; i++) {
            for(int j = 1; j <= blank; j++) {
                bw.write(" ");
            }
            for(int j = 1; j <= star; j++) {
                bw.write("*");
            }
            bw.write("\n");
            blank++;
            star -= 2;
        }
        bw.close();
    }
}
