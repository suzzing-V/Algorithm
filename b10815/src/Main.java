import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bf.readLine());
        String sangStr = bf.readLine();
        String[] sang = sangStr.split(" ");
        HashMap<Integer, Integer> sMap = new HashMap<>(n);
        for(int i = 0; i < n; i++) {
            sMap.put(i, Integer.parseInt(sang[i]));
        }

        int m = Integer.parseInt(bf.readLine());
        String cardStr = bf.readLine();
        String[] card = cardStr.split(" ");
        int[] cards = new int[m];
        for(int i = 0; i < m; i++) {
            cards[i] = Integer.parseInt(card[i]);
        }
        
        for(int c : cards) {
            if(sMap.containsValue(c)) {
                bw.write("1 ");
            } else {
                bw.write("0 ");
            }
        }
        bw.close();
    }
}
