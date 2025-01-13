class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int start = toSecond(h1, m1, s1); // 초로 환산하기
        int end = toSecond(h2, m2, s2);

        int count = 0;
        if(start == 0 || start == 3600 * 12) count ++; // 반복문에서 시작시간은 확인하지 않음 -> 시작시간이 0시, 12시일 경우만 초칰과 나머지 침이 겹침. 예를 들어 35분 35초라고 해서 정확히 침이 같은 곳을 가리키지 않기 때문에 초침과 나머지 침이 겹치는 경우는 이 두 경우밖에 없다.

        // 가는 동안이나 가고 나서 겹치는지 확인 -> 시작시간은 확인하지 않고, 끝시간은 확인하기 때문에 end까지 가지 않음.
        while(start < end) {
            double curr_h = (double) start / 120 % 360; // 간 각도
            double curr_m = (double) start / 10 % 360;
            double curr_s = (double) start * 6 % 360;

            // 다음 초의 각도가 0일 경우 비교가 힘들어진다. 따라서 360으로 맞춰놓는다. 짜피 다음 턴에 0도로 다시 계산되기 때문에 후는 생각 안해도 된다.
            double next_h = ((double) start + 1) / 120 % 360 == 0? 360 : ((double) start + 1) / 120 % 360;
            double next_m = ((double) start + 1) / 10 % 360 == 0? 360 : ((double) start + 1) / 10 % 360;
            double next_s = ((double) start + 1) * 6 % 360 == 0? 360 :((double) start + 1) * 6 % 360;

            if(curr_h > curr_s && next_h <= next_s) {
                count ++;
            }
            if(curr_m > curr_s && next_m <= next_s) {
                count ++;
            }
            if(next_h == next_s && next_m == next_s) {
                count --;
            }
            start ++;
        }
        return count;
    }

    private int toSecond(int h, int m, int s) {
        return h * 3600 + m * 60 + s;
    }
}

