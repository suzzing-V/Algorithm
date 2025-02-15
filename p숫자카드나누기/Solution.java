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
        int first = arrayA[0];
        // 첫번째 수의 약수 담기
        for(int i = 2; i * i <= first; i ++) {
            if(first % i == 0) {
                divide.add(i);
                if(i * i != first) {
                    divide.add(first / i);
                }
            }
        }
        divide.add(first);
        Collections.sort(divide);
        // 남은 요소들이 약수들로 나눠지는지 확인하고 나눠지지 않는 약수는 삭제하기
        for(int i = 1; i < arrayA.length; i++) {
            int num = arrayA[i];
            // System.out.println("num: " + num);
            ListIterator<Integer> iter = divide.listIterator();
            while(iter.hasNext()) {
                int mod = iter.next();
                if(num % mod != 0) {
                    // System.out.println("안 나눠져서 삭제삭제: " + mod);
                    iter.remove();
                    if(divide.isEmpty()) { // 다같이 나눠지는 수 없음 -> b 탐색
                        return 0;
                    }
                }
            }
        }

        // int gcdA = divide.get(divide.size()- 1);
        // b 배열에서는 모두 나눠지면 안됨. 나눠지는 수 있으면 삭제
        for(int i = 0; i < arrayB.length; i++) {
            int num = arrayB[i];
            ListIterator<Integer> iter = divide.listIterator();
            while(iter.hasNext()) {
                int mod = iter.next();
                if(num % mod == 0) {
                    // System.out.println("삭제: " + mod);
                    iter.remove();
                    if(divide.isEmpty()) { // 다같이 나눠지지 않는 수 없음 -> b 탐색
                        return 0;
                    }
                }
            }
        }
        return divide.get(divide.size() - 1);
    }
}
