import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] scores;
    static Map<Integer, Integer> cardsByValue = new HashMap<>(); // <카드번호, 플레이어>
    static Map<Integer, Integer> cardsByIdx = new HashMap<>(); // <플레이어, 카드번호>

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        scores = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            int value = Integer.parseInt(st.nextToken());
            cardsByValue.put(value, i);
            cardsByIdx.put(i, value);
        }

        play();

        for(int i = 0; i < n; i++) {
            bw.write(scores[i] + " ");
        }
        bw.close();
    }

    private static void play() {
        for(int i = 0; i < n; i++) {
            if(cardsByIdx.get(i) != 1 && cardsByValue.get(1) != null) { // 1의 경우 모든 수를 나눌 수 있으므로 1번 카드와 겨루면 패배 처리
                scores[i] --;
                scores[cardsByValue.get(1)] ++;
            }

            int card = cardsByIdx.get(i);
            for(int j = 2; j * j <= card; j ++) { // 현재 카드의 약수에 해당하는 수가 있으면 그 약수 카드 가진 플레이어 승리, 현재 카드는 패배 처리
                if(card % j != 0) continue;
//                System.out.println(cardsByValue.get(j) + " " + cardsByValue.get(card / j));
                if(cardsByValue.get(j) != null) {
                    scores[i] --;
                    scores[cardsByValue.get(j)] ++;
                }

                if(j * j == card) continue; // 두 약수가 똑같은 경우 한번만 게임(중복되는 카드 없으므로)

                if(cardsByValue.get(card / j) != null) {
                    scores[i] --;
                    scores[cardsByValue.get(card / j)] ++;
                }
            }
        }
    }
}
