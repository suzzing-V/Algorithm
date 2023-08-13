public class Solution {
    public class Road {
        int startX = 0;
        int startY = 0;
        int endX = 0;
        int endY = 0;
        
        road(int startX, int startY, int endX, int endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }
        
        public boolean equals(Object obj) {
            if(obj instanceof Road) {
                Road tmp = (Road) obj;
                return (tmp.startX == this.startX && tmp.startX == this.startY)
                    && (tmp.start)
            }
        }
    }
    public int solution(String dirs) {
        int answer = 0;
        
        for(int i = 0; i < dirs.length(); i++) {
            answer += moveChar(dirs.charAt(i), )
        } 
        return answer;
    }
} {
    
}
