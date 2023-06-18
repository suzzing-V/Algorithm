import java.util.*;

class Solution {
    char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    Integer[] arr = new Integer[8];
    boolean[] visit = new boolean[8];
    int count = 0;
    public int solution(int n, String[] data) {
        for(int i = 0; i < 8; i++) {
            visit[i] = true;
            makeArray(0, i, data);
            visit[i] = false;
        }
        return count;
    }
    
    public void makeArray(int arrIndex, int friIndex, String[] data) {
        arr[arrIndex] = (Integer)(int)friends[friIndex];
        if(arrIndex == 7) {
            count += checkArray(data);
            return;
        }
        for(int i = 0; i < 8; i++) {
            if(!visit[i]){
                visit[i] = true;
                makeArray(arrIndex + 1, i, data);
                visit[i] = false;
            }
        }
    }
    
    public int checkArray(String[] data) {
        List<Integer> list = Arrays.asList(arr);
        for(String request : data) {
            char ch1 = request.charAt(0);
            char ch2 = request.charAt(2);
            char sign = request.charAt(3);
            int num = request.charAt(4) - '0';
            int distance = Math.abs(list.indexOf((Integer)(int)ch1) - list.indexOf((Integer)(int)ch2)) - 1;
            if(sign == '=') {
                if(distance != num) return 0;
            } else if(sign == '<') {
                if(distance >= num) return 0;
            } else if(sign == '>') {
                if(distance <= num) return 0;
            }
        }
        return 1;
    }
}