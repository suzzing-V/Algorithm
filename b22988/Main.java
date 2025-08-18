import java.io.*;
import java.util.*;

// 가장 적은 병과 가장 많은 병을 합해야 이득. -> 투포인터
// 아무리 최악이어도 3병으로는 무조건 xml병 만들 수 있다.

public class Main {

    private static int n;
    private static long x;
    private static long[] tongs;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        x = Long.parseLong(st.nextToken());
        tongs = new long[n];

        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            tongs[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(tongs);

        int answer = 0;
        int rest = 0;
        int left = 0;
        int right = n - 1;
        // x보다 큰 거 제외. x와 같거나 큰 건 안 합치는 게 이득
        while(right >= 0 && tongs[right] >= x) {
            right --;
            answer ++;
        }

        while(left < right) {
            // 두 값을 합친 게 x와 같거나 크면 교환
            if((double)tongs[left] + (double)tongs[right] + (double)x / 2 >= (double)x) {
                answer ++;
                left ++;
                right --;
            } else { // 두 값을 합친 게 x보다 작으면 left를 나머지로 뺴두기
                left ++;
                rest ++;
            }
            // 마자막으로 가리키는 게 같다면 이건 남는 병
            if(left == right) {
                rest ++;
                break;
            }
        }

        System.out.println(answer + rest / 3);
    }
}
