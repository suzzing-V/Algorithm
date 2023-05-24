class Solution {
    public int solution(String word) {
        int num = 0;
        int[] position = new int[5];
        position[4] = 1;
        for(int i = 3; i >= 0; i--) { //자리수마다 한 글자씩 지날 때마다 지나는 단어 수
            position[i] = position[i + 1] + (int)Math.pow(5, 4 - i);
        }
        
        String[] wordS = new String[word.length() - 1];
        wordS = word.split("");
        
        for(int i = 0; i < word.length(); i++) {
            num += position[i] * "AEIOU".indexOf(wordS[i]) + 1;
        }
        return num;
    }
}