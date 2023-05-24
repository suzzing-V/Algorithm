class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        boolean[] count = new boolean[31];
        int both = 0;
        for(int i = 0; i < lost.length; i++) {
            count[lost[i]] = true;
        }
        for(int i = 0; i < reserve.length; i++) {
            if(count[reserve[i]]) both++;
        }
        answer = n - (lost.length - both) + Math.min(lost.length - both, reserve.length - both) + both;
        return answer;
    }
}