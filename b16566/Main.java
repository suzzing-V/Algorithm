import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int k;
    static List<Integer> cards = new ArrayList<>();
    static int[] roots;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        roots = new int[m + 1];
        for(int i = 0; i < m; i ++) {
            cards.add(Integer.parseInt(st.nextToken()));
            roots[i + 1] = i + 1;
        }
        Collections.sort(cards);


        st = new StringTokenizer(bf.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < k; i++) {
            int c = Integer.parseInt(st.nextToken());
            int min = binarySearch(0, cards.size() - 1, c); // 민수가 낸 카드보다 큰 카드 중 가장 작은 카드의 인덱스
            int index = find(min);
            sb.append(cards.get(index) + "\n");
            union(index, index + 1); // 다음에 이 인덱스를 선택할 경우 index + 1의 루트 값 선택
        }

        bw.write(sb.toString());
        bw.close();
    }

    private static int binarySearch(int left, int right, int target) {
        if(left == right) return left;

        int mid = (left + right) / 2;
        if(cards.get(mid) <= target) {
            return binarySearch(mid + 1, right, target);
        }
        return binarySearch(left, mid, target);
    }

    private static int find(int x) {
        if(roots[x] == x) return x;

        return roots[x] = find(roots[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a < b) {
            roots[a] = b;
        } else {
            roots[b] = a;
        }
    }
}
