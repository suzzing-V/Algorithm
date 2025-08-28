import java.util.*;
import java.io.*;

// 내 방식으로 하면 통과는 되지만 k가 250000일 경우 시간초과날 경우가 있음.
// 스택에 수를 차례로 넣으면서 스택의 수가 현재 수보다 작으면 지우기
// 시간 복잡도: 5* 10^5 * 2
public class Main {

    private static int[] nums;
    private static int n;
    private static int k;
    private static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        nums = new int[n];
        int size = n - k;
        String[] split = bf.readLine().split("");
        for(int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }

        // 어짜피 반복문 최대로 돌아가도 다 합해서 n번 돌아간다.
        // 길이가 n - k와 같거나 긴 앞의 자리 수가 가장 큰 내림차순 수열 만들기. 앞의 수가 가장 커야하므로 이전에 작은 것들이 있으면 다 지우기. 하지만 길이는 최소 n -k여야 하므로 k를 다 썼을 때 뒤에 더 큰 게 와도 어쩔 수 없다.
        for(int i = 0; i < n; i++) {
            // 아직 지울 수 있는 횟수가 남아있고, 수열의 마지막 요소가 현재 탐색 수보다 더 작으면 지우기. 더 크거나 같을 때까지 지우기
            while(!stack.isEmpty() && k > 0 && stack.peek() < nums[i]) {
                k --;
                stack.pop();
            }

            // 지울 수 있는 횟수가 안 남아있으면 뒤에 거 그냥 다 추가
            stack.add(nums[i]);
        }

        // k가 남아있으면 요구하는 길이보다 더 긴 내림차순 수열이 만들어진 것이므로 뒤에거 자름
        if(k > 0) {
            while(stack.size() > size) {
                stack.pop();
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        String reverse = sb.toString();
        for(int i = reverse.length() - 1; i >= 0; i--) {
            System.out.print(reverse.charAt(i));
        }
    }
}
