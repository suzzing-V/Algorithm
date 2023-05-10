import java.util.*;

class Song {
    public int index;
    public String genre;
    
    public Song(int index, String genre) {
        this.index = index;
        this.genre = genre;
    }
}

class Solution {
	public static void main(String[] args) {
		String[] genres = { "classic", "pop", "classic", "classic", "pop" };
		int[] plays = { 500, 600, 150, 800, 2500 };
		int[] res = solution(genres, plays);
	}
	
    public static int[] solution(String[] genres, int[] plays) {
    	 List<Integer> answer = new ArrayList<>();
         HashMap<Integer, Song> hm = new HashMap<>();
         for(int i = 0; i < plays.length; i++) {
             hm.put(plays[i], new Song(i, genres[i]));
         }
         
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
                 String genre = iterator.next();
                 if(total.get(genre) > max) {
                     max = total.get(genre);
                     maxGe = genre;
                 }
             }
             geOrder.add(maxGe);
             keySet.remove(maxGe);
            }
         Arrays.sort(plays);
         Iterator<String> iterator2 = geOrder.iterator();
         while(iterator2.hasNext()) {
             String genre = iterator2.next();
             List<Integer> tmp = new ArrayList<>();
             for(int i = plays.length - 1; i >= 0; i--) {
                 if(genre.equals(hm.get(plays[i]).genre)) {
                     tmp.add(hm.get(plays[i]).index);
                 }
                 if(tmp.size() == 2) break;
             }
         
             for(int i : tmp) {
                 answer.add(i);
             }
         }
         
         int[] result = answer.stream()
             .mapToInt(Integer :: intValue)
             .toArray();
         return result;
    }
}