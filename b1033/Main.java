import java.util.*;
import java.io.*;

// 시간복잡도: 2 * log9 * 10
// 최소공배수: (a * b) / gcd(a, b);
// 최대공약수는 유클리드 호제법 활용
// 0 재료의 비율을 1로 두고, 0재료에 대한 나머지 재료의 비율을 분자 분모 따로 저장해준다.
// 저장할 때, 분자와 분모를 최대공약수로 나누어서 기약분수로 만들어준다.
// 이 분모들의 최소공배수를 구해서 각 재료의 비율에 곱한다. 그러면 다 분모가 1이 되고, 재료가 정수가 된다.
// 유클리드 호제법으로 최대공약수를 구하는 시간복잡도는 log(min(a, b)) 이다.
// 전체 최소공배수가 최대 9^10 일수 있고, 나중에 이 값을 곱할 때 분자의 값이 9이면 int의 범위를 넘어선다. 따라서 타입을 long으로 잡아준다.
public class Main {

    private static int n;
    private static List<Info>[] conn;
    private static long[][] per; // 분자와 분모 저장
    private static long totalLcm = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        conn = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            conn[i] = new ArrayList<>();
        }
        per = new long[n][2];
        per[0][0] = 1;
        per[0][1] = 1;

        for(int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            conn[n1].add(new Info(n1, n2, p, q));
            conn[n2].add(new Info(n2, n1, q, p));
        }

        // 0 재료에 대한 비율 저장하기
        dfs(0);
//        for(int i = 0; i < n;i ++) {
//            System.out.println(per[i][0] + " " + per[i][1]);
//        }

//        System.out.println("totalGcd: " + totalLcm);
        // 각 재료의 비율에 전체 최대공약수 곱해서 정수로 만들기
        for(int i = 0; i < n; i++) {
            per[i][0] *= (long)(totalLcm / per[i][1]);
        }

        // 각 재료의 분자 출력
        for(int i = 0; i< n; i++) {
            System.out.print(per[i][0] + " ");
        }
    }

    private static void dfs(int curr) {
//        System.out.println("curr :" + curr);
        for(Info i : conn[curr]) {
            // 이미 해당 재료의 비율이 저장되어 있으면 넘어간다.
            if(per[i.n2][0] != 0) continue;

//            System.out.println(i.n2 + " 값 넣음");
            // 채워넣어야 할 재료의 분자, 분모값을 저장한다.
            per[i.n2][0] = per[curr][0] * i.p2;
            per[i.n2][1] = per[curr][1] * i.p1;

            // 분자, 분모를 최대공약수로 나눠 기약분수로 만들어준다.
            long gcd = gcd(Math.max(per[i.n2][0], per[i.n2][1]), Math.min(per[i.n2][0], per[i.n2][1]));
            per[i.n2][0] /= gcd;
            per[i.n2][1] /= gcd;

            // 현재 분모로 전체 최소공배수를 갱신해준다.
            totalLcm = (totalLcm * per[i.n2][1]) / gcd(Math.max(totalLcm, per[i.n2][1]), Math.min(totalLcm, per[i.n2][1]));
//            System.out.println(totalLcm + " 와 " + per[i.n2][1] + "의 lcm: " + totalLcm);
            dfs(i.n2);
        }
    }

    private static long gcd(long a, long b) {
        if(b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }

    private static class Info {
        int n1;
        int n2;
        int p1;
        int p2;

        Info(int n1, int n2, int p1, int p2) {
            this.n1 = n1;
            this.n2 = n2;
            this.p1 = p1;
            this.p2 = p2;
        }
    }
}
