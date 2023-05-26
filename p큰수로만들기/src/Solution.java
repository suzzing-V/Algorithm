class Solution {
    public String solution(String number, int k) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        int count = number.length() - k;
        int max = 0;
        int maxIndex = -1;
        
        while(count > 0) {
            max = number.charAt(maxIndex + 1) - '0';
            maxIndex = maxIndex + 1;
            for(int i = maxIndex + 1; i <= number.length() - count; i++) {
                if(number.charAt(i) - '0' > max) {
                    max = number.charAt(i) - '0';
                    maxIndex = i;
                }
            }
            sb.append(Integer.toString(max));
            count--;
        }
        answer = sb.toString();
        return answer;
    }
}