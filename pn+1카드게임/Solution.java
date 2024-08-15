import java.util.*;

class Solution {
    List<Integer> myCards = new ArrayList<>();
    int n;

    public int solution(int coin, int[] cards) {
        n = cards.length;

        for(int i = 0; i < n / 3; i++) {
            myCards.add(cards[i]);
        }

        List<Integer> picked = new ArrayList<>();
        return game(n / 3, coin, 1, picked, cards);
    }

    public int game(int idx, int coin, int round, List<Integer> picked, int[] cards) {
        if(idx >= n) {
            return round;
        }

        int first = cards[idx];
        int second = cards[idx + 1];

        picked.add(first);
        picked.add(second);
        // 기존 페어 내기
        for(int i = 0; i < myCards.size() - 1; i++) {
            for(int j = i + 1; j < myCards.size(); j++) {
                int a = myCards.get(i);
                int b = myCards.get(j);
                int sum = a + b;
                if(sum == n + 1) {
                    myCards.remove((Integer)a);
                    myCards.remove((Integer)b);
                    return game(idx + 2, coin, round + 1, picked, cards);
                }
            }
        }

        if(coin >= 1) { // 카드 1장 뽑기
            for(int i = 0; i < myCards.size(); i++) {
                for(int j = 0; j < picked.size(); j++) {
                    int a = myCards.get(i);
                    int b = picked.get(j);
                    int sum = a + b;
                    if(sum == n + 1) {
                        myCards.remove((Integer)a);
                        picked.remove((Integer)b);
                        return game(idx + 2, coin - 1, round + 1, picked, cards);
                    }
                }
            }
        }

        if(coin >= 2) { // 카드 2장 가지기
            for(int i = 0; i < picked.size() - 1; i++) {
                for(int j = i + 1; j < picked.size(); j++) {
                    int a = picked.get(i);
                    int b = picked.get(j);
                    int sum = a + b;
                    if(sum == n + 1) {
                        picked.remove((Integer)a);
                        picked.remove((Integer)b);
                        return game(idx + 2, coin - 2, round + 1, picked, cards);
                    }
                }
            }
        }

        return round;
    }
}