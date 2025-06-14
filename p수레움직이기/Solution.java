import java.util.*;

class Solution {
    private int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private int goalRedX;
    private int goalRedY;
    private int goalBlueX;
    private int goalBlueY;
    private int n;
    private int m;
    private int[][] maze;

    private class Node {
        int redX;
        int redY;
        int blueX;
        int blueY;
        int turn;
        boolean[][] visitedRed;
        boolean[][] visitedBlue;

        Node(int redX, int redY, int blueX, int blueY, int turn, boolean[][] visitedRed, boolean[][] visitedBlue) {
            this.redX = redX;
            this.redY = redY;
            this.blueX = blueX;
            this.blueY = blueY;
            this.turn = turn;
            this.visitedRed = visitedRed;
            this.visitedBlue = visitedBlue;
        }
    }

    public int solution(int[][] maze1) {
        maze = maze1;
        n = maze.length;
        m = maze[0].length;
        boolean[][] visitedRed = new boolean[n][m];
        boolean[][] visitedBlue = new boolean[n][m];
        int initRedX = -1;
        int initRedY = -1;
        int initBlueX = -1;
        int initBlueY = -1;

        // 빨간 수레, 파란 수레 시작 좌표 찾기
        for(int i = 0; i < maze.length; i++) {
            for(int j = 0; j < maze[0].length; j++) {
                if(maze[i][j] == 1) {
                    initRedX = i;
                    initRedY = j;
                }

                if(maze[i][j] == 2) {
                    initBlueX = i;
                    initBlueY = j;
                }

                if(maze[i][j] == 3) {
                    goalRedX = i;
                    goalRedY = j;
                }

                if(maze[i][j] == 4) {
                    goalBlueX = i;
                    goalBlueY = j;
                }
            }
        }

        // 시작부분 방문처리
        visitedRed[initRedX][initRedY] = true;
        visitedBlue[initBlueX][initBlueY] = true;
        return bfs(new Node(initRedX, initRedY, initBlueX, initBlueY, 0, visitedRed, visitedBlue));
    }

    private int bfs(Node start) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);

        while(!queue.isEmpty()) {
            Node curr = queue.remove();

            // System.out.println(curr.redX + " " + curr.redY + " " + curr.blueX + " " + curr.blueY);
            if(isGoalRed(curr.redX, curr.redY) && isGoalBlue(curr.blueX, curr.blueY)) { //  둘다 목적지에 도착하면 끝
                return curr.turn;
            } else if(isGoalRed(curr.redX, curr.redY)) { // 빨간색이 목적지에 도착했다면 파란색만 움직이기
                int nextRedX = curr.redX;
                int nextRedY = curr.redY;
                for(int j = 0; j < 4; j++) {
                    int nextBlueX = curr.blueX + dir[j][0];
                    int nextBlueY = curr.blueY + dir[j][1];

                    if(isWallOrOut(nextBlueX, nextBlueY) || curr.visitedBlue[nextBlueX][nextBlueY]) continue;

                    if(isMeet(nextRedX, nextRedY, nextBlueX, nextBlueY)) continue;

                    boolean[][] visitedRed = copyVisited(curr.visitedRed);
                    boolean[][] visitedBlue = copyVisited(curr.visitedBlue);
                    visitedRed[nextRedX][nextRedY] = true;
                    visitedBlue[nextBlueX][nextBlueY] = true;
                    queue.add(new Node(nextRedX, nextRedY, nextBlueX, nextBlueY, curr.turn + 1, visitedRed, visitedBlue));
                }
            } else if(isGoalBlue(curr.blueX, curr.blueY)) { // 파란색이 도착했다면 빨간색만 움직이기
                int nextBlueX = curr.blueX;
                int nextBlueY = curr.blueY;
                for(int j = 0; j < 4; j++) {
                    int nextRedX = curr.redX + dir[j][0];
                    int nextRedY = curr.redY + dir[j][1];

                    if(isWallOrOut(nextRedX, nextRedY) || curr.visitedRed[nextRedX][nextRedY]) continue;

                    if(isMeet(nextRedX, nextRedY, nextBlueX, nextBlueY)) continue;

                    boolean[][] visitedRed = copyVisited(curr.visitedRed);
                    boolean[][] visitedBlue = copyVisited(curr.visitedBlue);
                    visitedRed[nextRedX][nextRedY] = true;
                    visitedBlue[nextBlueX][nextBlueY] = true;
                    queue.add(new Node(nextRedX, nextRedY, nextBlueX, nextBlueY, curr.turn + 1, visitedRed, visitedBlue));
                }
            } else { // 둘다 도착하지 않았으면 둘다 움직이기

                for(int i = 0; i < 4; i++) {
                    int nextRedX = curr.redX + dir[i][0];
                    int nextRedY = curr.redY + dir[i][1];
                    if(isWallOrOut(nextRedX, nextRedY) || curr.visitedRed[nextRedX][nextRedY]) continue;

                    for(int j = 0; j < 4; j++) {
                        int nextBlueX = curr.blueX + dir[j][0];
                        int nextBlueY = curr.blueY + dir[j][1];

                        if(isWallOrOut(nextBlueX, nextBlueY) || curr.visitedBlue[nextBlueX][nextBlueY]) continue;

                        if(isMeet(nextRedX, nextRedY, nextBlueX, nextBlueY)) continue;
                        if(isCross(curr.redX, curr.redY, curr.blueX, curr.blueY, nextRedX, nextRedY, nextBlueX, nextBlueY)) continue;

                        boolean[][] visitedRed = copyVisited(curr.visitedRed);
                        boolean[][] visitedBlue = copyVisited(curr.visitedBlue);
                        visitedRed[nextRedX][nextRedY] = true;
                        visitedBlue[nextBlueX][nextBlueY] = true;
                        queue.add(new Node(nextRedX, nextRedY, nextBlueX, nextBlueY, curr.turn + 1, visitedRed, visitedBlue));
                    }
                }
            }
        }

        return 0;
    }

    private boolean isWallOrOut(int x, int y) {
        if(x >= n || x < 0 || y >= m || y < 0) return true;
        if(maze[x][y] == 5) return true;
        return false;
    }

    private boolean isGoalRed(int x, int y) {
        if(x == goalRedX && y == goalRedY) return true;
        return false;
    }

    private boolean isGoalBlue(int x, int y) {
        if(x == goalBlueX && y == goalBlueY) return true;
        return false;
    }

    private boolean isMeet(int x1, int y1, int x2, int y2) {
        if(x1 == x2 && y1 == y2) return true;
        return false;
    }

    private boolean isCross(int cx1, int cy1, int cx2, int cy2, int nx1, int ny1, int nx2, int ny2) {
        if(cx1 == nx2 && cy1 == ny2 && cx2 == nx1 && cy2 == ny1) return true;
        return false;
    }

    private boolean[][] copyVisited(boolean[][] origin) {
        boolean[][] copy = new boolean[n][m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j ++) {
                copy[i][j] = origin[i][j];
            }
        }

        return copy;
    }
}