import java.util.*;

class Solution {
    public int solution(int N, int number) {
        List<Set<Integer>> list = new ArrayList<>();
        list.add(new HashSet<Integer>());
        list.get(0).add(0);
        list.add(new HashSet<Integer>());
        list.get(1).add(N);
        if(list.get(1).contains(number)) return 1;
        
        for(int i = 2; i <= 8; i++) {
            list.add(new HashSet<Integer>());
            String r = "";
            for(int k = 0; k < i; k++) r += Integer.toString(N);
            list.get(i).add(Integer.parseInt(r));
            for(int j = 1; j < i; j++) {
                putValue(list.get(i), list.get(j), list.get(i - j));
            }
            
            if(list.get(i).contains(number)) return i;
        }
        return -1;
    }
    
    public Set<Integer> putValue(Set<Integer> tmp, Set<Integer> set1, Set<Integer> set2) {
        for(int i : set1) {
            for(int j : set2) {
                tmp.add(i + j);
                tmp.add(i - j);
                tmp.add(i * j);
                if(j != 0) tmp.add(i / j);
            }
        }
        return tmp;
    }
}