import java.util.*;
class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];;
        for(int i = 0; i < numbers.length; i ++) {
            String bin = Long.toBinaryString(numbers[i]);

            bin = fillZero(bin); // 포화 이진트리의 노드 개수는 2^n - 1이므로 자리수를 이에 맞춘다
            answer[i] = checkTree(bin, false) ? 1 : 0;
        }
        return answer;
    }

    private String fillZero(String bin) {
        int p = 0;
        while(true) {
            if(bin.length() <= Math.pow(2, p) - 1) {
                bin = String.format("%" + (int)(Math.pow(2, p) - 1) + "s", bin).replace(' ', '0');
                break;
            }
            p++;
        }
        return bin;
    }

    private boolean checkTree(String tree, boolean isDummy) {
        int half = tree.length() / 2;

        // 길이가 2^n - 1인지 확인
        int p = 0;
        while(true) {
            if(tree.length() < Math.pow(2, p) - 1) return false;
            else if(tree.length() == Math.pow(2, p) - 1) break;
            p ++;
        }

        // 부모 노드가 더미노드일 때 자식 노드들은 모두 0이어야 한다.
        long toLong = tree.isEmpty() ? 0L : Long.parseLong(tree, 2);
        if(isDummy && Long.bitCount(toLong) != 0) return false;

        // 마지막 노드면 완성
        if(tree.length() == 1) return true;

        String left = tree.substring(0, half);
        String right = tree.substring(half + 1, tree.length());

        boolean isD = tree.charAt(half) == '0'? true : false;
        if(checkTree(left, isD) && checkTree(right, isD)) { // 왼 오 나눴을 때 두 서브트리 모두 조건을 만족한다면 올바른 트리
            return true;
        }
        return false;
    }
}