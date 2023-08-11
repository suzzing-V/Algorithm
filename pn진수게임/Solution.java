class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        int num = 0;
        int order = 1;
        int count = 0;
        if(p == m) p = 0;
        
        while(count <= t) {
            String cum = "";
            if(num == 0) cum = "0";
            else cum = changeNumToN("0123456789ABCDEF", num, n);
            //System.out.println("cum: " + cum);
            for(int i = 0; i < cum.length(); i++) {
                if(order % m == p) {
                    answer += String.valueOf(cum.charAt(i));
                    count++;
                    if(count == t) return answer;
                }
                order ++;
            }
            num++;
        }
        return answer;
    }
    
    public String changeNumToN(String form, int num, int n) {
        if(num == 0) {
            return "";
        }
        return changeNumToN(form, num / n, n) + String.valueOf(form.charAt(num % n));
    }
}