class Solution {

    private int[] cnt;

    public int solution(int[] a) {
        cnt = new int[a.length + 1];
        for(int i = 0; i < a.length; i++) {
            cnt[a[i]] ++;
        }

        int max = 0;
        for(int i = 0; i < cnt.length; i++) {
            if(max >= cnt[i]) continue; // 현재 숫자를 교집합으로 하는 수열의 개수가 max보다 작으면 넘어감.

            int answer = 0;
            for(int j = 0; j < a.length - 1; j++) {
                if((a[j] == i || a[j + 1] == i) && a[j] != a[j + 1]) {
                    answer ++;
                    j ++;
                }
            }
            max = Math.max(max, answer);
        }
        return max * 2;
    }
}
