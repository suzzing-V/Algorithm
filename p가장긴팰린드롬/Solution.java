import java.util.*;

class Solution
{
    private int max = 1;
    private String s;

    public int solution(String s1)
    {
        s = s1;
        for(int i = 0; i < s.length() - 1; i++) {
            if(i != 0 && s.charAt(i - 1) == s.charAt(i + 1)) {
                max = Math.max(max, countOddPellin(i));
            }

            if(s.charAt(i) == s.charAt(i + 1)) {
                max = Math.max(max, countEvenPellin(i, i + 1));
            }
        }

        return max;
    }

    private int countOddPellin(int start) {
        int cnt = 1;

        int left = start - 1;
        int right = start + 1;
        while(left >= 0 && right < s.length()) {
            if(s.charAt(left) == s.charAt(right)) {
                left --;
                right ++;
                cnt += 2;
            } else {
                break;
            }
        }

        return cnt;
    }

    private int countEvenPellin(int left, int right) {
        int cnt = 0;

        while(left >= 0 && right < s.length()) {
            if(s.charAt(left) == s.charAt(right)) {
                left --;
                right ++;
                cnt += 2;
            } else {
                break;
            }
        }

        return cnt;
    }
}
