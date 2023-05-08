import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        HashMap<String, Integer> hm = new HashMap<>();
        for(String number: phone_book) {
            hm.put(number, 0);
        }
        
        for(int i = 0; i < phone_book.length; i++) {
            for(int j = 1; j < phone_book[i].length(); j++) {
                    String cut = phone_book[i].substring(0, j);
                    if(hm.containsKey(cut)) return false;
            }
        }
        return true;
    }
}