import java.util.*;

class Solution {

    public List<Double> prefixSum = new ArrayList<>();

    public double[] solution(int k, int[][] ranges) {
        double sum = 0;
        int n = 0;
        prefixSum.add(0.0);
        while(k != 1) {
            double prev = k;
            if(k % 2 == 0) k /= 2;
            else k = k * 3 + 1;
            sum += (prev + k) / 2;
            prefixSum.add(sum);
            n ++;
        }

        double[] result = new double[ranges.length];
        for(int i = 0; i < ranges.length; i ++) {
            int start = ranges[i][0];
            int end = n + ranges[i][1];
            if(start > end) {
                result[i] = -1;
                continue;
            }
            result[i] = prefixSum.get(end) - prefixSum.get(start);
        }
        return result;
    }
}