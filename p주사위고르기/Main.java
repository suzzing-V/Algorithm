import java.util.*;

class Solution {
    int diceNum;
    int max = 0;
    List<Integer> maxDices = new ArrayList<>();

    public int[] solution(int[][] dice) {
        diceNum = dice.length;
        int[] answer = new int[diceNum / 2];

        List<Integer> selected = new ArrayList<>();
        for(int i = 1; i <= diceNum; i++) {
            selected.add(i);
            selectDice(selected, 1, i, dice);
            selected.remove(selected.size() - 1);
        }

        for(int i = 0; i < diceNum / 2; i++) {
            answer[i] = maxDices.get(i);
        }

        return answer;
    }

    public void selectDice(List<Integer> selected, int count, int start, int[][] dice) {
        if(count == diceNum / 2) {
            List<Integer> selectedB = new ArrayList<>();
            for(int i = 1; i <= diceNum; i++) {
                if(!selected.contains(i)) {
                    selectedB.add(i);
                }
            }

            int winCount = getWinCount(selected, selectedB, dice);
            if(winCount > max) {
                max = winCount;
                maxDices = new ArrayList<>();
                for(Integer num: selected) {
                    maxDices.add(num);
                }
            }
            return;

        }


        for(int i = start + 1; i <= diceNum; i++) {
            selected.add(i);
            selectDice(selected, count + 1, i, dice);
            selected.remove(selected.size() - 1);
        }
    }

    public int getWinCount(List<Integer> selectedA, List<Integer> selectedB, int[][] dice) {
        int[] aSum = new int[100000];
        int[] bSum = new int[100000];

        countSum(aSum, selectedA, dice);
        countSum(bSum, selectedB, dice);

        int winCount = 0;
        for(int i = 1; i <= diceNum * 100; i++) {
            if(aSum[i] != 0) {
                int bSumCount = 0;
                for(int j = 1; j < i; j ++) {
                    bSumCount += bSum[j];
                }
                winCount += aSum[i] * bSumCount;
            }
        }

        return winCount;
    }

    public void countSum(int[] sum, List<Integer> selected, int[][] dice) {
        for(int i = 0; i < 6; i ++) {
            int first = dice[selected.get(0) - 1][i];
            if(diceNum >= 4) {
                for(int j = 0; j < 6; j++) {
                    int second = dice[selected.get(1) - 1][j];
                    if(diceNum >= 6) {
                        for(int k = 0; k < 6; k++) {
                            int third = dice[selected.get(2) - 1][k];
                            if(diceNum >= 8) {
                                for(int l = 0; l < 6; l++) {
                                    int fourth = dice[selected.get(3) - 1][l];
                                    if(diceNum == 10) {
                                        for(int m = 0; m < 6; m++) {
                                            int fifth = dice[selected.get(4) - 1][m];
                                            sum[first + second + third + fourth + fifth] += 1;
                                        }
                                    } else {
                                        sum[first + second + third + fourth] += 1;
                                    }
                                }
                            } else {
                                sum[first + second + third] += 1;
                            }
                        }
                    } else {
                        sum[first + second] += 1;
                    }
                }
            } else {
                sum[first] += 1;
            }
        }
    }
}
