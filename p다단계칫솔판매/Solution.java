import java.util.*;

// 문제 잘 읽기. 처음에는 모든 이익금 다 합치고 10퍼씩 배분하는 문제인 줄 알고 dfs를 활용한 그래프로 풀었다. 하지만 수익이 날 때마다 처리를 해줘야 했다.
// 두 경우에 결과가 다른 이유는 10퍼 계산 시에 1보다 작은 경우 내가 다 가지는데, 이 계산 대상이 달라진다. 그리고 절사의 경우에도 값의 차이가 난다.
// 내가 다 수익금 가지는 경우에는 위에 더 배분할 게 없으므로 위에 더 갈 필요 없어 그냥 재귀를 끝내면 된다.

class Solution {

    private Map<String, Integer> si = new HashMap<>();
    private Map<String, Integer> result_map = new HashMap<>();
    private Map<String, Integer> total_amount = new HashMap<>();
    private String[] referral;

    public int[] solution(String[] enroll, String[] referral1, String[] seller, int[] amount) {
        int n = enroll.length;
        referral = referral1;
        // 판매원의 enroll 내 인덱스와 결과 초기값 저장
        for(int i = 0; i < n; i++) {
            si.put(enroll[i], i);
            result_map.put(enroll[i], 0);
        }

        // 이익 정보에 따라 수익 분배
        for(int i = 0; i < seller.length; i++) {
            goUp(seller[i], amount[i] * 100);
        }

        int[] answer = new int[n];
        for(int i = 0; i < n; i++) {
            answer[i] = result_map.get(enroll[i]);
        }
        return answer;
    }

    private void goUp(String seller, int price) {
        // center면 리턴
        if(seller.equals("-")) return;

        // 원 단위 절사
        int per_10 = price / 10;
        // 내 이익금의 10퍼가 1보다 작으면 내가 다 가진다 -> 나눠줄 거 없으므로 위로 더 올라갈 필요가 없다
        if(per_10 < 1) {
            result_map.put(seller, result_map.get(seller) + price);
            return;
        }

        // 내 이익금의 10퍼를 내 추천인한테 나눠준다.
        result_map.put(seller, result_map.get(seller) + price - per_10);
        goUp(referral[si.get(seller)], per_10);
    }
}