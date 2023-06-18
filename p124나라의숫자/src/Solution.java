class Solution {
    public String solution(int n) {
        String answer = "";
        String[] otf = { "1", "2", "4" };
        
        boolean flag = false;
        while(n != 0) {
            if(flag == true) n --;
            if(n == 0) break;
            if(n % 3 == 1) answer = otf[0] + answer;
            else if(n % 3 == 2) answer = otf[1] + answer;
            else {
                flag = true;
                answer = otf[2] + answer;
            }
            n /= 3;
        }
        return answer;
    }
}