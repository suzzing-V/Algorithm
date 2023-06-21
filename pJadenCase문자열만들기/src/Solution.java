class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 || (i > 0 && s.charAt(i - 1) == ' ')) {
                if(s.charAt(i) >= 'a' && s.charAt(i) <= 'z') sb.append(String.valueOf((char)(s.charAt(i) - 32)));
                else sb.append(String.valueOf(s.charAt(i)));
            } else {
                if(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') sb.append(String.valueOf((char)(s.charAt(i) + 32)));
                else sb.append(String.valueOf(s.charAt(i)));
            }
        }
        
        String answer = sb.toString();
        return answer;
    }
}