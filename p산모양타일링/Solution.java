class Solution {
    public int solution(int n, int[] tops) {
        int[] mo = new int[n];
        int[] mx = new int[n];

        mo[0] = 1;
        mx[0] = tops[0] == 1 ? 3 : 2;

        for(int i = 1; i < n; i ++) {
            mo[i] = (mo[i - 1] + mx[i - 1]) % 10007;
            if(tops[i] == 1) {
                mx[i] = (mx[i - 1] * 3 + mo[i - 1] * 2) % 10007;
            } else {
                mx[i] = (mx[i - 1] * 2 + mo[i - 1]) % 10007;
            }
        }
        return (mo[n - 1] + mx[n - 1]) % 10007;
    }
}
