import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static long[][] monkeys;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());
        monkeys = new long[n][2];
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            monkeys[i][0] = x;
            monkeys[i][1] = y;
        }

        bw.write(String.valueOf(binarySearch(-1000000000, 1000000000)));
        bw.close();
    }

    private static long binarySearch(long left, long right) {
        long mid = (left + right) / 2;
        long prev = getMaxDistance(mid - 1);
        long curr = getMaxDistance(mid);
        long next = getMaxDistance(mid + 1);

//        System.out.println(mid + " " + prev + " " + curr + " "+ next);
        if(curr <= prev && curr <= next) { // 앞에거보다도 작거나 같고 뒤에거보다도 작거나 같으면 이게 최소값이다
            return curr;
        }

        if(curr < next && curr > prev) { // prev < curr < next이면 왼쪽 탐색
            return binarySearch(left, mid - 1);
        }
        return binarySearch(mid + 1, right); // next < curr < prev면 오른쪽 탐색
    }

    private static long getMaxDistance(long ground) {
        long max = 0;

        for(int i = 0; i < n; i++) {
            long x1 = monkeys[i][0];
            long y1 = monkeys[i][1];
            for(int j = i + 1; j < n; j++) {
                long x2 = monkeys[j][0];
                long y2 = monkeys[j][1];

                if(x1 == x2) {
                    long distance = Math.abs(y1 - y2);
                    max = Math.max(distance, max);
                } else {
                    long distance = Math.abs(y1 - ground) + Math.abs(y2 - ground) + Math.abs(x1 - x2);
                    max = Math.max(distance, max);
                }
            }
        }
        return max;
    }
}
