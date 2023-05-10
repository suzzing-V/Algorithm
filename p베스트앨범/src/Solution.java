import java.util.*;

class Song {
    int index;
    String genre;
    int play;
    
    public Song(int index, String genre, int play) {
        this.index = index;
        this.genre = genre;
        this.play = play;
    }
}

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        List<Song> answer = new ArrayList<>();
        
        HashMap<String, Integer> total = new HashMap<>();
        for(int i = 0; i < plays.length; i++) {
            total.put(genres[i], total.getOrDefault(genres[i], 0) + plays[i]);
        }
        List<String> geOrder = new ArrayList<>();
        Set<String> keySet = total.keySet();
        while(keySet.size() != 0) {
            int max = 0;
            String maxGe = "";
            Iterator<String> iterator = keySet.iterator();
            while(iterator.hasNext()) {
                String key = iterator.next();
                if(total.get(key) > max) {
                    max = total.get(key);
                    maxGe = key;
                }
            }
            geOrder.add(maxGe);
            keySet.remove(maxGe);
        }

        Iterator<String> iterator2 = geOrder.iterator();
        while(iterator2.hasNext()) {
            String ge = iterator2.next();
            List<Song> tmp = new ArrayList<>();
            for(int i = 0; i < genres.length; i ++) {
                if(ge.equals(genres[i])) {
                    tmp.add(new Song(i, genres[i], plays[i]));
                }
            }
        
            Collections.sort(tmp, (o1, o2) -> o2.play - o1.play);
            answer.add(tmp.get(0));
            if(tmp.size() > 1) {
                answer.add(tmp.get(1));
            }
        }
        
        int[] result = new int[answer.size()];
        for(int i=0; i<answer.size(); i++){
            result[i] = answer.get(i).index;
        }
        
        return result;
    }
}