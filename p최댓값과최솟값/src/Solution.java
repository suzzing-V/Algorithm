class Solution {
    public String solution(String s) {
        String answer = "";
        int max = -2147483648;
        int min = 2147483647;
        
        String tmp = "";
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '-') tmp += "-";
            else if(s.charAt(i) == ' ') continue;
            else {
                tmp += String.valueOf(s.charAt(i));
                if(i == s.length() - 1 || s.charAt(i + 1) == ' ') {
                    max = Math.max(max, Integer.parseInt(tmp));
                    min = Math.min(min, Integer.parseInt(tmp));
                    tmp = "";
                }
            }
        }
        
        answer = min + " " + max;
        return answer;
    }
}