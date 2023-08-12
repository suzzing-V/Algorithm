class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        for(int i = 0; i < skill_trees.length; i++) {
            if(isPossible(skill_trees, skill, i)) answer++;
        }
        return answer;
    }
    
    public boolean isPossible(String[] skill_trees, String skill, int i) {
        int index = 0;
        for(int j = 0; j < skill_trees[i].length(); j++) {
            char alpha = skill_trees[i].charAt(j);
            if(skill.contains(String.valueOf(alpha))) {
                if(alpha != skill.charAt(index)) return false;
                else {
                    index++;
                    if(index >= skill.length()) return true;
                }
            }
        }
        return true;
    }
}