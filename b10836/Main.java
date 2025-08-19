import java.util.*;
import java.io.*;

// 변화량을 연속으로 넣어야하는데, 일일히 더하면 (700 * 2 - 1) * 10 ^ 6 으로 시간 초과난다.
// 연속으로 넣는 것이므로, 구간을 표시하고, 나중에 한번에 누적합을 적용해 변화량을 구할 수 있다.
// 오른쪽 위로 갈수록 무조건 변화량이 더 크므로, 특정 지점에서 위에 있는 위치의 변화량을 가져오면 그게 제일 큰 변화량이다.
// 시간 복잡도: O(n)

public class Main {

    private static int m;
    private static int n;
    private static int[] var;
    private static int[][] house;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        var = new int[2 * m + 2];
        house = new int[m][m];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                house[i][j] = 1;
            }
        }

        // 각 위치의 변화량 구하기
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            int cnt0 = Integer.parseInt(st.nextToken());
            int cnt1 = Integer.parseInt(st.nextToken());
            int cnt2 = Integer.parseInt(st.nextToken());

            var[cnt0] ++;
            var[cnt0 + cnt1] --;
            var[cnt0 + cnt1] += 2;
            var[cnt0 + cnt1 + cnt2] -= 2;
        }

        for(int i = 1; i < var.length; i++) {
            var[i] += var[i - 1];
        }

        // 왼쪽 아래부터 오른쪽 위까지 변화량 적용하기
        for(int i = 0; i < m; i ++) {
            house[m - 1 - i][0] += var[i];
        }

        for(int i = m; i < 2 * m - 1; i++) {
            house[0][i - (m - 1)] += var[i];
        }

        // 1,1 부터 m, m까지 채워넣기. 무조건 위의 변화량이 제일 크므로 위의 변화량 적용한다.
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < m; j++) {
                house[i][j] = house[i - 1][j];
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                System.out.print(house[i][j] + " ");
            }
            System.out.println();
        }
    }
}
