class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        for(int i = 1; i <= yellow; i++) {
            if(yellow % i == 0) {
                if(2 * (i + yellow / i) + 4 == brown) {
                    answer[0] = yellow / i + 2;
                    answer[1] = i + 2;
                    break;
}

}
}
        return answer;
    }
}