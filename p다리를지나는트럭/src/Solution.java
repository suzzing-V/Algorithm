import java.util.*;

class Solution {
	public static void main(String[] args) {
		int[] arr = {7, 4, 5, 6};
		int sol = solution(2, 10, arr);
		System.out.println(sol);
	}
	
	public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Integer> queue = new LinkedList<>();
        int i = 0;
        int sum = 0;
        
        while(i < truck_weights.length) {
            if(queue.size() == bridge_length) {
                sum -= queue.remove();
            }
            if(queue.size() < bridge_length && sum + truck_weights[i] <= weight) {
                queue.offer(truck_weights[i]);
                sum += truck_weights[i];
                i++;
            } else {
                queue.offer(0);
            }
            answer++;
        }
        return answer + bridge_length; //마지막 트럭 건너기
    }
}