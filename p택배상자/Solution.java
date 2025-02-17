import java.util.*;

class Solution {

    private Queue<Integer> main = new LinkedList<>();
    private Stack<Integer> sub = new Stack<>();

    public int solution(int[] order) {
        int answer = 0;
        Integer[] tmp = new Integer[order.length];
        for(int i = 0; i < order.length; i++) {
            tmp[order[i] - 1] = i + 1;
        }
        main.addAll(Arrays.asList(tmp));

        for(int i = 1; i <= order.length; i++) {
            boolean isExist = true;
            // System.out.println("i: "+ i);
            while(true) {
                int mc = main.isEmpty()? 0: main.peek();
                int sc = sub.isEmpty()? 0 : sub.peek();
                // System.out.println(mc + " " + sc);
                if(mc == i) {
                    answer ++;
                    main.remove();
                    break;
                } else if(sc == i) {
                    answer ++;
                    sub.pop();
                    break;
                } else {
                    if(main.isEmpty()) {
                        isExist = false;
                        break;
                    }
                    main.remove();
                    sub.push(mc);
                }
            }
            if(!isExist) {
                break;
            }
        }
        return answer;
    }
}