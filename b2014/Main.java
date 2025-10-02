import java.util.*;
import java.io.*;

// 시간복잡도: 10^7 * 7
// 곱한 결과에 또 곱하면 소수들을 곱한 값이다.

public class Main {

    private static PriorityQueue<Long> pq = new PriorityQueue<>();
    private static int n;
    private static int k;
    private static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        arr = new long[k];

        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            pq.add(arr[i]);
        }

        int cnt = 0;
        long answer = 0;
        while(!pq.isEmpty()) {
            long curr = pq.remove();

            cnt ++;
            if(cnt == n) {
                answer = curr;
                break;
            }

            // 중복 피하기 위해 무조건 내림차순으로만 곱한다.
            for(int i = 0; i < k; i ++) {
                // 결과가 2^31 이상이면 넣지 않는다. 답은 2^31보다 작은 자연수
                if(curr * arr[i] >= Integer.MAX_VALUE) break;
                pq.add(curr * arr[i]);
                if(curr % arr[i] == 0) break;
            }
        }

        System.out.println(answer);
    }
}
