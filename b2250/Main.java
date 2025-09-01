import java.util.*;
import java.io.*;

// 시간복잡도: O(n)
public class Main {

    private static int n;
    private static int[] left;
    private static int[] right;
    private static int[] min;
    private static int[] max;
    private static boolean[] hasParent;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        left = new int[n + 1];
        right = new int[n + 1];
        min = new int[n + 1];
        max = new int[n + 1];
        hasParent = new boolean[n + 1];
        Arrays.fill(min, 20000);
        Arrays.fill(max, -1);
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int curr = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            left[curr] = l;
            right[curr] = r;
            if(l != -1) hasParent[l] = true;
            if(r != -1) hasParent[r] = true;
        }

        // 루트노드 찾기
        int root = 0;
        for(int i = 1; i <= n; i++) {
            if(!hasParent[i]) {
                root = i;
                break;
            }
        }

        // 각 레벨에서의 열 최솟값과 최댓값 구하기
        dfs(root, 0, 1);

        int max_level = 1;
        int max_gap = 0;
        for(int i = 1; i < min.length; i++) {
            if(max[i] - min[i] > max_gap) {
                max_gap = max[i] - min[i];
                max_level = i;
            }
        }

        System.out.println(max_level);
        System.out.println(max_gap + 1);
    }

    private static int dfs(int curr, int column, int level) {
        // 리프노드일 경우 공간 하나 차지하고 정보 저장하고 반환
        if(left[curr] == -1 && right[curr] == -1) {
            min[level] = Math.min(min[level], column);
            max[level] = Math.max(max[level], column);
            return column + 1;
        }

        int next = column;
        // 왼쪽 트리가 공간 다 쓴 후 다음 공간
        if(left[curr] != -1) next = dfs(left[curr], column, level + 1);
        // curr의 열 정보 저장, 공간 차지
        min[level] = Math.min(min[level], next);
        max[level] = Math.max(max[level], next);
        next ++;
        // 오른쪽 트리가 공간 다 쓴 후 다음 공간
        if(right[curr] != -1) next = dfs(right[curr], next, level + 1);

        return next;
    }
}
