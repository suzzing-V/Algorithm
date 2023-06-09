import java.util.*;

class Solution {
    public class Word {
        String word;
        int count;
        
        public Word(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        int answer = bfs(begin, target, words);
        return answer;
    }
    
    public int bfs(String begin, String target, String[] words) {
        Queue<Word> queue = new LinkedList<>();
        queue.add(new Word(begin, 0));
        int n = words.length;
        boolean[] visit = new boolean[n];
        
        while(!queue.isEmpty()) {
            Word tmp = queue.poll();
            
            if(tmp.word.equals(target)) return tmp.count;
            for(int i = 0; i < n; i++) {
                if(visit[i] == true) continue;
                if(isDifferentOnlyOne(tmp.word, words[i])) {
                    visit[i] = true;
                    queue.add(new Word(words[i], tmp.count + 1));
                }
            }
        }
        return 0;
    }
    
    public boolean isDifferentOnlyOne(String word1, String word2) {
        int count = 0;
        int n = word1.length();
        for(int i = 0; i < n; i++) {
            if(word1.charAt(i) != word2.charAt(i)) count ++;
            if(count >= 2) return false;
        }
        return true;
    }
}