import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        LinkedList<String> link = new LinkedList<>();
        
        for(int i = 0; i < cities.length; i++) {
            String city = changeToSmall(cities[i]);
            if(link.contains(city)) {
                answer ++;
                if(!link.isEmpty() && link.size() == cacheSize) link.removeLast();
                link.addFirst(city);
            } else {
                answer += 5;
                if(!link.isEmpty() && link.size() == cacheSize) link.removeLast();
                link.addFirst(city);
            }
        }
        return answer;
    }
    
    public String changeToSmall(String str) {
        String result = "";
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') result += String.valueOf((char)(str.charAt(i) + 32));
            else result += String.valueOf(str.charAt(i));
        }
        return result;
    }
}
