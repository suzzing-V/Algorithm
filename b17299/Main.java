import java.util.*;
import java.io.*;

// 가장 왼쪽 -> 가장 먼저 등장하는 -> 스택 사용하면 좋음. 어짜피 더 왼쪽 요소를 탐색하더라도 현재 값보다 작은 건 사용할 일이 없다.
// 다음 턴에는 현재값에서 가장 큰 왼쪽에 있는 큰 값과 현재 값을 비교하게 된다. 현재값이 더 크다면 이 값을 채택하게 된다.
// 시간복잡도: 어짜피 스택에 한번 들어갔다 나오므로 O(n)
public class Main {

    private static int n;
    private static int[] arr;
    private static Stack<Integer> stack = new Stack<>();
    private static int[] cnt = new int[(int)Math.pow(10, 6) + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        arr =  new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        // 각 숫자 개수 세기
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            cnt[arr[i]] ++;
        }

        // 뒤에서부터 탐색하면서 스택의 마지막 요소의 개수가 현재 탐색하는 수의 개수보다 클 때까지 스택의 요소를 제거한다. 그 수를 출력하고 현재 수를 넣는다.
        List<Integer> reverse = new ArrayList<>();
        for(int i = n - 1; i >= 0; i --) {
            int max = -1;
            while(!stack.isEmpty()) {
                if(cnt[stack.peek()] <= cnt[arr[i]]) {
                    stack.pop();
                } else {
                    max = stack.peek();
                    break;
                }
            }
            stack.add(arr[i]);
            reverse.add(max);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = reverse.size() - 1; i >= 0; i --) {
            sb.append(reverse.get(i)).append(" ");
        }
        System.out.println(sb.toString());
    }
}
