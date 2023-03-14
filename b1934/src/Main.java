import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(bf.readLine());
        
        for(int i = 0; i < t; i++) {
            String[] ab = new String[2];
            ab = bf.readLine().split(" ");
            int a = Integer.parseInt(ab[0]), b = Integer.parseInt(ab[1]);
            int min = a < b ? a : b;
            int mcm = 1;
            int d = 2;
            while(d <= min) {
                if(a % d == 0 && b % d == 0) {
                    mcm *= d;
                    a /= d;
                    b /= d;
                } else {
                    d++;
                }
            }
            bw.write(mcm * a * b + "\n");
        }
        bw.close();
    }
}
