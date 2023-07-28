class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "";
        
        for(int i = 0;i < musicinfos.length; i++) {
            String[] split = new String[4];
            split = musicinfos[i].split(",");
            
            int l = calculateMusicLength(split[0], split[1]);
            String title = split[2];
            String melody = split[3];
            if(isRight(m, l, melody)) {
                answer = title;
                break;
            }
        }
        return answer;
    }
    
    public int calculateMusicLength(String start, String end) {
        
    }
    
    public boolean isRight(String m, int l, String melody) {
        
    }
}