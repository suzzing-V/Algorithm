import java.util.*;
import java.io.*;

public class Main {

    private static List<Integer> sumCntA = new ArrayList<>();
    private static List<Integer> sumCntB = new ArrayList<>();
    private static int[] pizzaA;
    private static int[] pizzaB;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int target = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        pizzaA = new int[2 * m];
        pizzaB = new int[2 * n];

        int total = 0;
        for(int i = 0; i < m; i++) {
            pizzaA[i] = pizzaA[i + m] = Integer.parseInt(bf.readLine());
            total += pizzaA[i];
        }
        sumCntA.add(total);
        sumCntA.add(0);

        total = 0;
        for(int i = 0; i < n; i++) {
            pizzaB[i] = pizzaB[i + n] = Integer.parseInt(bf.readLine());
            total += pizzaB[i];
        }
        sumCntB.add(total);
        sumCntB.add(0);

        // 만들 수 있는 합 구하기
        makeSum(pizzaA, m, sumCntA);
        makeSum(pizzaB, n, sumCntB);

        // 정렬
        Collections.sort(sumCntA);
        Collections.sort(sumCntB);

        // a피자의 sum + b피자의 sum = target 되는 경우의 수 구하기
        int answer = 0;
        for(int i = 0; i <= target; i++) {
            // 이분탐색 시에는 배열에 k보다 같거나 큰 수가 없을 경우 배열크기를 인덱스로 하는 위치가 상한선, 하한선이 될 수 있으므로 범위를 0~배열크기로 설정한다.
            int leftA = lowerBound(0, sumCntA.size(), i, sumCntA);
            int rightA = upperBound(0, sumCntA.size(), i, sumCntA);
            int a = rightA - leftA;

            int leftB = lowerBound(0, sumCntB.size(), target - i, sumCntB);
            int rightB = upperBound(0, sumCntB.size(), target - i, sumCntB);
            int b = rightB - leftB;
            answer += a * b;
        }

        System.out.println(answer);
    }

    private static int upperBound(int start, int end, int target, List<Integer> sumCnt) {
        if(start == end) return start;

        int mid = (start + end) / 2;
        if(sumCnt.get(mid) <= target) {
            return upperBound(mid + 1, end, target, sumCnt);
        } else {
            return upperBound(start, mid, target, sumCnt);
        }
    }

    private static int lowerBound(int start, int end, int target, List<Integer> sumCnt) {
        if(start == end) return start;

        int mid = (start + end) / 2;
        if(sumCnt.get(mid) < target) {
            return lowerBound(mid + 1, end, target, sumCnt);
        } else {
            return lowerBound(start, mid, target, sumCnt);
        }
    }

    private static void makeSum(int[] pizza, int n, List<Integer> sumCnt) {
        for(int i = 0; i < n; i++) {
            int sum = 0;
            for(int j = i; j < i + n - 1; j++) {
                sum += pizza[j];
                sumCnt.add(sum);
            }
        }
    }
}
