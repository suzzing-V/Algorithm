import java.util.*;

class Solution {
    public int solution(String[][] relation) {
        List<String> canKey = new ArrayList<>();
        String form = makeForm(relation[0].length);
        dfs(0, "", form, canKey, relation);
        return canKey.size();
    }
    
    public void dfs(int i, String contain, String form, List<String> canKey, String[][] relation) {
        if(i == form.length()) {
            if(!checkMinimum(canKey, contain) && !contain.equals("")) {
                if(checkUnique(relation, contain)) canKey.add(contain);
            }
            return;
        }
        
        dfs(i + 1, contain, form, canKey, relation);
        dfs(i + 1, contain + String.valueOf(form.charAt(i)), form, canKey, relation);
    }
    
    public String makeForm(int size) {
        String result = "";
        for(int i = 0; i < size; i++) {
            result += String.valueOf(i);
        }
        return result;
    }
    
    public boolean checkMinimum(List<String> canKey, String str) {
        for(String key : canKey) {
            int count = 0;
            for(int i = 0; i < str.length(); i++) {
                if(key.contains(String.valueOf(str.charAt(i)))) count++;
            }
            if(count == key.length()) return true;
        }
        return false;
    }
    
    public boolean checkUnique(String[][] relation, String contain) {
        HashSet<String> set = new HashSet<>();
        for(int i = 0; i < relation.length; i++) {
            String tuple = "";
            for(int j = 0; j < relation[0].length; j++) {
                if(contain.contains(String.valueOf(j))) {
                    tuple += relation[i][j];
                }
            }
            if(set.contains(tuple)) return false;
            else set.add(tuple);
        }
        return true;
    }
}