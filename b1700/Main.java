import java.io.*;
import java.util.*;

// 가장 나중에 등장하는 플러그를 뽑아야한다.
// 시간복잡도: 100 * 100 + 100 * 100
public class Main {

    private static int n;
    private static int k;
    private static int[] arr;
    private static int[][] appeared;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[k];
        appeared = new int[k +1][k];
        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            // 등장하지 않는 건 가장 큰 우선순위이므로 최댓값으로 초기화
            Arrays.fill(appeared[i + 1], 200);
        }

        // 각 위치에서 각 숫자들이 가장 먼저 등장하는 위치 저장
        for(int i = k - 2; i >= 0; i --) {
            // 다음 위치에서의 정보에
            for(int j = 1; j <= k; j++) {
                appeared[j][i] = appeared[j][i + 1];
            }

            // 다음 요소의 정보 갱신
            appeared[arr[i + 1]][i] = i + 1;
        }

        int cnt = 0;
        // 플러그 꽉 찼을 경우 그 위치에서 가장 나중에 등장하는 플러그 뽑기
        Map<Integer, Integer> plug = new HashMap<>();
        for(int i = 0; i < k; i ++) {
            // 이미 꼽혀 있으면 넘어가기
            if(plug.get(arr[i]) != null) {
                continue;
            }

            // 플러그 꽉 안찼을 경우 그냥 꼽기
            if(plug.size() < n) {
                plug.put(arr[i], 0);
            // 플러그 꽉 찼을 경우 교체
            } else {
                int later = -1;
                int device = -1;
                for(int key : plug.keySet()) {
                    // 현재 저장된 가장 나중에 등장하는 위치보다 더 나중에 등장하면 갱신
                    if(later < appeared[key][i]) {
                        later = appeared[key][i];
                        device = key;
                    }
                }

                cnt ++;

                // 가장 나중에 등장하는 기기의 플러그 뽑고 현재 기기 꼽기
                plug.remove(device);
                plug.put(arr[i], 0);
            }
        }

        System.out.println(cnt);
    }
}
