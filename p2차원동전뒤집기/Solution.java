import java.util.*;

class Solution {

    int min = Integer.MAX_VALUE;
    int[][] target;
    int row;
    int col;
    int[][] beginning;

    public int solution(int[][] beginning1, int[][] target1) {
        target = target1;
        beginning = beginning1;
        row = beginning.length;
        col = beginning[0].length;

        dfs(0, 0);

        if(min == Integer.MAX_VALUE) {
            return -1;
        }
        return min;
    }

    private void dfs(int map, int num) {
        if(num == (row + col)) {
            String bin = Integer.toBinaryString(map);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < (row + col) - bin.length(); i++) {
                sb.append("0");
            }
            sb.append(bin);
            bin = sb.toString();
            // System.out.println(bin);
            int[][] flip  = flip(bin);
            if(isSame(flip)) {
                min = Math.min(min, Integer.bitCount(map));
            }
            return;
        }

        dfs(map, num + 1);
        map |= (1 << num);
        dfs(map, num + 1);
    }

    private int[][] flip(String map) {
        int[][] space = new int[row][col];
        for(int i = 0; i < row; i ++) {
            for(int j = 0; j < col; j++) {
                space[i][j] = beginning[i][j];
            }
        }

        for(int i = 0; i < row; i++) {
            if(map.charAt(i) == '1') {
                for(int j = 0; j < col; j++) {
                    space[i][j] = beginning[i][j] == 1 ? 0: 1;
                }
            }
        }

        for(int i = row; i < map.length(); i++) {
            if(map.charAt(i) == '1') {
                for(int j = 0; j < row; j++) {
                    // System.out.println(space.length + " " + space[0].length + " " + beginning.length + " " + beginning[0].length);
                    space[j][i-row] = (space[j][i-row] == 1)? 0: 1;
                }
            }
        }

        // for(int i = 0; i < row; i++) {
        //     System.out.println(Arrays.toString(space[i]));
        // }

        return space;
    }

    private boolean isSame(int[][] map) {
        for(int i = 0; i < row;i ++) {
            for(int j = 0; j < col; j++) {
                if(map[i][j] != target[i][j]) return false;
            }
        }
        return true;
    }
}
