class Solution {
    public int solution(int n) {
        int answer = 0;
        int k = 3;
        
        for(int i = 2; k <= n; i++) {
            if((n - k) % i == 0) answer ++;
            k += (i + 1);
        }
        answer ++;
        return answer;
    }
}