import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int i = 0; i < priorities.length; i++) {
            hm.put(i, priorities[i]);
        }
        
        Arrays.sort(priorities);
        List<Integer> arr = new ArrayList<>();
        for(int i = 0; i < priorities.length; i++) {
            arr.add(priorities[i]);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < priorities.length; i++) {
            queue.offer(i);
        }
        
        int answer = 0;
        while(!queue.isEmpty()) {
            if(hm.get(queue.peek()) >= arr.get(arr.size() - 1)) {
                answer++;
                if(queue.peek() == location) return answer;
                else {
                    queue.remove();
                    arr.remove(arr.size() - 1);
                }
            } else {
                int tmp = queue.remove();
                queue.offer(tmp);
            }
        }
        return answer;
    }
}