import java.io.*;
import java.util.*;

public class Main {

    static long n;
    static long k;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        k = Integer.parseInt(bf.readLine());

        long result = binarySearch(1, k); // b[k] <= k
        bw.write(String.valueOf(result));
        bw.close();
    }

    private static long binarySearch(long left, long right) {
        if(left == right) return left;

        long mid = (left + right) / 2;

        int sameOrMin = 0; // mid보다 같거나 작은 수 세기
        for(int i = 1; i <= n; i++) {
            sameOrMin += Math.min(mid / i, n); // 1단당 최대로 가질 수 있는 원소 수가 N임
        }
        if(sameOrMin >= k) return binarySearch(left, mid); // sameOrMin == k인 여러 요소 중 가장 먼저 등장하는 거 구해야하므로 lower bound
        else return binarySearch(mid + 1, right);
    }
}
