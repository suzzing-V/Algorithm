import java.util.*;
import java.io.*;

// 시간복잡도: 10^6
// 오일러 피 함수: n과 서로소인 1~n까지의 정수 개수
// 오일러(n) = (n의 소인수1)^(n의소인수1개수)*(1- 1/(n의 소인수1)) * (n의 소인수2)^(n의소인수2개수)*(1 - 1/(n의소인수2))*...
public class Main {

    private static long n;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Long.parseLong(bf.readLine());

        long result = n;
        for(int i = 2; i <= Math.sqrt(n); i ++) {
            // i가 n의 서로소면 result에 (1 - 1/i) 곱하기
            if(n % i == 0) {
                result -= result / i;

                // n이 가지고 있는 i 다 빼주기. 안 빼주면 만약에 i가 2라면 i가 4일 때 또 처리를 해준다. 2에 관련된 건 여기서 다 처리하고 끝내야 한다.
                while(n % i == 0) {
                    n /= i;
                }
            }
        }

        // 만약에 n 자체가 소수였다면 자기 자신에 대해 오일러 피함수. 소수가 아닐 경우 자기 자신에 대해 안 하는 이유는 자기 자신이 소인수가 될 수 없기 때문이다.
    // 소수가 아니라면 위의 과정을 거쳤을 때 n에서 소인수를 다 뺐기 때문에 n이 1이어야 한다. 1이 아니라면 위의 과정을 거치지 않은 것이므로 소수
        if(n != 1) {
            result -= result / n;
        }
        System.out.println(result);
    }
}
