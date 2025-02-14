import java.util.*;

class Solution {

    PriorityQueue<Gyool> gyools = new PriorityQueue<>();

    private class Gyool implements Comparable<Gyool> {
        private int size;
        private int num;

        Gyool(int size, int num) {
            this.size = size;
            this.num = num;
        }

        @Override
        public int compareTo(Gyool o) {
            return o.num - this.num;
        }
    }

    public int solution(int k, int[] tangerine) {
        int answer = 0;
        Arrays.sort(tangerine);
        for(int i = 0; i < tangerine.length; i++) {
            int cnt = 0;
            int j = i;
            int curr = tangerine[i];
            while(j < tangerine.length && curr == tangerine[j]) {
                cnt ++;
                j++;
            }
            gyools.add(new Gyool(curr, cnt));
            i = j - 1;
        }

        int cnt = 0;
        int sizeCnt = 0;
        while(!gyools.isEmpty()) {
            Gyool curr = gyools.remove();
            // System.out.println("curr: " + curr.size + " " + curr.num);
            if(k - cnt < curr.num) {
                sizeCnt ++;
                break;
            }
            cnt += curr.num;
            sizeCnt ++;
            if(k == cnt) {
                break;
            }
        }
        return sizeCnt;
    }
}
