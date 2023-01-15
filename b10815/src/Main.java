import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());
        
        String[] sangStr = new String[n];
        int[] sang = new int[n];
        sangStr = bf.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            sang[i] = Integer.parseInt(sangStr[i]);
        }

        int m = Integer.parseInt(bf.readLine());
        String[] cardsStr = new String[m];
        int[] cards = new int[m];
        cardsStr = bf.readLine().split(" ");
        for(int i = 0; i < m; i++) {
            cards[i] = Integer.parseInt(cardsStr[i]);
        }
        
    }
}
