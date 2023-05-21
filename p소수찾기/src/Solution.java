import java.util.*;

class Solution {
    public int solution(String numbers) {
        int answer = 0;
        Set<Integer> set = new HashSet<>();
        String[] num = new String[numbers.length()];
        num = numbers.split("");
        
        int[] count = new int[num.length];
        addDigit(set, "", count, num);
        answer = set.size();
        return answer;
    }
    
    public String addDigit(Set<Integer> set, String str, int[] count, String[] num) {
        if(str.length() == count.length) {
            return str;
        }
        for(int i = 0; i < count.length; i++) {
            if(count[i] == 0) {
                count[i]++;
                int tmp = Integer.parseInt(addDigit(set, str + num[i], count, num));
                count[i]--;
                if(isPrime(tmp)) set.add(tmp);
            }
        }
        return str;
    }
    
    public boolean isPrime(int n) { //소수판별 함수
        if(n == 0 || n == 1) return false;
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) return false;
        }
        return true;
    }
}