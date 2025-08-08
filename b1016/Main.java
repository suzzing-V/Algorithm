import java.util.*;
import java.io.*;

// 범위 안의 수 중 2 * 2부터 max보다 작은 n * n까지의 수로 나눠지는 수를 표시한다. 범위 안의 수만 확인하면 되므로, 시간 복잡도는 10^6 * (10^6 * (1/2^2 + 1/3^3 + 1/4^4 ...))이다.
// 범위 안의 수 중 i*i로 나누어 떨어지는 가장 작은 수는 어떻게 구할까? min을 i*i로 나눠보고, 나머지가 0이면 min이고, 0이 아니면 몫 + 1 에 i*i를 곱한 수이다.
// i * i로 나누어지는 가장 작은 수부터 max와 작거나 같은 수까지 표시한다.
// 제곱수로 나누이지지 않는 수를 세어야하므로 isDivided가 false인 수를 센다.
// 공간복잡도를 고려해 범위 안의 수를 조정하여 최대 100만인 배열 안에서 해결할 수 있도록 한다.
public class Main {

    private static long min;
    private static long max;
    private static boolean[] isDivided;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        min = Long.parseLong(st.nextToken());
        max = Long.parseLong(st.nextToken());

        isDivided = new boolean[(int)(max - min) + 1];

        for(long i = 2; i * i <= max; i++) {
            // min~max에서 i*i로 나누어 떨어지는 가장 작은 수 구하기
            long pow = i * i;
            long start = min % (i * i) == 0 ? min : (min / (i * i) + 1) * (i * i);
            for(long j = start; j <= max; j+= pow) {
                isDivided[(int)(j - min)] = true;
            }
        }

        int cnt = 0;
        for(int i = 0; i < isDivided.length; i++) {
            if(!isDivided[i]) cnt ++;
        }

        System.out.println(cnt);
    }
}
