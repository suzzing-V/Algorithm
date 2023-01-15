import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

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
        
        int[] exist = new int[m];
        compareCards(sang, cards, n, m, exist); //정적 메소드에서는 정적 메소드만 사용 가능
        for(int i = 0; i < m; i++){
            bw.write(Integer.toString(exist[i]) + " ");
        }
        bw.close();
    }

    public static void compareCards(int[] sang, int[] cards, int n, int m, int[] exist) {
        Arrays.fill(exist, 0); //Arrays.fill(배열이름, 초기화할 수) : 채움

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(sang[i] == cards[j]) {
                    exist[j] = 1;
                }
            }
        }
    }
}
