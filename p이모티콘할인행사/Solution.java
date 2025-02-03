import java.util.*;

class Solution {
    int maxPlus = 0;
    int maxPrice = 0;
    int emoLen;
    int[] emoticons2;
    int[][] users2;
    int[] selectSales = {10, 20, 30, 40};

    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        emoLen = emoticons.length;
        emoticons2 = emoticons;
        users2 = users;
        List<Integer> list = new ArrayList<>();
        dfs(list);
        answer[0] = maxPlus;
        answer[1] = maxPrice;
        return answer;
    }

    public void dfs(List<Integer> sales) {
        if(sales.size() == emoLen) {
            List<Double> prices = new ArrayList<>();
            for(int i = 0; i < emoLen; i++) {
                prices.add(emoticons2[i] * ((double)(100 - sales.get(i)) * 0.01));
            }

            int totalBuy = 0;
            int totalPlus = 0;
            for(int i = 0; i < users2.length; i++) {
                int buy = 0;
                for(int j = 0; j < emoLen; j++) {
                    if(sales.get(j) >= users2[i][0]) {
                        buy += prices.get(j);
                    }
                }
                // System.out.println("limit, buy: " + users2[i][1] + " " + buy);
                if(buy >= users2[i][1]) {
                    totalPlus ++;
                } else {
                    totalBuy += buy;
                }
            }
            // System.out.println(totalPlus + " " + totalBuy);
            if(maxPlus < totalPlus) {
                maxPlus = totalPlus;
                maxPrice = totalBuy;
            } else if(maxPlus == totalPlus) {
                if(maxPrice < totalBuy) {
                    maxPrice = totalBuy;
                }
            }
            return;
        }

        for(int i = 0; i < 4; i++) {
            sales.add(selectSales[i]);
            dfs(sales);
            sales.remove(sales.size() - 1);
        }
    }
}
