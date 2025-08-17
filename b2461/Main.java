import java.util.*;
import java.io.*;

// 최솟값과 최댓값을 제외하고 중간의 값들은 상관이 없다. 따라서 최솟값 or 최댓값만 변화시키며 차이를 확인하면 된다.
// 시간 복잡도: 1000 * 3000
public class Main {

    private static int n;
    private static int m;
    private static int[][] capacities;
    private static int[] pointers; // 각 반의 현재 포인터 위치
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        capacities = new int[n][m];
        pointers = new int[n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < m; j++) {
                capacities[i][j] = Integer.parseInt(st.nextToken());
            }
            // 투포인터를 위한 정렬
            Arrays.sort(capacities[i]);
        }


        // 최솟값을 증가시키며 확인
        while(true) {
            int max = 0;
            int min = Integer.MAX_VALUE;
            int min_class = 0;
            // 현재 위치에서 최솟값, 최댓값 구해서 answer 갱신
            for(int i = 0; i < n; i++) {
                int class_idx = pointers[i];
                max = Math.max(max, capacities[i][class_idx]);
                if(min > capacities[i][class_idx]) {
                    min = capacities[i][class_idx];
                    min_class = i;
                }
            }
            answer = Math.min(answer, max - min);

            // 현재 최솟값인 반의 포인터를 오른쪽으로 옮긴다.
            pointers[min_class] ++;
            // 포인터가 인덱스의 범위를 넘으면 더이상 탐색할 게 없다는 의미이므로 멈춘다.
            if(pointers[min_class] == m) break;
        }

        System.out.println(answer);
    }
}
