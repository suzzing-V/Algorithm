import java.util.*;

// 최대한 시간 줄이기
// 시간복잡도 더 꼼꼼히 고려하기
class Solution {

    private Map<Integer, Integer> referer = new HashMap<>();
    private Map<String, Integer> ids = new HashMap<>();
    private int[] result;
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        result = new int[enroll.length];

        // 사람을 번호로 치환
        for(int i = 0; i < enroll.length; i++) {
            ids.put(enroll[i], i);
        }

        // 추천인 맵에 저장
        for(int i = 0; i < referral.length; i++) {
            String name = referral[i];
            if(name.equals("-")) referer.put(i, -1);
            else referer.put(i, ids.get(name));
        }

//         // 판매액 배분
        for(int i = 0; i < seller.length; i++) {
            int curr = ids.get(seller[i]);
            int origin = amount[i] * 100;
            int ten_per = origin / 10;

            while(true) {
                int parent = referer.get(curr);
                result[curr] += origin - ten_per;
                origin = ten_per;
                if(origin == 0) { // 남은 금액 0이면 더이상 할 필요 없다.
                    break;
                }
                ten_per = origin / 10;
                curr = parent;
                if(parent == -1) {
                    break;
                }
            }
        }
        return result;
    }
}