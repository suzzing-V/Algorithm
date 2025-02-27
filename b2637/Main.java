import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static List<Integer>[] conn; // 위상정렬 연결 배열
    static Map<Integer, Integer>[] basic; // 부품 별 필요 부품과 개수
    static int[] degree; // 위상정렬 차수
    static Map<Integer, Integer>[] result; // 각 부품별 만들기 위해 필요한 기본 부품과 개수

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        m = Integer.parseInt(bf.readLine());
        conn = new ArrayList[n + 1];
        degree = new int[n + 1];
        result = new HashMap[n + 1];
        basic = new HashMap[n + 1];
        for(int i = 1; i <= n; i++) {
            basic[i] = new HashMap<>();
            conn[i] = new ArrayList<>();
            result[i] = new HashMap<>();
        }

        for(int i = 1; i <= m; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int target = Integer.parseInt(st.nextToken());
            int need = Integer.parseInt(st.nextToken());
            int needCnt = Integer.parseInt(st.nextToken());
            basic[target].put(need, needCnt);
            conn[need].add(target);
            degree[target] ++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1; i <= n; i++) {
            if(degree[i] == 0) {
                queue.add(i);
            }
        }

        while(!queue.isEmpty()) {
            int curr = queue.remove();
            for(int next : conn[curr]) {
                int currCnt = basic[next].get(curr); // 다음 부품이 필요로 하는 현재 부품 개수
                if(result[curr].isEmpty()) { // 현재 기본부품일 경우
                    result[next].put(curr, currCnt);
                } else { // 중간 부품일 경우
                    for(int key : result[curr].keySet()) { // 현재 부품을 만들기 위한 기본 부품을 순회하면서 다음 기본 부품 수에 현재 기본 부품 수 * 다음 부품이 필요로 하는 현재 부품 개수를 더해줌
                        int nextCnt = result[next].getOrDefault(key, 0);
                        result[next].put(key, nextCnt + currCnt * result[curr].get(key));
                    }
                }
                degree[next] --;
                if(degree[next] == 0) queue.add(next);
            }
        }

        int[] arr = new int[n + 1];
        for(int key : result[n].keySet()) {
            arr[key] = result[n].get(key);
        }
        for(int i = 1; i <= n; i++) {
            if(arr[i] != 0) {
                bw.write(i + " " + arr[i] + "\n");
            }
        }
        bw.close();
    }
}
