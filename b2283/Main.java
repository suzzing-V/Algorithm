import java.util.*;
import java.io.*;

// 양쪽 끝점이 관건 -> 투포인터 고려
// 구간의 길이가 가장 작을 필요는 없다. a가 무조건 제일 작아야하므로 탐색 시작점을 0으로 잡아야한다.
public class Main {

    private static int[] starts;
    private static int[] ends;
    private static int n;
    private static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        starts = new int[(int)Math.pow(10, 6) + 1];
        ends = new int[(int)Math.pow(10, 6) + 1];

        // 시작점이 i, 끝점이 i인 요소들의 수 저장
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            starts[start] ++;
            ends[end] ++;
        }

        int left = 0;
        int right = 0;
        // 왼쪽 끝에 걸치는 선분 수
        int left_cnt = starts[0];
        // 오른쪽 끝에 걸치는 선분 수
        int right_cnt = starts[0];
        // 현재 구간에서의 합
        int sum = 0;

        while(true) {
            // sum이 k보다 작으면 right를 늘려준다. right_cnt는 오른쪽이 늘어날 때마다 더해줘야하는 값이다.
            // 만약에 늘린 right가 어떤 선분의 시작점이라면, 다음에 right를 늘릴 때에는 이 선분도 포함해서 sum을 늘려줘야 한다.
            // 만약에 늘린 right가 어떤 선분의 끝점이라면, 다음에 right를 늘릴 때에는 이 선분은 포함하지 말아야 한다.
            // 따라서 right를 늘린다음 right_cnt를 조정해준다.
            // left도 마찬가지.
            if(sum < k) {
                sum += right_cnt;
                right ++;
                if(right > Math.pow(10, 6)) break;
                right_cnt += starts[right];
                right_cnt -= ends[right];
            } else if(sum > k) {
                sum -= left_cnt;
                left ++;
                left_cnt -= ends[left];
                left_cnt += starts[left];
            } else {
                System.out.println(left + " " + right);
                return;
            }
        }

        // 끝까지 못 찾았을 경우
        System.out.println("0 0");
    }
}
