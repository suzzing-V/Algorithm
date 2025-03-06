import java.util.*;

class Solution {

    private int[] divisor; // 약수 개수
    private int[] maxNum; // i ~ n까지의 최대 약수개수 가진 수

    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];
        divisor = new int[e + 1];
        maxNum = new int[e + 1];
        countPrime(e);
        getMaxDivisorNum(e);

        for(int i = 0; i < starts.length; i++) {
            answer[i] = maxNum[starts[i]];
        }
        return answer;
    }

    private void countPrime(int target) {
        // System.out.print(target + " ");
        for(int i = 1; i <= target; i++) {
            for(int j = i; j <= target; j+= i) {
                divisor[j] ++;
            }
        }

        // System.out.println(Arrays.toString(divNum));
    }

    private void getMaxDivisorNum(int e) {
        int maxIdx = e;
        for(int i = e; i >= 1; i--) {
            if(divisor[maxIdx] <= divisor[i]) {
                divisor[maxIdx] = divisor[i];
                maxIdx = i;
            }

            maxNum[i] = maxIdx;
        }
    }
}