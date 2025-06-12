import java.util.*;

// 새로운 카드들 다 모아놓고 필요할 때 그때 미리 사놓은 거로 치기
class Solution {

    private List<Integer> notPicked = new ArrayList<>();
    private List<Integer> init = new ArrayList<>();
    private int n;

    public int solution(int coin, int[] cards) {
        n = cards.length;

        // 초기 카드 담기
        for(int i = 0; i < n / 3; i++) {
            init.add(cards[i]);
        }

        // 매 라운드마다 동전 가장 적게 사용하는 쪽 선택하기
        int idx = n / 3;
        int round = 0;

        while(true) {
            round ++;

            if(idx == n) { // 남은 카드뭉치가 없을 경우 게임 종료
                break;
            }
            // 뽑지 않은 카드 리스트에 새로운 카드 2개 추가
            notPicked.add(cards[idx]);
            notPicked.add(cards[idx + 1]);

            // 코인 0개 사용 : 초기 카드에서 2개 뽑기
            if(canMakeInOneList(init)) {

                // 코인 1개 사용 : 초기 카드에서 1개, 뽑지 않은 카드에서 1개 사용
            } else if(canMakeInTwoList(init, notPicked)) {
                coin -= 1;

                // 코인 2개 사용 : 뽑지 않은 카드에서 2개 사용
            } else if(canMakeInOneList(notPicked)) {
                coin -= 2;

                // 다 불가능하면 더이상 n + 1을 만들 수 없으므로 게임 종료
            } else {
                break;
            }

            // 코인이 부족하면 게임 종료
            if(coin < 0) break;

            idx += 2;
        }

        return round;
    }

    private boolean canMakeInOneList(List<Integer> list) {
        for(int i : list) {
            if(list.contains(n + 1 - i)) {
                list.remove((Integer)i);
                list.remove((Integer)(n + 1 - i));
                return true;
            }
        }

        return false;
    }

    private boolean canMakeInTwoList(List<Integer> list1, List<Integer> list2) {
        for(int i : list1) {
            if(list2.contains(n + 1 - i)) {
                list1.remove((Integer)i);
                list2.remove((Integer)(n + 1 - i));
                return true;
            }
        }

        return false;
    }
}