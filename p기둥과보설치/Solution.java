import java.util.*;

class Solution {
    private int[][][] column;
    private int[][][] floor;
    private int n;
    private int cnt = 0;

    public int[][] solution(int n1, int[][] build_frame) {
        n = n1;
        column = new int[n + 1][n + 1][2];
        floor = new int[n + 1][n + 1][2];

        for(int a = 0; a < build_frame.length; a++) {
            int x = build_frame[a][0];
            int y = build_frame[a][1];
            int isFloor = build_frame[a][2];
            int isBuild = build_frame[a][3];

            // 삭제
            if(isBuild == 0) {
                boolean isOk = true;
                if(isFloor == 0) {
                    // System.out.println()
                    column[x][y][0] = 0;
                    column[x][y + 1][1] = 0;
                    cnt --;
                    for(int i = 0; i <= n; i++) {
                        for(int j = 0; j <= n; j++) {
                            if((column[i][j][0] == 1 && !isColumnOk(i, j)) || (floor[i][j][0] == 1 && !isFloorOk(i, j))) {
                                isOk = false;
                                break;
                            }
                        }
                    }
                    if(!isOk) {
                        // System.out.println(x + " " + y + " 삭제");
                        column[x][y][0] = 1;
                        column[x][y + 1][1] = 1;
                        cnt ++;
                    }
                } else {
                    floor[x][y][0] = 0;
                    floor[x + 1][y][1] = 0;
                    cnt --;

                    for(int i = 0; i <= n; i++) {
                        for(int j = 0; j <= n; j++) {
                            if((column[i][j][0] == 1 && !isColumnOk(i, j)) || (floor[i][j][0] == 1 && !isFloorOk(i, j))) {
                                isOk = false;
                                break;
                            }
                        }
                    }

                    if(!isOk) {
                        floor[x][y][0] = 1;
                        floor[x + 1][y][1] = 1;
                        cnt ++;
                    }
                }
            } else {
                boolean isOk = true;
                if(isFloor == 0) {
                    isOk = isColumnOk(x, y);
                    if(isOk) {
                        column[x][y][0] = 1;
                        column[x][y + 1][1] = 1;
                        cnt ++;
                    }
                } else {
                    isOk = isFloorOk(x, y);
                    if(isOk) {
                        floor[x][y][0] = 1;
                        floor[x + 1][y][1] = 1;
                        cnt ++;
                    }
                }
            }
        }
        int[][] answer = new int[cnt][3];
        int c = 0;
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= n; j++) {
                if(column[i][j][0] == 1) {
                    answer[c][0] = i;
                    answer[c][1] = j;
                    answer[c][2] = 0;
                    c ++;
                }
                if(floor[i][j][0] == 1) {
                    answer[c][0] = i;
                    answer[c][1] = j;
                    answer[c][2] = 1;
                    c ++;
                }
            }
        }
        return answer;
    }

    private boolean isColumnOk(int x, int y) {
        if(y == 0) return true;
        if(floor[x][y][0] == 1 || floor[x][y][1] == 1) return true;
        if(column[x][y][1] == 1) return true;
        return false;
    }

    private boolean isFloorOk(int x, int y) {
        // System.out.println(column[x][y][1] + " " + column[x + 1][y][1]);
        if(column[x][y][1] == 1 || (x + 1 <= n && column[x + 1][y][1] == 1)) return true;
        // System.out.println(floor[x][y][1] + " " + floor[x + 1][y][0]);
        if(floor[x][y][1] == 1 && (x + 1 <= n && floor[x + 1][y][0] == 1)) return true;
        return false;
    }
}