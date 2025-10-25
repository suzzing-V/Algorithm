import java.util.*;

// 시간복잡도: 탐색을 진행할 때마다 탐색 시작점을 최대한 뒤로 미루므로 빠르다.
// k가 10^12이기 때문에 배열에도 저장할 수 없고, O(n)도 불가능하다.
// 따라서 맵에 (방 번호, 이 방을 선택했을 때 줄 방 번호)를 저장한다.
// 만약 어떤 방을 원했을 때 그 방이 맵에 존재하지 않으면, 그 방은 아직 선점되지 않은 것이다. 따라서 맵에 이 방의 번호와 다음에 이 방이 선택됐을 때 다음 방을 주도록 이방번호 + 1을 저장해준다.
// 그 방이 맵에 존재하면, 그 방은 이미 선점된 것이다. 따라서 다음 방을 본다. 하지만 그 다음 방도 비어있지 않을 수 있다.
// 재귀적으로 다음 방이 비어있을 때까지 탐색한다. 다음 방이 비어있으면 그 방을 선택하고, 그 방을 선점 처리 하면서 이 방의 다음 방을 이방 + 1로 저장한다. 재귀를 빠져나오면서 건너왔던 방들의 다음 방 정보를 선택된 방 + 1로 업데이트해준다.
class Solution {

    private Map<Long, Long> reserve = new HashMap<>();

    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        for(int i = 0; i < room_number.length; i++) {
            // 아직 비어있다면 이 방 차지하고 다음 줄 방 저장
            if(!reserve.containsKey(room_number[i])) {
                reserve.put(room_number[i], room_number[i] + 1);
                answer[i] = room_number[i];
            } else {
                // 비어있지 않다면 다음 줄 방이 비어있을 떄까지 재귀 돌린다. 그리고 재귀 빠져나오는 동안 그 키의 값에 선택된 방 + 1의 값을 넣는다.
                answer[i] = findVoid(reserve.get(room_number[i]));
            }
        }

        return answer;
    }

    private long findVoid(long curr) {
        // 현재 방이 비어있다면 이 방 선택
        if(!reserve.containsKey(curr)) {
            reserve.put(curr, curr + 1);
            return curr;
        }

        // 현재 방이 비어있지 않다면 그 다음 방 선택
        long selected = findVoid(reserve.get(curr));
        // 선택된 방의 다음 방을 현재 방을 원할 때 줄 다음 방으로 저장
        reserve.put(curr, selected + 1);
        return selected;
    }
}
