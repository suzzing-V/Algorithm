import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());
        
        String[] sangArr = bf.readLine().split(" ");
        int[] minusNum = new int[10000001];
        int[] plusNum = new int[10000001];
        Arrays.fill(minusNum, 0);
        Arrays.fill(plusNum, 0);
        for(int i = 0; i < n; i++) {
            int sang = Integer.parseInt(sangArr[i]);
            if(sang < 0) {
                minusNum[-sang] ++;
            } else {
                plusNum[sang] ++;
            }
        }

        int m = Integer.parseInt(bf.readLine());
        String[] cardArr = bf.readLine().split(" ");
        for(String cardStr : cardArr) {
            int card = Integer.parseInt(cardStr);
            if(card >= 0) {
                bw.write(String.valueOf(plusNum[card]) + " ");
            } else {
                bw.write(String.valueOf(minusNum[-card]) + " ");
            }
        }
        bw.close();
    }
}