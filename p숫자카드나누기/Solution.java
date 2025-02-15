import java.util.*;

class Solution {
    public long solution(int[] arrayA, int[] arrayB) {
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);
        int byArrayA = getMaxResult(arrayA, arrayB);
        int byArrayB = getMaxResult(arrayB, arrayA);
        return Math.max(byArrayA, byArrayB);
    }

    private int getMaxResult(int[] arrayA, int[] arrayB) {
        LinkedList<Integer> divide = new LinkedList<>();
        int gcdA = arrayA[0];
        // 최대공약수 구하기
        for(int i = 2; i < arrayA.length; i ++) {
            if(arrayA[i] % gcdA == 0) continue;
            gcdA = gcd(arrayA[i], gcdA);
        }

        // int gcdA = divide.get(divide.size()- 1);
        // b 배열에서는 모두 나눠지면 안됨. 나눠지는 수 있으면 삭제
        for(int i = 0; i < arrayB.length; i++) {
            int num = arrayB[i];
            if(num % gcdA == 0) {
                return 0;
            }
        }
        return gcdA;
    }

    private int gcd(int a, int b) {
        if(a < b) {
            int tmp = b;
            b = a;
            a = tmp;
        }
        if(b == 0) return a;
        return gcd(b, a % b);
    }
}
