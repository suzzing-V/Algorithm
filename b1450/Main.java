import java.io.*;
import java.util.*;


public class Main {

    static int n;
    static long c;
    static List<Long> sum1 = new ArrayList<>();
    static List<Long> sum2 = new ArrayList<>();
    static long[] w;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Long.parseLong(st.nextToken());
        w = new long[n];
        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            w[i] = Long.parseLong(st.nextToken());
        }

        // 물건들 반으로 나눠서 모든 경우로 물건 담았을 때 무게 합 구하기
        dfs(sum1, 0, 0, n / 2 - 1);
        dfs(sum2, 0, n / 2, n - 1);

        // sum2에 대해 이분탐색 위해 정렬
        Collections.sort(sum2);

        int cnt = 0;
        for(int i = 0; i < sum1.size(); i++) {
            // c - sum1[i]의 용량 더 담을 수 있다. sum2의 요소 중 c - sum1[i]보다 작거나 같은 합 다 넣을 수 있다. 따라서 처음으로 c - sum1[i]를 초과하는 sum2의 요소의 인덱스가 sum1[i]일 때 담을 수 있는 sum2의 경우의 수다. -> upper bound
            cnt += binarySearch(0, sum2.size() - 1, c - sum1.get(i));
        }

        bw.write(String.valueOf(cnt));
        bw.close();
    }

    private static void dfs(List<Long> arr, long sum, int idx, int end) {
        if(idx > end) {
            arr.add(sum);
            return;
        }

        dfs(arr, sum, idx + 1, end);
        dfs(arr, sum + w[idx], idx + 1, end);
    }

    private static int binarySearch(int left, int right, long target) {
        if(left >= right) {
            if(sum2.get(left) <= target) return left + 1; // 초과하는 값이어야 하는데 배열의 끝이라 같거나 작을 경우 1 더해준다.
            return left;
        }

        int mid = (left + right) / 2;
        if(sum2.get(mid) <= target) {
            return binarySearch(mid + 1, right, target);
        } else {
            return binarySearch(left, mid, target);
        }
    }
}
