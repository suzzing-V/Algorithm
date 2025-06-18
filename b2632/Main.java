import java.util.*;
import java.io.*;

public class Main {

    private static Map<Integer, Integer> sumCntA = new HashMap<>();
    private static Map<Integer, Integer> sumCntB = new HashMap<>();
    private static int[] pizzaA;
    private static int[] pizzaB;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

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
        sumCntA.put(total, 1);
        sumCntA.put(0, 1);

        total = 0;
        for(int i = 0; i < n; i++) {
            pizzaB[i] = pizzaB[i + n] = Integer.parseInt(bf.readLine());
            total += pizzaB[i];
        }
        sumCntB.put(total, 1);
        sumCntB.put(0, 1);

        // 만들 수 있는 합 구하기
        makeSum(pizzaA, m, sumCntA);
        makeSum(pizzaB, n, sumCntB);

        // a피자의 sum + b피자의 sum = target 되는 경우의 수 구하기
        int answer = 0;
        for(int sum : sumCntA.keySet()) {
            if(sumCntB.get(target - sum) != null) {
                answer += sumCntA.get(sum) * sumCntB.get(target - sum);
//                System.out.println(sum);
            }
        }

        System.out.println(answer);
    }

    private static void makeSum(int[] pizza, int n, Map<Integer, Integer> sumCnt) {
        for(int i = 0; i < n; i++) {
            int sum = 0;
            for(int j = i; j < i + n - 1; j++) {
                sum += pizza[j];
                sumCnt.put(sum, sumCnt.getOrDefault(sum, 0) + 1);
            }
        }
    }
}
