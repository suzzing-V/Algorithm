class Solution {
    public int[] solution(long begin, long end) {
        int[] answer = new int[(int)end - (int)begin + 1];
        int k = 0;
        
        for(int i = (int)begin; i <= (int)end; i++) {
            if(i == 1) answer[k++] = 0;
            else if(i % 2 == 0) answer[k ++] = i / 2;
            else answer[k++] = findMaxDivide(i);
        }
        return answer;
    }
    
    public int findMaxDivide(int n) {
        for(int i = n - 2; i > 2; i -= 2) {
            if(n % i == 0) return i;
        }
        return 1;
    }
}