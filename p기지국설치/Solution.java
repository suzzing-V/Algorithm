import java.util.*;

class Solution {

    private int cnt = 0;
    private int w;

    public int solution(int n, int[] stations, int w1) {
        w = w1;

        // 맨 처음 기지의 앞 부분 처리
        int void_len = stations[0] - w - 1;
        int range = w * 2 + 1;
        if(void_len > 0) cnt += void_len % range == 0? void_len / range : void_len / range + 1;

        // 전파 영향 끼치지 않는 범위 구하고 그 범위에 설치할 수 있는 최소 기지국 수 cnt에 더하기
        // 범위에 설치할 수 있는 최소 기지국 수 = 범위 길이가 (w * 2 + 1)의 배수이면 범위길이/(w * 2 + 1) 의 몫, 배수가 아니면 몫 + 1
        for(int i = 0; i < stations.length - 1; i++) {
            int start = stations[i] + w;
            int end = stations[i + 1] - w;
            void_len = end - start - 1;
            // 이미 설치된 기지국 사이에 빈 부분이 없으면. 전파 영향이 겹치거나 빈부분이 없으면 설치할 필요 없음
            if(void_len >= 0) cnt += void_len % range == 0? void_len / range : void_len / range + 1;
        }

        // 마지막 기지국부터 끝까지 처리
        void_len = n - (stations[stations.length - 1] + w);
        if(void_len > 0) cnt += void_len % range == 0? void_len / range : void_len / range + 1;

        return cnt;
    }
}
