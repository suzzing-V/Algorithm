import java.util.*;

class Solution {

    private int[] aSumCount = new int[601];
    private int[] bPrefixSum = new int[601];
    private int maxWin = 0;
    private int[] answer;
    private int n;
    private int[][] dice;

    public int[] solution(int[][] dice1) {
        n = dice1.length;
        dice = dice1;
        answer = new int[n / 2];
        makeDiceCombi(0, new ArrayList<>());

        for(int i = 0; i < n / 2; i++) {
            answer[i] ++;
        }
        return answer;
    }

    private void makeDiceCombi(int dice, List<Integer> dicesA) {
        if(dicesA.size() == n / 2) {
            aSumCount = new int[601];
            bPrefixSum = new int[601];
            List<Integer> dicesB = new ArrayList<>();
            for(int i = 0; i < n; i ++) {
                if(!dicesA.contains(i)) dicesB.add(i);
            }
            makeDiceSum(0, 0, dicesA, aSumCount);
            makeDiceSum(0, 0, dicesB, bPrefixSum);
            makePrefixSum(bPrefixSum);

            int win = 0;
            for(int i = 1; i <= 600; i++) {
                if(aSumCount[i] != 0) {
                    win += aSumCount[i] * bPrefixSum[i - 1];; // a의 합이 i일 때, b의 합이 i보다 작아야 a가 승리
                }
            }

            if(win > maxWin) { // 해당 조합일 때 a의 승리횟수가 지금까지의 최대 승리횟수보다 크다면 갱신
                maxWin = win;
                answer = listToArray(dicesA);
            }
            return;
        }

        for(int i = dice; i < n; i ++) {
            dicesA.add(i);
            makeDiceCombi(i + 1, dicesA);
            dicesA.remove((int)dicesA.size() - 1);
        }
    }

    private void makeDiceSum(int idx, int sum, List<Integer> dices, int[] sumCount) {
        if(idx == dices.size()) {
            sumCount[sum] ++;
            return;
        }

        int d = dices.get(idx);
        for(int i = 0; i < 6; i ++) {
            makeDiceSum(idx + 1, sum + dice[d][i], dices, sumCount);
        }
    }

    private void makePrefixSum(int[] sumCount) {
        for(int i = 1; i < 601; i++) {
            sumCount[i] += sumCount[i - 1];
        }
    }

    private int[] listToArray(List<Integer> list) {
        int[] arr = new int[list.size()];

        for(int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}