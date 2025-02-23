import java.util.*;

class Solution {

    Map<String, Integer> tenCnt = new HashMap<>();

    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int start = 0;
        int end = 9;
        for(int i = 0; i < end; i++) {
            tenCnt.put(discount[i], tenCnt.getOrDefault(discount[i], 0) + 1);
        }

        while(end < discount.length) {
            tenCnt.put(discount[end], tenCnt.getOrDefault(discount[end], 0) + 1);

            boolean isOk = true;
            for(int i = 0; i < want.length; i++) {
                if(tenCnt.getOrDefault(want[i], 0) < number[i]) {
                    isOk = false;
                    break;
                }
            }
            if(isOk) answer ++;

            if(tenCnt.get(discount[start]) == 1) {
                tenCnt.remove(discount[start]);
            } else {
                tenCnt.put(discount[start], tenCnt.get(discount[start]) - 1);
            }

            start ++;
            end ++;
        }
        return answer;
    }
}
