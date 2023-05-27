import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        List<Integer> reverse = new ArrayList<>();
        for(int i = 0; i < people.length; i++) {
            reverse.add(people[people.length - 1 - i]);
        }

        while(!reverse.isEmpty()) {
            int tmp = reverse.get(0);
            reverse.remove(0);
            getBoat(limit - tmp, reverse);
            answer++;
        }
        return answer;
    }
    
    public void getBoat(int rest, List<Integer> reverse) {
        if(rest == 0) return ;
        int kg = 0;
        int index = 0;
        for(int i = 0; i < reverse.size(); i++) {
            if(reverse.get(i) <= rest) {
                kg = reverse.get(i);
                index = i;
                break;
            }
        }
        if(kg == 0) return;
        else {
            reverse.remove(index);
            getBoat(rest - kg, reverse);
        }
    }
}