import java.io.*;
import java.util.*;

// 시간복잡도: 8000
// 경유하는 도로 중 현재 도시들을 오가는 최소시간보다 작은 경우 있으면 모순된 것이다. 무조건 최소시간이 저장돼있기 때문이다.
// 다른 도로 써서 두 도시 이을 수 있으면 현재 두 도시를 잇는 직접 연결 도로 필요 없다.
public class Main {

    private static int n;
    private static int[][] min;
    private static int[][] selected;
    private static int sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        min = new int[n + 1][n + 1];
        selected = new int[n + 1][n + 1];

        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 1; j <= n; j ++) {
                min[i][j] = Integer.parseInt(st.nextToken());
                selected[i][j] = min[i][j];
            }
        }
        // 직접 연결 도로가 아닌 다른 도시를 경유하는 방법으로 두 도시를 연결할 수 있으면 두 도시를 연결하는 직접 도로는 제외한다.
        // 만약에 현재 저장돼 있는 i도시에서 j도시로 다른 도시 경유해서 가는 경로가 i도시에서 j도시로 가는 최소 경로보다 더 작은 경우가 있으면 이건 모순된 정보이다.
        // 지금 이 표에는 최소경로가 저장돼있다고 명시해놨기 때문이다.
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    for(int k = 1; k <= n; k++) {

                        // 같은 도시 잇는 경로도 확인할 경우 i와 j의 직접 연결 도로를 확인하는 것과 같으므로 배제한다.
                    if(i != k && j != k && min[i][k] + min[k][j] == min[i][j]) {
                        selected[i][j] = 0;
                    }
                    if(min[i][k] + min[k][j] < min[i][j]) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }
            for(int i = 1; i <= n; i++) {
                for(int j = i + 1; j <= n; j++) {
                    sum += selected[i][j];
                }
            }
        System.out.println(sum);
    }

    private static class Node implements Comparable<Node> {
        int a;
        int b;
        int t;

        Node(int a, int b, int t) {
            this.a = a;
            this.b = b;
            this.t = t;
        }

        @Override
        public int compareTo(Node n) {
            return this.t - n.t;
        }
    }
}
