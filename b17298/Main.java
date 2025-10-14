import java.util.*;
import java.io.*;

// 시간복잡도: 2 * 1000000
// 직관적으로 구현하면 O(n^2)로 시간 초과
// 현재 값이 스택의 탑값보다 작으면 스택에 넣고, 크면 스택의 탑값이 현재값보다 작을 때까지 뽑는 값들의 가장 오른쪽에 있으면서 자기보다 큰 수가 현재 값이다.
// 따라서 뽑으면서 answer에 현재 값을 저장해준다. 더 큰 값을 마주치면 멈추고 현재 값을 푸시한다.
public class Main {

    private static int n;
    private static int[] arr;
    private static int[] answer;
    private static Stack<Integer> stack =  new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        arr = new int[n];
        answer = new int[n];
        Arrays.fill(answer, -1);
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < n; i ++) {
            // 만약 stack이 비어있거나 stack의 top 값이 현재 값보다 크거나 같으면 현재 인덱스 stack에 push
            if(stack.isEmpty() || arr[stack.peek()] >= arr[i]) {
                stack.push(i);
            } else {
                // 현재 값이 top 값보다 크면 현재값보다 큰 값 만나거나 스택이 빌 때까지 스택에서 값을 꺼내면서 그 값들의 answer에 현재 값 저자
                while(!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                    int idx = stack.pop();
                    answer[idx] = arr[i];
                }
                stack.push(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb);
    }
}
