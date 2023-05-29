import java.util.*;

class Bridge {
    int is1;
    int is2;
    int cost;
    Bridge(int island1, int island2, int cost) {
        this.is1 = island1;
        this.is2 = island2;
        this.cost = cost;
    }
}
class Solution {
    public int solution(int n, int[][] costs) {
        int answer = 0;
        Arrays.sort(costs, new Comparator<int[]> () { //cost 적은 순으로 정렬
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        List<Bridge> list = new ArrayList<>();
        for(int i = 0; i < costs.length; i++) { //costs 각각의 요소 Bridge로 만들기
            Bridge tmp = new Bridge(costs[i][0], costs[i][1], costs[i][2]); 
            list.add(tmp);
        }
        
        Set<Integer> used = new HashSet<>();
        int i = 0;
        int count = 0;
        while(count < n - 1) {
            if(i >= list.size()) { //끝까지 돌아도 used 안에 들어있는 요소를 포함한 객체가 없을 때
                count++;
                //System.out.println(list.get(0).cost);
                answer += list.get(0).cost; //제일 cost 작은 객체 선택
                used.add(list.get(0).is1);
                used.add(list.get(0).is2);
                list.remove(0);
                i = -1;
            }
            else if(used.contains(list.get(i).is1) || used.contains(list.get(i).is2)) {
                count ++;
                //System.out.println("is1 cost: " + list.get(i).is1 + " " + list.get(i).cost);
                answer += list.get(i).cost;
                used.add(list.get(i).is1);
                used.add(list.get(i).is2);
                list.remove(i);
                i = -1;
            }
            i++;
        }
        return answer;
    }
}