import java.util.*;

public class Node {
    int redX;
    int redY;
    int blueX;
    int blueY;
    int turn;

    boolean[][] redV;
    boolean[][] blueV;
    Node(int redX, int redY, int blueX, int blueY, int turn, boolean[][] redV, boolean[][] blueV) {
        this.redX = redX;
        this.redY = redY;
        this.blueX = blueX;
        this.blueY = blueY;
        this.turn = turn;
        this.redV = new boolean[redV.length][redV[0].length];
        this.blueV = new boolean[redV.length][redV[0].length];
        for(int i = 0; i < redV.length; i++) {
            for(int j = 0; j < redV[0].length; j++) {
                this.redV[i][j] = redV[i][j];
                this.blueV[i][j] = blueV[i][j];
            }
        }


    }
}

class Solution {
    public int[][] dir = { {1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public int solution(int[][] maze) {
        int answer = 0;
        int row = maze.length;
        int col = maze[0].length;
        int redSrtX = 0;
        int redSrtY = 0;
        int blueSrtX = 0;
        int blueSrtY = 0;
        int redArvX = 0;
        int redArvY = 0;
        int blueArvX = 0;
        int blueArvY = 0;

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(maze[i][j] == 1) {
                    redSrtX = i;
                    redSrtY = j;
                } else if(maze[i][j] == 2) {
                    blueSrtX = i;
                    blueSrtY = j;
                } else if(maze[i][j] == 3) {
                    redArvX = i;
                    redArvY = j;
                } else if(maze[i][j] == 4) {
                    blueArvX = i;
                    blueArvY = j;
                }
            }
        }

        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(redSrtX, redSrtY, blueSrtX, blueSrtY, 0, new boolean[row][col], new boolean[row][col]));
        while(!queue.isEmpty()) {
            Node now = queue.remove();
            //경계, 방문 확인
            if(now.redX >= row || now.redX < 0 || now.redY >= col || now.redY < 0
                    || now.blueX >= row || now.blueX < 0 || now.blueY >= col || now.blueY < 0 || now.redV[now.redX][now.redY] || now.blueV[now.blueX][now.blueY]) {
                continue;
            }

            //벽 체크
            if(maze[now.redX][now.redY] == 5 || maze[now.blueX][now.blueY] == 5) {
                continue;
            }

            //동시에 같은 칸인지
            if(now.redX == now.blueX && now.redY == now.blueY) {
                continue;
            }

            boolean redArv = false;
            boolean blueArv = false;
            // 빨강 도착
            if(now.redX == redArvX && now.redY == redArvY) {
                redArv = true;
            }

            // 파랑 도착
            if(now.blueX == blueArvX && now.blueY == blueArvY) {
                blueArv = true;
            }

            if(redArv && blueArv) {
                answer = now.turn;
                break;
            }

            if(redArv) {
                now.blueV[now.blueX][now.blueY] = true;
            } else if(blueArv) {
                now.redV[now.redX][now.redY] = true;
            } else {
                now.redV[now.redX][now.redY] = true;
                now.blueV[now.blueX][now.blueY] = true;
            }

            if(redArv) {
                for(int j = 0; j < 4; j++) {
                    int blueNx = now.blueX + dir[j][0];
                    int blueNy = now.blueY + dir[j][1];
                    queue.add(new Node(now.redX, now.redY, blueNx, blueNy, now.turn + 1, now.redV, now.blueV));
                }
                continue;
            }

            if(blueArv) {
                for(int i = 0; i < 4; i++) {
                    int redNx = now.redX + dir[i][0];
                    int redNy = now.redY + dir[i][1];
                    queue.add(new Node(redNx, redNy, now.blueX, now.blueY, now.turn + 1, now.redV, now.blueV));
                }
                continue;
            }

            for(int i = 0; i < 4; i++) {
                for(int j = 0; j < 4; j++) {
                    int redNx = now.redX + dir[i][0];
                    int redNy = now.redY + dir[i][1];
                    int blueNx = now.blueX + dir[j][0];
                    int blueNy = now.blueY + dir[j][1];

                    //서로 자리 바꾸기인지
                    if(redNx == now.blueX && redNy == now.blueY && blueNx == now.redX && blueNy == now.redY) {
                        continue;
                    }
                    queue.add(new Node(redNx, redNy, blueNx, blueNy, now.turn + 1, now.redV, now.blueV));
                }
            }
        }

        return answer;
    }
}