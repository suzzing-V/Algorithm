import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nm = new String[2];
        nm = bf.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        String[] cardStr = new String[n];
        int[] card = new int[n];
        cardStr = bf.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            card[i] = Integer.parseInt(cardStr[i]);
        }

        int max = 0;
        for(int a = 0; a < n - 2; a++) {
            for(int b = a + 1; b < n - 1; b++) {
                for(int c = b + 1; c < n; c++) {
                    if(card[a] + card[b] + card[c] <= m && card[a] + card[b] + card[c] > max) {
                        max = card[a] + card[b] + card[c];
                    }
                }
            }
        }

        bw.write(Integer.toString(max));
        bw.close();
    }
}
