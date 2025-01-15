import java.util.*;

class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        int[][] dir = { {startX, 2 * n - startY}, {startX, -startY}, {-startX, startY}, {2 * m - startX, startY}};

        for(int i = 0; i < balls.length; i++) {
            int min = Integer.MAX_VALUE;
            for(int j = 0; j < 4; j++) {
                if(balls[i][0] == startX) {
                    int maxY = Math.max(startY, dir[j][1]);
                    int minY = Math.min(startY, dir[j][1]);
                    if(balls[i][1] < maxY && balls[i][1] > minY) {
                        continue;
                    }
                }
                if(balls[i][1] == startY) {
                    int maxX = Math.max(startX, dir[j][0]);
                    int minX = Math.min(startX, dir[j][0]);
                    // System.out.println("max min: " + maxX + " " + minX + " " + balls[i][0]);
                    if(balls[i][0] < maxX && balls[i][0] > minX) {
                        continue;
                    }
                }

                int distance = (int)(Math.pow(balls[i][0] - dir[j][0], 2) + Math.pow(balls[i][1] - dir[j][1], 2));

                // System.out.println(i + " " + j + " " + distance);
                min = Math.min(min, distance);
            }
            answer[i] = min;
        }
        return answer;
    }
}