import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bf.readLine());
        StringTokenizer sangStr = new StringTokenizer(bf.readLine());
        HashMap<Integer, Integer> sMap = new HashMap<>(n);
        for(int i = 0; i < n; i++) {
            sMap.put(i, Integer.parseInt(sangStr.nextToken()));
        }

        int m = Integer.parseInt(bf.readLine());
        StringTokenizer cardsStr = new StringTokenizer(bf.readLine());
        int[] cards = new int[m];
        for(int i = 0; i < m; i++) {
            cards[i] = Integer.parseInt(cardsStr.nextToken());
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
