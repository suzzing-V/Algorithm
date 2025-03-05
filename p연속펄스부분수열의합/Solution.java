class Solution {

    private long[] acc1;
    private long[] acc2;
    private long[] pulse1;
    private long[] pulse2;
    public long solution(int[] sequence) {
        pulse1 = new long[sequence.length + 1];
        pulse2 = new long[sequence.length + 1];
        acc1 = new long[sequence.length + 1];
        acc2 = new long[sequence.length + 1];

        long max1 = Long.MIN_VALUE;
        int idx1 = 0;
        long max2 = Long.MIN_VALUE;
        int idx2 = 0;
        for(int i = 1; i <= sequence.length; i++) {
            if(i % 2 == 0) {
                pulse1[i] = sequence[i - 1];
                pulse2[i] = - sequence[i - 1];
            } else {
                pulse1[i] = - sequence[i - 1];
                pulse2[i] = sequence[i - 1];
            }
        }

        for(int i = 1; i <= sequence.length;i ++) {
            acc1[i] = acc1[i - 1] + pulse1[i];
            acc2[i] = acc2[i - 1] + pulse2[i];
            if(max1 <= acc1[i]) {
                max1 = acc1[i];
                idx1 = i;
            }
            if(max2 <= acc2[i]) {
                max2 = acc2[i];
                idx2 = i;
            }
        }
        // System.out.println(max1 + " " + max2);

        long a1 = Long.MIN_VALUE;
        for(int i = 0; i < idx1; i++) {
            a1 = Math.max(max1 - acc1[i], a1);
        }
        long a2 = Long.MIN_VALUE;
        for(int i = 0; i < idx2; i++) {
            a2 = Math.max(max2 - acc2[i], a2);
        }

        return Math.max(a1, a2);
    }
}
