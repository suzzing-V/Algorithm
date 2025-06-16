import java.util.*;

class Solution {

    private int min = Integer.MAX_VALUE;
    private int[][] reqs;

    public int solution(int k, int n, int[][] reqs1) {
        reqs = reqs1;

        // 유형별 가능한 멘토 수 조합 구하기
        int[] mentoCnt = new int[k + 1];
        Arrays.fill(mentoCnt, 1);
        makeMentoCombi(n - k, 1, mentoCnt);
        return min;
    }

    private void makeMentoCombi(int rest, int idx, int[] mentoCnt) {
        if(idx == mentoCnt.length) {
            if(rest != 0) return;

            counsel(mentoCnt);
            return;
        }

        for(int i = rest; i >= 0; i --) {
            mentoCnt[idx] = i + 1;
            makeMentoCombi(rest - i, idx + 1, mentoCnt);
        }
    }

    private void counsel(int[] mentoCnt) {
        PriorityQueue<Counsel>[] counsels = new PriorityQueue[mentoCnt.length];

        // 유형별 멘토 수만큼 큐에 넣기
        for(int i = 1; i < mentoCnt.length; i++) {
            counsels[i] = new PriorityQueue<Counsel>();
            int num = mentoCnt[i];
            // System.out.print(num + " ");
            for(int j = 1; j <= num; j++) {
                counsels[i].add(new Counsel(0, 0));
            }
        }
        System.out.println();

        // 상담 진행하면서 대기시간 구하기
        int waitingTime = 0;
        for(int i = 0; i < reqs.length; i++) {
            int start = reqs[i][0];
            int duration = reqs[i][1];
            int type = reqs[i][2];

            Counsel c = counsels[type].remove(); // 해당 유형 상담 중 가장 빨리 끝나는 상담 뽑기
            // System.out.println(start + " " + duration);
            // System.out.println(c.start + " " + c.duration);

            int waiting = 0;
            if(c.start + c.duration > start) { // 뽑은 상담의 끝나는 시간이 현재 멘티의 도착시간보다 크면 대기해야함
                waiting = c.start + c.duration - start;
            }
            waitingTime += waiting;
            // System.out.println(waitingTime);
            counsels[type].add(new Counsel(start + waiting, duration)); // 멘토의 상담 갱신
        }

        min = Math.min(min, waitingTime);
    }

    private class Counsel implements Comparable<Counsel> {
        int start;
        int duration;

        Counsel(int start, int duration) {
            this.start = start;
            this.duration = duration;
        }

        @Override
        public int compareTo(Counsel c) {
            return (this.start + this.duration) - (c.start + c.duration);
        }
    }
}