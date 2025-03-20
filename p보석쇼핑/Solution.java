import java.util.*;

class Solution {
    private static Set<String> set = new HashSet<>();
    private static Map<String, Integer> jewels = new HashMap<>();

    public int[] solution(String[] gems) {
        int left = 0;
        int right = 0;
        for(int i = 0; i < gems.length; i++) { // 보석 종류 세기
            set.add(gems[i]);
        }

        int[] answer = {0, gems.length - 1};

        int kind = set.size();
        jewels.put(gems[0], 1);
        while(true) {
            if(jewels.size() == kind) { // 현재 선택한 구간 안의 보석 수가 보석 종류와 같으면 길이가 최소값일 경우 갱신하고 왼쪽 포인터 +1
                // System.out.println(left + " "+ right);
                if(answer[1] - answer[0] > right - left) {
                    answer[1] = right;
                    answer[0] = left;
                }

                if(jewels.get(gems[left]) == 1) jewels.remove(gems[left]);
                else jewels.put(gems[left], jewels.get(gems[left]) - 1);
                left ++;
            } else { // 충족되지 않으면 오른쪽 포인터 늘리기
                right ++;
                if(right == gems.length) break; // 오른쪽 포인터가 범위 넘었다는 건 그때까지 충족하지 못했다는 뜻이다. 아무리 왼쪽 포인터를 조정해도 충족할 수 없으므로 멈춘다.
                jewels.put(gems[right], jewels.getOrDefault(gems[right], 0) + 1);
            }
        }

        answer[0] ++;
        answer[1] ++;
        return answer;
    }
}
