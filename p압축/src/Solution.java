import java.util.*;

class Solution {
    public int[] solution(String msg) {
        HashMap<String, Integer> dict = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        putAlpha(dict);
        LZW(dict, list, msg, 0, 1);
        
        int[] answer = list.stream()
            .mapToInt(i -> i)
            .toArray();   
        return answer;
    }
    
    public void putAlpha(HashMap<String, Integer> dict) {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String[] alphaArr = new String[alpha.length()];
        alphaArr = alpha.split("");
        for(int i = 0; i < alpha.length(); i++) {
            dict.put(alphaArr[i], i + 1);
        }
    }
    
    public void LZW(HashMap<String, Integer> dict, List<Integer> list, String msg, int start, int end) {
        if(start > msg.length() - 1) return;
        else if(end > msg.length()) {
            String tmp = msg.substring(start, end - 1);
            // System.out.println("arr: " + tmp + " " + dict.get(tmp));
            list.add(dict.get(tmp));
            return;
        }
        
        String tmp = msg.substring(start, end);
        // System.out.println("tmp: " + tmp);
        if(dict.containsKey(tmp)) {
            // System.out.println(tmp + "존재");
            LZW(dict, list, msg, start, end + 1);
        } else {
            // System.out.println(tmp + "존재안함");
            dict.put(tmp, dict.size() + 1);
            tmp = msg.substring(start, end - 1);
            // System.out.println("arr: " + tmp + " " + dict.get(tmp));
            list.add(dict.get(tmp));
            LZW(dict, list, msg, end - 1, end);
        }
    }
}
