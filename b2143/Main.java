import java.io.*;
import java.util.*;

public class Main {
    static int t;
    static int n;
    static int m;
    static int[] arrA;
    static int[] arrB;
    static Map<Long, Long> totalCountA = new HashMap<>();
    static Map<Long, Long> totalCountB = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(bf.readLine());
        n = Integer.parseInt(bf.readLine());
        arrA = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            arrA[i] = Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(bf.readLine());
        arrB = new int[m];
        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < m; i++) {
            arrB[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < n; i++) {
            dfs(i, 0, arrA, n, totalCountA);
        }
        for(int i = 0; i < m; i++) {
            dfs(i, 0, arrB, m, totalCountB);
        }

        long result = 0;
        for(Long key : totalCountA.keySet()) {
            Long countB = totalCountB.get(t - key);
            if(countB == null) {
                continue;
            }
            result += totalCountA.get(key) * countB;
        }
        System.out.println(result);
    }

    public static void dfs(int index, long sum, int[] arr, int length, Map<Long, Long> totalCount) {
        if (index >= length) {
            return;
        }

        sum += arr[index];
        if(totalCount.get(sum) == null) {
            totalCount.put(sum, 1L);
        } else {
            totalCount.put(sum, totalCount.get(sum) + 1);
        }
        dfs(index + 1, sum, arr, length, totalCount);
    }
}
