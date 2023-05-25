class Solution {
    public int solution(String name) {
        String[] str = new String[name.length()];
        str = name.split("");
        int lr = str.length - 1; //좌우 이동 수 //최대 좌우 이동 수를 넣어야 최소값 구할 수 있음
        int ud = 0; //상하 이동 수
        
        for(int i = 0; i < str.length; i++) {
            ud += Math.min(str[i].charAt(0) - 'A', 'Z' - str[i].charAt(0) + 1);
            //위나 아래 중 어디로 이동해야 최소 이동인지
            int k = i + 1;
            if(i + 1 < str.length && str[i+1].charAt(0) == 'A') { //다음글자가 A일 경우
                //A가 아닐경우는 최소 경우가 나올 수 없다 //돌아갈 필요가 없기 때문
                while(str[k].charAt(0) == 'A') k++; //A가 아닌 지점까지
                lr = Math.min(i * 2 + str.length - k, lr); //처음부터 i까지 왔다가 다시 뒤로 돌아가는 경우
                lr = Math.min(i + (str.length - k) * 2, lr); //처음부터 뒤로 갔다가 i까지 가는 경우
            }
        }
        return lr + ud;
    }
}