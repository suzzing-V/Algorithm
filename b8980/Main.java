import java.io.*;
import java.util.*;

// 시간복잡도: 10^4 * 4 * 10^3

public class Main {

    private static int n;
    private static int c;
    private static int m;
    // 해당 마을 위치에서 남은 택배 용량
    private static int[] rest;
    private static Delivery[] deliveries;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        rest = new int[n];
        Arrays.fill(rest, c);
        m = Integer.parseInt(bf.readLine());
        deliveries = new Delivery[m];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());

            deliveries[i] = new Delivery(start, end, cnt);
        }
        // 도착 마을이 가장 가까운 순으로 정렬. 택배 빨리 내려야 그만큼 더 담을 수 있으므로 빨리 내릴수록 이득
        Arrays.sort(deliveries, (o1, o2) -> o1.end - o2.end);

        int result = 0;
        for(int i = 0; i < m; i++) {
            Delivery curr = deliveries[i];

            // 이 택배 중 담을 수 있는 택배 개수 구하기. 시작마을과 도착 마을 사이의 남은 택배 용량이 이 택배보다 커야한다. 작으면 그 남은 용량만큼밖에 못 담는다.
            // 도착 마을에서는 택배를 내리기 때문에 도착 마을에서 여유가 있을 필요는 없다.
            int min = curr.cnt;
            for(int a = curr.start; a < curr.end; a++) {
                min = Math.min(min, rest[a]);
            }

            // 담을 수량 정해졌으면 시작마을과 도착마을 사이의 택배 용량에서 빼주기
            for(int a = curr.start; a < curr.end; a++) {
                rest[a] -= min;
            }

            result += min;
        }

        System.out.println(result);
    }

    private static class Delivery {
        int start;
        int end;
        int cnt;

        Delivery(int start, int end, int cnt) {
            this.start = start;
            this.end = end;
            this.cnt = cnt;
        }
    }
}
