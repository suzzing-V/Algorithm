import java.util.*;
import java.io.*;

// 받을 수 있는 점수 범위는 1~20*10^5. 여기서 받을 수 있는 최대 점수를 구한다.
// mid를 받을 수 있는 최대 점수라고 하면, 각 그룹의 문제 합은 mid와 같거나 커야 한다. mid는 그룹 중 가장 작은 문제 합이기 때문이다.
// 조건에 맞는 그룹 수가 k보다 크면 그룹 수를 줄여야한다. 따라서 mid의 값을 높여야하므로 mid + 1~end를 탐색한다.
// 조건에 맞는 그룹 수가 k보다 작으면 그룹 수를 늘려야한다. 따라서 mid의 값을 낮춰야하므로 start~mid - 1를 탐색한다.
// 조건에 맞는 그룹 수가 k와 같으면 k개의 그룹을 만들 수 있는 더 큰 mid가 존재할 수 있기 때문에 mid + 1을 탐색한다.
// k개를 만들었는데 만들고 남은 게 있다면? mid + 1~end를 탐색하기 때문에 이걸 다 쓸 수 있는 값까지 탐색하게 된다.
// upper bound이다. k개의 그룹을 만들 수 있는 가장 큰 수를 찾으므로, start가 가장 먼저 k를 넘을 때 end는 k개의 그룹을 만들 수 있는 가장 큰 수를 가리키게 된다. 따라서 end가 정답.
public class Main {

    private static int[] score;
    private static int n;
    private static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        score = new int[n];
        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            score[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(binarySearch(1, 20 * (int)Math.pow(10, 5)));
    }

    private static int binarySearch(int start, int end) {
        if(start > end) return end;
        int mid = (start + end) / 2;

        int group = 0;
        int sum = 0;
        for(int i = 0; i < n;i++) {
            sum += score[i];
            if(sum >= mid) {
                group ++;
                sum = 0;
            }
        }

        if(group >= k) return binarySearch(mid + 1, end);
        return binarySearch(start, mid - 1);
    }
}
