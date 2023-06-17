import java.util.*;

class Solution {
    public class Request {
        char c1;
        char c2;
        int max = 0;
        int min = 5;
        Request(char c1, char c2, int min, int max) {
            this.c1 = c1;
            this.c2 = c2;
            this.max = Math.max(max, this.max);
            this.min = Math.min(min, this.min);
        }
    }
    
    List<Request> rList = new ArrayList<>();
    char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    Integer[] arr = new Integer[8];
    boolean[] visit = new boolean[8];
    int count = 0;
    public int solution(int n, String[] data) {
        int index;
        for(int i = 0; i < data.length; i++) {
            char ch1 = data[i].charAt(0);
            char ch2 = data[i].charAt(2);
            char sign = data[i].charAt(3);
            int num = data[i].charAt(4) - '0';
            
            if((index = listContains(ch1, ch2)) >= 0) {
                if(!changeRequest(index, sign, num)) return 0;
            } else {
                if(sign == '=') {
                    rList.add(new Request(ch1, ch2, num, num));
                } else if(sign == '<') {
                    if(num != 0)
                        rList.add(new Request(ch1, ch2, 0, num - 1));
                    else return 0;
                } else {
                    if(num != 5)
                        rList.add(new Request(ch1, ch2, num + 1, 5));
                    else return 0;
                }
            }
        }
        
        for(int i = 0; i < 8; i++) {
            visit[i] = true;
            makeArray(0, i);
            visit[i] = false;
        }
        return count;
    }
    
    public int listContains(char ch1, char ch2) {
        for(int i = 0; i < rList.size(); i++) {
            Request tmp = rList.get(i);
            if((ch1 == tmp.c1 && ch2 == tmp.c2) || (ch1 == tmp.c2 && ch2 == tmp.c1)) return i;
        }
        return -1;
    }
    
    public boolean changeRequest(int index, char sign, int num) {
        Request tmp = rList.get(index);
        if(sign == '<') {
            if(tmp.max >= num) tmp.max = num - 1;
            else if(tmp.min >= num) return false;
        } else if(sign == '>') {
            if(tmp.min <= num) tmp.min = num + 1;
            else if(tmp.max <= num) return false;
        } else {
            if(num >= tmp.min && num <= tmp.max) {
                tmp.max = num;
                tmp.min = num;
            } else return false;
        }
        return true;
    }
    
    public void makeArray(int arrIndex, int friIndex) {
        arr[arrIndex] = (Integer)(int)friends[friIndex];
        if(arrIndex == 7) {
            count += checkArray();
            return;
        }
        for(int i = 0; i < 8; i++) {
            if(!visit[i]){
                visit[i] = true;
                makeArray(arrIndex + 1, i);
                visit[i] = false;
            }
        }
    }
    
    public int checkArray() {
        List<Integer> list = Arrays.asList(arr);
        for(Request tmp : rList) {
            int distance = Math.abs(list.indexOf((Integer)(int)tmp.c1) - list.indexOf((Integer)(int)tmp.c2)) - 1;
            if(!(distance <= tmp.max && distance >= tmp.min)) {
                return 0;
            }
        }
        return 1;
    }
}