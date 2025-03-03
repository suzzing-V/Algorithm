import java.util.*;

class Solution {

    private PriorityQueue<Integer>[] endTimes;
    private int[] mentoNum;
    private int k;
    private int n;
    private int[][] reqs;
    private int min = Integer.MAX_VALUE;

    public int solution(int k1, int n1, int[][] reqs1) {
        k = k1;
        n = n1;
        reqs = reqs1;
        endTimes = new PriorityQueue[k + 1];
        mentoNum = new int[k + 1];
        // 유형당 멘토 하나씩 있어야됨
        n -= k;
        Arrays.fill(mentoNum, 1);
        makeMentoCombi(0, 0); // 가능한 모든 멘토수 조합

        return min;
    }

    private void makeMentoCombi(int start, int sum) {
        if(sum == n) { // 조합 완성하면 그 조합으로 했을 때의 대기시간 합 구하기
            int wt = calWaitingTime();
            min = Math.min(wt, min);
            return;
        }

        for(int i = start; i <= k; i++) { // 조합이므로 처음부터 시작하지 않아도 됨. 다음에 오는건 자기와 같거나 큰거
            mentoNum[i] ++;
            makeMentoCombi(i, sum + 1);
            mentoNum[i] --;
        }
    }

    private int calWaitingTime() {
        // 멘토 수에 맞게 endTimes 초기화
        for(int i = 1; i <= k; i++) {
            endTimes[i] = new PriorityQueue<>();
            for(int j = 0; j < mentoNum[i]; j++) {
                endTimes[i].add(0);
            }
        }

        // 상담자 순회하면서 해당 상담자의 상담유형에 들어있는 큐에서 제일 작은 값(끝나는 시간이 가장 빠른 상담사) 뽑기
        int time = 0;
        for(int i = 0; i < reqs.length; i++) {
            int rt = reqs[i][0];
            int st = reqs[i][1];
            int type = reqs[i][2];
            int et = endTimes[type].remove();
            if(et <= rt) { // 상담 가능
                endTimes[type].add(rt + st);
            } else { // 상담 불가능
                time += (et - rt);
                endTimes[type].add(et + st);
            }
        }

        return time;
    }
}
