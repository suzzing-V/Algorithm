import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        List<String> setA = new ArrayList<>();
        List<String> setB = new ArrayList<>();
        
        getSet(str1, setA);
        getSet(str2, setB);
        double unionSize = setA.size() + setB.size();
        System.out.println("unionSize: " + unionSize);
        if(setA.size() == 0 && setB.size() == 0) answer = 65536;
        else answer = (int)((getIntersectionSize(setA, setB) / unionSize) * (double)65536);
        return answer;
    }
    
    public void getSet(String str, List<String> set) {
        for(int i = 0; i < str.length() - 1; i++) {
            char c1 = str.charAt(i);
            char c2 = str.charAt(i + 1);
            if(!((c1 >= 'A' && c1 <= 'Z') || (c1 >= 'a' && c1 <= 'z'))) continue;
            if(!((c2 >= 'A' && c2 <= 'Z') || (c2 >= 'a' && c2 <= 'z'))) continue;
            if(c1 >= 'a' && c1 <= 'z') c1 -= 32;
            if(c2 >= 'a' && c2 <= 'z') c2 -= 32;
            
            System.out.println(String.valueOf(c1) + String.valueOf(c2));
            set.add(String.valueOf(c1) + String.valueOf(c2));
        }
    }
    
    public int getIntersectionSize(List<String> setA, List<String> setB) {
        int count = 0;
        
        for(String token : setA) {
            if(setB.contains(token)) {
                System.out.println("교집합: " + token);
                setB.remove(token);
                count++;
            }
        }
        System.out.println("count: " + count);
        return count;
    }
}