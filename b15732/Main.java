import java.util.*;
import java.io.*;

// 시간 복잡도: 6 * 10^4
// mid번째 상자 이전에 들어있는 도토리 수가 d보다 크면 mid 늘리고, 작으면 줄여야한다.
// 가장 먼저 d가 되는 mid를 구해야 하므로 lower bound. -> why? 가장 먼저 d가 되는 상자 위치가 딱 d번째 도토리가 들어가는 상자이기 때문이다. 그 후의 상자를 마지막 상자라고 가정해도 이전 도토리 수가 d일 수 있지만, 그 상자에 들어가지는 않는다.
public class Main {

    private static int n;
    private static int k;
    private static long d;
    private static int[][] rules;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        d = Long.parseLong(st.nextToken());
        rules = new int[k][3];
        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(bf.readLine());
            rules[i][0] = Integer.parseInt(st.nextToken());
            rules[i][1] = Integer.parseInt(st.nextToken());
            rules[i][2] = Integer.parseInt(st.nextToken());
        }

        System.out.println(binarySearch(1, n));
    }

    private static int binarySearch(int start, int end) {
        if(start >= end) return start;

        int mid = (start + end) / 2;
        long before_mid = 0;
        for(int i = 0; i < k; i++) {
            if(mid < rules[i][0]) continue;
            before_mid += (Math.min(mid, rules[i][1]) - rules[i][0]) / rules[i][2] + 1;
        }

        if(before_mid < d) return binarySearch(mid + 1, end);
        return binarySearch(start, mid);
    }

}
