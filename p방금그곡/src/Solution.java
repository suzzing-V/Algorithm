class Solution {
    public String solution(String m, String[] musicinfos) {
        
        String answer = "";
        int max = 0;
        
        m = changeSharp(m);
        for(int i = 0;i < musicinfos.length; i++) {
            String[] split = new String[4];
            split = musicinfos[i].split(",");
            
            int l = calculateMusicLength(split[0], split[1]);
            String title = split[2];
            String melody = changeSharp(split[3]);
            if(isRight(m, l, melody) && l > max) {
                answer = title;
                max = l;
            }
        }
        
        if(answer.equals("")) return "(None)";
        return answer;
    }
    
    public String changeSharp(String str) {
        String result = "";
        
        for(int i = 0; i < str.length(); i++) {
            if(i != str.length() - 1 && str.charAt(i + 1) == '#') {
                result += String.valueOf((char)(str.charAt(i) + 32));
                i++;
            } else {
                result += String.valueOf(str.charAt(i));
            } 
        }
        return result;
    }
    
    public int calculateMusicLength(String start, String end) {
        
        String[] split = new String[2];
        split = start.split(":");
        int startMinute = Integer.parseInt(split[0]);
        int startSecond = Integer.parseInt(split[1]);
        
        split = end.split(":");
        int endMinute = Integer.parseInt(split[0]);
        int endSecond = Integer.parseInt(split[1]);
        
        
        int minute = endMinute - startMinute;
        if(startSecond > endSecond) {
            endSecond += 60;
            minute --;
        }
        int second = endSecond - startSecond;
        
        return minute * 60 + second;
    }
    
    public boolean isRight(String m, int l, String melody) {
        int i = 0;
        while(l > 0 && i < melody.length()) {
            if(m.charAt(0) == melody.charAt(i) && isSame(m, melody, i, l)) return true;
            l--;
            i++;
        }
        
        return false;
    }
    
    public boolean isSame(String m, String melody, int i, int l) {
        int mIndex = 0;
        while(mIndex < m.length() && l > 0) {
            if(m.charAt(mIndex) != melody.charAt(i)) return false;
            mIndex++;
            l--;
            i++;
            if(i == melody.length()) i = 0;
        }
        
        if(mIndex == m.length()) return true;
        return false;
    }
}