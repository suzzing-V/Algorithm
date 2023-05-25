class Solution {
    public int solution(String name) {
        int count = 0;
        int i = 0;
        String[] str = new String[name.length()];
        str = name.split("");
        
        while(i < str.length) {
            count += Math.min(str[i].charAt(0) - 'A', 'Z' + 1 - str[i].charAt(0));
            if(i + 1 < str.length && str[i + 1].equals("A")) { //끝이 아니고 다음게 A일 경우
                int k = i;
                i++;
                while(str[i].equals("A")) {
                    i++;
                }
                count += Math.min(i - k, k + str.length - i);
                //System.out.println("A: " + count);
            } else if(i + 1 < str.length && !str[i + 1].equals("A")){ //끝이 아니고 다음게 A가 아닐 경우
                count ++;
                i ++;
            } else { i++; } //끝일 경우
        }
        return count;
    }
}