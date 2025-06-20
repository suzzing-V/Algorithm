import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for(int i = 0; i < numbers.length; i++) {
            // 이진수 문자열 얻기
            String bn = Long.toBinaryString(numbers[i]);

            // 문자열 길이가 이진트리 노드 개수가 될 수 없으면 될때까지 앞에 0붙여줌
            if(bn.length() == 1) {}
            else if(bn.length() <= 3) bn = fillZero(bn, 3) + bn;
            else if(bn.length() <= 7) bn = fillZero(bn, 7) + bn;
            else if(bn.length() <= 15) bn = fillZero(bn, 15) + bn;
            else if(bn.length() <= 31) bn = fillZero(bn, 31) + bn;
            else if(bn.length() <= 63) bn = fillZero(bn, 63) + bn;

            // 문자열 쪼개가면서 탐색.
            answer[i] = dfs(0, bn.length() - 1, bn);
        }
        return answer;
    }

    private String fillZero(String bn, int size) {
        StringBuilder sb = new StringBuilder();
        for(int i = bn.length(); i < size; i++) {
            sb.append('0');
        }
        return sb.toString();
    }

    private int dfs(int start, int end, String str) {
        if(start == end) { // 끝까지 도달했으면 문제없다는 의미
            return 1;
        }

        int mid = (start + end) / 2;
        if(str.charAt(mid) == '0') { // 가운데가 0일 경우는 다 0이어야 한다.
            for(int i = start; i <= end; i++) {
                if(str.charAt(i) != '0') return 0;
            }
        }

        // 가운데가 1일 경우는 양쪽 트리 모두 조건을 만족해야 한다
        if(dfs(start, mid - 1, str) == 1 && dfs(mid + 1, end, str) == 1) {
            return 1;
        }
        return 0;
    }
}