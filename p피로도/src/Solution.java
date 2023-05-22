class Solution {
    public int answer = 0;
    public int solution(int k, int[][] dungeons) {
        int[] count = new int[dungeons.length];
        addDigit("", dungeons, k, count);
        return answer;
    }
    
    public void addDigit(String str, int[][] dungeons, int k, int[] count) {
        if(str.length() == dungeons.length) { //자릿수 다 채우면 멈춤
            int tmp = getMaxDun(str, dungeons, k); //해당 순서로 던전 돌아서 최대 던전수 구하기
            if(tmp > answer) answer = tmp;
            return;
        }
        
        for(int i = 0; i < dungeons.length; i++) {
            if(count[i] == 0) {
                count[i] ++; //이 숫자는 썼다는 지표
                addDigit(str + Integer.toString(i), dungeons, k, count);
                count[i] --; //초기화
            }
        }
        return;
    }
    
    public int getMaxDun(String str, int[][] dungeons, int k) {
        int max = 0;
        for(int i = 0; i < str.length(); i++) {
            int digit = str.charAt(i) - 48;
            if(dungeons[digit][0] > k) break;
            k -= dungeons[digit][1];
            max++;
        }
        return max;
    }
}