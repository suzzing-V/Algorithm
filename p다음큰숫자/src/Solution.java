class Solution {
    public int solution(int n) {
        int answer = n + 1;
        int count = countOne(n);
        
        while(true) {
            if(countOne(answer) == count) return answer; 
            answer++;
        }
    }
    
    public int countOne(int answer) {
        int count = 0;
        
        while(answer > 0) {
            if(answer % 2 == 1) count ++;
            answer /= 2;
        }
        return count;
    }
}