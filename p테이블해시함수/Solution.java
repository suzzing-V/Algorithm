import java.util.*;

class Solution {
    static int col2;
    static List<Tuple> tuples = new ArrayList<>();

    private class Tuple implements Comparable<Tuple> {
        private int[] arr;

        Tuple(int[] arr) {
            this.arr = arr;
        }

        @Override
        public int compareTo(Tuple o) {
            if(this.arr[col2 - 1] == o.arr[col2 - 1]) {
                return o.arr[0] - this.arr[0];
            }
            return this.arr[col2 - 1] - o.arr[col2 - 1];
        }
    }

    public int solution(int[][] data, int col, int row_begin, int row_end) {
        col2 = col;
        for(int i = 0; i < data.length; i++) {
            tuples.add(new Tuple(data[i]));
        }

        Collections.sort(tuples);

        int answer = 0;
        for(int i = 0; i < data[row_begin - 1].length; i++) {
            answer += tuples.get(row_begin - 1).arr[i] % row_begin;
        }

        for(int i = row_begin; i < row_end; i++) {
            int sum = 0;
            for(int j = 0; j < data[0].length; j++) {
                sum += tuples.get(i).arr[j] % (i + 1);
            }
            answer ^= sum;
        }

        return answer;
    }
}