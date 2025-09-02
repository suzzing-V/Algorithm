import java.util.*;
import java.io.*;

public class Main {

    private static boolean[] isWrap;
    private static int n;
    private static String expression;
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        isWrap = new boolean[n / 2 + 1];
        expression = bf.readLine();

        dfs(0, 0, '+');
        System.out.println(max);
    }

    private static void dfs(int idx, long sum, char operator) {
        // 마지막 하나 남았을 때 그냥 연산해준다.
        if(idx == n - 1) {
            int n1 = expression.charAt(idx) - '0';
            long result = calculate(sum, n1, operator);
            max = Math.max(max, (int)result);
            return;
        }

        // 아무것도 안 남았을 때 최댓값 갱신
        if(idx == n + 1) {
            max = Math.max(max, (int)sum);
            return;
        }

//        System.out.println(expression.charAt(idx) + " "+ sum + " "+ operator);
        // 현재 수 괄호 안 넣기
        long n1 = expression.charAt(idx) - '0';
        long result = calculate(sum, n1, operator);
        if(idx + 1 < n) {
            char next_oper = expression.charAt(idx + 1);
            dfs(idx + 2, result, next_oper);

            // 현재 수 괄호 넣기
            int n2 = expression.charAt(idx + 2) - '0';
            result = calculate(sum, calculate(n1, n2, next_oper), operator);
            if(idx + 3 < n) {
                next_oper = expression.charAt(idx + 3);
                dfs(idx + 4, result, next_oper);
            } else {
                max = Math.max(max, (int)result);
            }
        } else {
            max = Math.max(max, (int)result);
        }
    }

    private static long calculate(long n1, long n2, char oper) {
        if(oper == '+') {
            return n1 + n2;
        } else if(oper == '-') {
            return n1 - n2;
        }
            return n1 * n2;
    }
}
