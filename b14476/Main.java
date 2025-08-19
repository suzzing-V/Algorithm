import java.util.*;
import java.io.*;

// 시간 복잡도: O(n)
// 유클리드 호제법 이용. 빠르게 최대공약수 구할 수 있다.
// 0~i까지의 최대공약수와 i~n - 1까지의 최대공약수 구한 후, k를 차례대로 지정해보면서 남은 수들의 최대공약수를 구한다.
// 어떤수들의 최대공약수와 또 다른 수들의 최대공약수의 최대공약수는 모든 수의 최대공약수이다.
public class Main {

    private static int[] left_gcd;
    private static int[] right_gcd;
    private static int n;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n];
        left_gcd = new int[n];
        right_gcd = new int[n];
        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // left_gcd: 0~i까지의 최대공약수
        // right_gcd: i ~ n - 1까지의 최대공약수
        left_gcd[0] = arr[0];
        right_gcd[n - 1] = arr[n - 1];
        // 지금까지의 최대공약수와 현재 값의 최대공약수는 현재까지의 최대공약수이다.
        // 지금까지 중에 나누어지는 것만 뽑아왔기 때문에 이것만으로 구할 수 있다.
        for(int i = 1; i < n; i++) {
            left_gcd[i] = gcd(Math.max(left_gcd[i - 1], arr[i]), Math.min(left_gcd[i - 1], arr[i]));
        }
        for(int i = n - 2; i >= 0; i--) {
            right_gcd[i] = gcd(Math.max(right_gcd[i + 1], arr[i]), Math.min(right_gcd[i + 1], arr[i]));
        }

        // 차례대로 k를 지정해보면서 가장 큰 최대공약수와 k구하기
        int max = 0;
        int k = -1;
        for(int i = 0; i < n; i++) {
            int gcd = 0;
            int tmp_k = arr[i];
            if(i == 0) {
                gcd = right_gcd[1];
            } else if(i == n - 1) {
                gcd = left_gcd[n - 2];
            } else {
                gcd = gcd(Math.max(left_gcd[i - 1], right_gcd[i + 1]), Math.min(left_gcd[i - 1], right_gcd[i + 1]));
            }

            // k의 약수이면 안된다
            if(tmp_k % gcd == 0) continue;
            if(gcd > max) {
                max = gcd;
                k = arr[i];
            }
        }

        if(k == -1) System.out.println("-1");
        else System.out.println(max + " " + k);
    }

    // 유클리드 호제법: a와 b로 나눈 나머지를 r이라고 할 때, a와 b의 최대공약수와 b와 r의 최대공약수는 같다. b가 0이 될 때 a값이 최대공약수이다.
    private static int gcd(int a, int b) {
        if(b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }
}
