import java.util.*;

class Solution {
    int gcd = 1;
    public long solution(int[] arr) {
        int a = arr[0];
        int b = arr[1];
        GCD(a, b);
        int lcm = (a / gcd) * (b / gcd) * gcd;
        
        for(int i = 2; i < arr.length; i++) {
            gcd = 1;
            GCD(lcm, arr[i]);
            lcm = (lcm / gcd) * (arr[i] / gcd) * gcd;
        }
        return lcm;
    }
    
    public void GCD(int a, int b) {
        int min = Math.min(a, b);
        for(int i = 2; i <= min; i++) {
            if(a % i == 0 && b % i == 0) {
                gcd *= i;
                GCD(a / i, b / i);
                return;
            }
        }
    }
}