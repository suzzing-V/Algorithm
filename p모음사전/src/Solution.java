class Solution {
    public int solution(String word) {
        int num = 0;
        int[] position = new int[5];
        position[4] = 1;
        for(int i = 3; i >= 0; i--) { //자리수마다 한 글자씩 지날 때마다 지나는 단어 수
            position[i] = position[i + 1] + (int)Math.pow(5, 4 - i);
        }
        
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(c == 'A') num += 0;
            else if(c == 'E') num += position[i] * 1;
            else if(c == 'I') num += position[i] * 2;
            else if(c == 'O') num += position[i] * 3;
            else if(c == 'U') num += position[i] * 4;
            num += 1; //세고 한자리 건너야함
        }
        return num;
    }
}