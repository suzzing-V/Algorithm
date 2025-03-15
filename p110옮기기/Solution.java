import java.util.*;

class Solution {

    private Stack<Integer> stack = new Stack<>();
    // stack 사용하지 않고 위치 옮기면서 하면 시간초과. 스택으로 하면 O(n)
    private int cnt_110 = 0;

    public String[] solution(String[] s) {

        String[] answer = new String[s.length];

        for(int i = 0; i < s.length; i++) {
            stack = new Stack<>();
            cnt_110 = 0;
            if(s[i].length() <= 3) { // 3보다 작을 경우 어떤 행동도 할 수 없다.
                answer[i] = s[i];
                continue;
            }

            get110(s[i]);
            answer[i] = insert_110();
        }

        return answer;
    }

    private void get110(String str) {
        for(int i = 0; i < str.length(); i++) {
            int c = str.charAt(i) - '0';
            int second = stack.isEmpty() ? -1 : stack.pop();
            int first = stack.isEmpty() ? -1 : stack.pop();
            if(first == 1 && second == 1 && c == 0) { // 110이면
                cnt_110 ++;
            } else { // 110 아니면 다시 넣는다
                if(first != -1) stack.add(first);
                if(second != -1) stack.add(second);
                stack.add(c);
            }
        }
    }

    // 110을 다 제거하면 남은 문자열에서 1이 연속으로 등장할 수 있는 위치는 끝밖에 없다. 앞은 다 1은 하나씩 있어야 한다.
    // 이 1이 연속적으로 등장하지 않는 문자열에서 110으로 더 사전 순 앞에 오게 할 수 있는 경우는 없다. 110보다 뒤처지는 건 111뿐이다.
    // 따라서 뒤에서부터 봤을 떄 처음으로 등장하는 0 뒤에 110들을 붙여주면 된다.
    private String insert_110() {
        StringBuilder sb = new StringBuilder();
        // 뒤에서부터 보면서 처음으로 0 나올 때 다음에 붙임
        boolean isUsed = false;
        while(!stack.isEmpty()) {
            int curr = stack.pop();
            if(curr == 0) {
                for(int i = 0; i < cnt_110; i++) sb.insert(0, "110");
                stack.add(curr);
                isUsed = true;
                break;
            }
            sb.insert(0, curr);
        }

        // 다 1이라서 110 못 넣은 경우
        if(!isUsed) {
            for(int i = 0; i < cnt_110; i++) sb.insert(0, "110");
        }

        // 남은 거 넣기
        while(!stack.isEmpty()) {
            int curr = stack.pop();
            sb.insert(0, curr);
        }
        return sb.toString();
    }
}
