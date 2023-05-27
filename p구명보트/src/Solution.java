import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        List<Integer> sort = new ArrayList<>();
        List<Integer> reverse = new ArrayList<>();
        for(int i = 0; i < people.length; i++) {
            sort.add(people[i]);
            reverse.add(people[people.length - 1 - i]);
        }

        while(!reverse.isEmpty()) {
            getBoat(limit - reverse.get(0), sort, reverse);
            answer++;
            if(!reverse.isEmpty()) {
                reverse.remove(0);
                sort.remove(sort.size() - 1);
            }
        }
        return answer;
    }
    
    public void getBoat(int rest, List<Integer> sort, List<Integer> reverse) {
        int tmp = 0;
        int index = 0;
        if(sort.isEmpty()) return ;
        if(sort.get(0) > rest) return;
        for(int i = 0; i < sort.size() - 1 && sort.get(i) <= rest; i++) {
            tmp = sort.get(i);
            index = i;
        }
        sort.remove(index);
        reverse.remove(reverse.size() - 1 - index);
        getBoat(rest - tmp, sort, reverse);
        return;
    }
}