import java.util.*;

class Solution {
    PriorityQueue<Integer> rooms = new PriorityQueue<>();
    public int solution(String[][] book_time) {
        Arrays.sort(book_time, (o1, o2) -> o1[0].compareTo(o2[0]));
        for(int i = 0; i < book_time.length; i++) {
            StringTokenizer st = new StringTokenizer(book_time[i][0], ":");
            int start = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
            st = new StringTokenizer(book_time[i][1], ":");
            int end = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());

            Integer minEnd = rooms.peek();
            if(minEnd == null || minEnd > start) {
                rooms.add(end + 10);
            } else {
                rooms.remove();
                rooms.add(end + 10);
            }
        }
        return rooms.size();
    }
}