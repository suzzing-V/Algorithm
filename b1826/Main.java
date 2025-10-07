import java.util.*;
import java.io.*;

// 시간복잡도: 10^6 * 5
// 하나씩 가면서 기름이 다 떨어지면 지나온 주유소 중 하나에서 주유한다.
public class Main {

    private static int[] oils = new int[(int)Math.pow(10, 6) + 1];
    private static int n;
    private static int l;
    private static int p;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        for(int i =  0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            oils[a] = b;
        }

        StringTokenizer st = new StringTokenizer(bf.readLine());
        l = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        int pos = 1;
        int oil = p;
        int cnt = 0;
        PriorityQueue<Integer> pass = new PriorityQueue<>((o1, o2) -> o2 - o1);
        while(true) {
            oil --;
            // 만약 현재 위치에 주유소 있으면 추가
            if(oils[pos] != 0) {
                pass.add(oils[pos]);
            }
            // 도착지 아닌데 oil 다 썼으면 충전
            if(pos != l && oil == 0) {
                // 충전할 수 있는 주유소가 없으면 -1 리턴
                if(pass.isEmpty()) {
                    cnt = -1;
                    break;
                } else {
                    oil += pass.remove();
                    cnt ++;
                }
            }

            pos ++;
            if(pos == l) {
                // 끝에 도착했으면 리턴
                break;
            }
        }

        System.out.println(cnt);
    }
}
