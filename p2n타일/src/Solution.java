class Solution {
    public int solution(int n) {
        int answer = 0;
        long two = (long)(n / 2);
        long one;
        long R = 1000000007;
        long count = 0;
        if(n % 2 == 0) one = 0;
        else one = 1;
        
        while(two >= 0) {
            count += (factorial(two + one) / (factorial(two) * factorial(one))) % R;
            two --;
            one += 2;
        }
        count %= R;
        answer = (int)count;
        return answer;
    }
    
    public long factorial(long n) {
        if(n == 0 || n == 1) {
            return 1;
        }
        
        return n * factorial(n - 1);
    }
}