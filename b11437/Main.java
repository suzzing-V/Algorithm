import java.util.*;
import java.io.*;

// 시간복잡도: 50000*10000
// 1초 == 3억까지 봐도 됨
public class Main {

    private static int n;
    private static int m;
    private static ArrayList<Integer>[] conn;
    private static int[] parent;
    private static int[] height;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        conn = new ArrayList[n + 1];
        parent = new int[n + 1];
        height = new int[n + 1];
        visited = new boolean[n + 1];
        for(int i = 1; i <= n; i++) {
            conn[i] = new ArrayList<>();
        }

        // 연결정보 담기
        for(int t = 0; t < n - 1; t ++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            conn[n1].add(n2);
            conn[n2].add(n1);
        }

        // 부모, 높이 정보 담기
        dfs(1, 1);

        m = Integer.parseInt(bf.readLine());
        for(int t = 0; t < m; t++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            // 두 노드의 높이가 다르다면 같을 때까지 깊은 높이의 노드의 부모를 구한다.
            if(height[n1] < height[n2]) {
                while(height[n1] < height[n2]) {
                    n2 = parent[n2];
                }
            } else if(height[n2] < height[n1]) {
                while(height[n2] < height[n1]) {
                    n1 = parent[n1];
                }
            }

//            System.out.println(n1 + " " + n2);
            // 높이를 맞췄다면 부모가 같을 때까지 함께 올라간다.

            while(true) {
                if(n1 == n2) {
                    System.out.println(n1);
                    break;
                }
                n1 = parent[n1];
                n2 = parent[n2];
            }
        }
    }

    private static void dfs(int curr, int h) {
        visited[curr] = true;
        for(int child : conn[curr]) {
            if(visited[child]) continue;
            parent[child] = curr;
            height[child] = h + 1;
            dfs(child, h + 1);
        }
    }
}
