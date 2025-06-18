class Solution {

    // 가장 큰 누적합과 가장 작은 누적합을 구해서 둘 차이의 절댓값 구하면 된다.
    // 가장 작은 게 가장 큰 거보다 뒤에 있다면 다른 펄스수열과 대칭이므로 그 펼스수열에서 가능하다.
    private long[] sum;
    public long solution(int[] sequence) {
        sum = new long[sequence.length];

        // 펄스 수열 만들기
        for(int i = 0; i < sequence.length; i++) {
            if(i % 2 == 1) {
                sequence[i] *= -1;
            }
        }

        // 누적합 구하면서 최소값, 최댓값 구하기
        sum[0] = sequence[0];
        long min = Math.min(0, sum[0]); // 부분 수열에 첫 원소도 포함할 경우 0도 누적합 될 수 있다.
        long max = Math.max(0, sum[0]);
        for(int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + sequence[i];
            // System.out.println(sum[i]);
            min = Math.min(min, sum[i]);
            max = Math.max(max, sum[i]);
        }

        long answer = Math.max(max - min, min - max);
        return answer;
    }
}