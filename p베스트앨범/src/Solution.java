import java.util.*;

public class Song {
    public int index;
    public String genre;
    
    public Song(int index, String genre) {
        this.index = index;
        this.genre = genre;
    }
}

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();
        
        HashMap<Integer, Song> hm = new HashMap<>();
        
        for(int i = 0; i < plays.length; i++) {
            hm.put(plays[i], new Song(i, genres[i]));
        }
        
        Arrays.sort(plays);
        
        int k = 0;
        for(int i = plays.length - 1; i >= 0; i--) {
            if(plays[i] != 0) {
                //String ge = hm.get(plays[i]).genre;
                
                for(int j = i - 1; j >= 0; j--) {
                    if(hm.get(plays[j]).genre.equals(hm.get(plays[i]).genre)) {
                        answer.add(hm.get(plays[j]).index);
                        plays[j] = 0;
                        break;
                    }
                }
            }
        }
        
        int[] result = answer.stream()
            .mapToInt(Integer :: intValue)
            .toArray();
        return result;
    }
}