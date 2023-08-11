import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        HashMap<String, String> users = getUsers(record);
        List<String> list = getMessage(users, record);
        String[] result = list.toArray(new String[list.size()]);
        return result;
    }
    
    public HashMap<String, String> getUsers(String[] record) {
        HashMap<String, String> map = new HashMap<>();
        
        for(int i = 0; i < record.length; i++) {
            String[] cut = new String[3];
            cut = record[i].split(" ");
            if(cut[0].equals("Enter") || cut[0].equals("Change")) 
                map.put(cut[1], cut[2]);
        }
        return map;
    }
    
    public List<String> getMessage(HashMap<String, String> users, String[] record) {
        List<String> list = new ArrayList<>();
        
        for(int i = 0; i < record.length; i++) {
            String[] cut = new String[3];
            cut = record[i].split(" ");
            String nickname = users.get(cut[1]);
            if(cut[0].equals("Enter")) 
                list.add(nickname + "´ÔÀÌ µé¾î¿Ô½À´Ï´Ù.");
            else if(cut[0].equals("Leave"))
                list.add(nickname + "´ÔÀÌ ³ª°¬½À´Ï´Ù.");
        }
        return list;
    }
}