import java.util.*;

class Solution {

    private int min = Integer.MAX_VALUE;
    private int[][] board;
    private int[][] dir = { {1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private class Result {
        private boolean isWin;
        private int turn;

        private Result(boolean isWin, int turn) {
            this.isWin = isWin;
            this.turn = turn;
        }
    }

    public int solution(int[][] board1, int[] aloc, int[] bloc) {
        board = board1;

        Result result = dfs(aloc[0], aloc[1], bloc[0], bloc[1]);
        return result.turn;
    }

    private Result dfs(int ax, int ay, int bx, int by) {
        // ax, ay가 지금 턴인 사람의 위치
        // System.out.println(ax + " " + ay + " " + bx + " " + by);

        boolean isFinish = false;
        for(int i = 0; i < 4; i++) {
            int nx = ax + dir[i][0];
            int ny = ay + dir[i][1];

            if(nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length || board[nx][ny] == 0) continue;
            isFinish = true;
        }
        if(!isFinish) { // 갈 수 있는 길이 없을 때
            return new Result(false, 0); // 다음 턴으로 갈 수 없으므로 0
        }

        if(ax == bx && ay == by) {
            return new Result(true, 1); // 현재 두 플레이어의 위치가 같을 경우 현재 플레이어가 움직이면 무조건 이긴다. 움직이는 턴때문에 1.
        }

        boolean canWin = false; // 한번이라도 이길 수 있으면 그 루트 선택함.
        int minTurn = Integer.MAX_VALUE;
        int maxTurn = 0;

        for(int i = 0; i < 4; i++) {
            int nx = ax + dir[i][0];
            int ny = ay + dir[i][1];

            if(nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length || board[nx][ny] == 0) continue;
            board[ax][ay] = 0;
            Result result = dfs(bx, by, nx, ny); // 다음 턴 사람 바꿈
            board[ax][ay] = 1;

            if(!result.isWin) { // 다음 턴에서 상대가 이길 수 없어야 현재 루트로 갔을 때 현재 플레이어가 이긴다.
                canWin = true;
                minTurn = Math.min(minTurn, result.turn); // 이길 경우 최소 턴인 루트 선택
            } else { // 현재 플레이어가 질 경우
                maxTurn = Math.max(maxTurn, result.turn); // 한번이라도 이길 경우에 이길 수 있다고 처리해야하기 때문에 canWin은 갱신하지 않는다. 질 경우에 최대 루트를 선택하므로 최대 턴 수 갱신.
            }
        }

        if(canWin) { // 이길 수 있는 경우
            return new Result(canWin, minTurn + 1); // 최소 턴수 리턴
        }
        return new Result(canWin, maxTurn + 1);
    }
}
