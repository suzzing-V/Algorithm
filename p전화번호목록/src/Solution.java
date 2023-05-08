import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        HashMap<String, Integer> hm = new HashMap<>();
        for(String number: phone_book) {
            hm.put(number, number.length());
        }
        
        for(int i = 0; i < phone_book.length; i++) {
            int length = hm.get(phone_book[i]);
            for(String number : phone_book) {
                if(number.length() > length) {
                    String cut = number.substring(0, length);
                    if(hm.containsKey(cut)) return false;
                }
            }
        }
        return true;
    }
}