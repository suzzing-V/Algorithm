class Solution {
    public String solution(String number, int k) {
        String answer = "";
        String[] str = new String[number.length()];
        str = number.split("");
        StringBuilder sb = new StringBuilder();
        int count = str.length - k;
        int max = 0;
        int maxIndex = -1;
        
        while(count > 0) {
            max = Integer.parseInt(str[maxIndex + 1]);
            maxIndex = maxIndex + 1;
            for(int i = maxIndex + 1; i <= str.length - count; i++) {
                if(Integer.parseInt(str[i]) > max) {
                    max = Integer.parseInt(str[i]);
                    maxIndex = i;
                }
            }
            sb.append(str[maxIndex]);
            count--;
        }
        answer = sb.toString();
        return answer;
    }
}