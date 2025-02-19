import java.util.*;

class Solution {

    private Map<Integer, Integer> partSum = new HashMap<>();

    public int solution(int[] elements) {
        int[] elements2 = new int[elements.length * 2];
        for(int i = 0; i < elements.length; i++) {
            elements2[i] = elements[i];
        }
        for(int i = elements.length; i < elements.length * 2; i++) {
            elements2[i] = elements[i - elements.length];
        }

        for(int i = 1; i <= elements.length; i ++) {
            for(int j = 0; j < elements.length; j++) {
                int sum = 0;
                for(int k = j; k < j + i; k++) {
                    sum += elements2[k];
                }
                partSum.put(sum, 0);
            }
        }
        return partSum.size();
    }
}