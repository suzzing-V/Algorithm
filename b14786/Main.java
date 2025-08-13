import java.util.*;
import java.io.*;

// ax + bsinx - c 는 발산함수다. 따라서 이 값이 0보다 크면 mid를 줄여주고, 0보다 작으면 mid를 늘려주면 된다.
// 탐색 범위의 길이가 10^-9라는 건 그 안에 정답이 있다는 의미이므로 오차 범위가 10^-9 이하라는 것이다.
// -1 <= sinx <= 1이므로 x의 최솟값은 (c - b) / a, 최댓값은 (c + b) / a이다.
// 시간복잡도는 log(2 * 10^5)
public class Main {

    private static double a;
    private static double b;
    private static double c;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        a = Double.parseDouble(st.nextToken());
        b = Double.parseDouble(st.nextToken());
        c = Double.parseDouble(st.nextToken());

        System.out.println(binarySearch((c - b) / a, (c + b) / a));
    }

    private static double binarySearch(double start, double end) {
        if(end - start <= Math.pow(10, -9)) {
            return end;
        }

        double mid = (start + end) / 2.0;


        // 두 경우 모두 mid를 포함할 수 있으므로 mid 포함 탐색
        if(a * mid + b * Math.sin(mid) - c <= 0) {
            start = mid;
        } else {
            end = mid;
        }
        return binarySearch(start, end);
    }
}
