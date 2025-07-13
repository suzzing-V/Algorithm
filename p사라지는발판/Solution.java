import java.util.*;

class Solution {

    private int[][] board;
    private int[][] dir = {{1,0}, {0, 1}, {-1, 0}, {0, -1}};

    public int solution(int[][] board1, int[] aloc, int[] bloc) {
        board = board1;

        return dfs(new Pos(aloc[0], aloc[1]), new Pos(bloc[0], bloc[1]));
    }

    private int dfs(Pos curr, Pos next) {
        // 도달한 칸이 0이면 게임 끝
        if(board[curr.x][curr.y] == 0) return 0;

        // 현재 턴에서 마지막 턴까지 minimax 알고리즘으로 게임했을 때 최종 이동 횟수
        // 만약 갈 수 있는 곳이 없으면 0 리턴
        int result = 0;
        board[curr.x][curr.y] = 0;
        for(int i = 0; i < 4; i++) {
            int nx = curr.x + dir[i][0];
            int ny = curr.y + dir[i][1];

            if(nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length || board[nx][ny] == 0) continue;

            int cnt = dfs(next, new Pos(nx, ny)) + 1;

            // 이전 탐색에서 이기는 경우가 없었는데 현재 탐색에서 이겼으면 무조건 갱신(현재 플레이어는 이기는 플레이어)
            if(result % 2 == 0 && cnt % 2 == 1) result = cnt;
            // 이전 탐색에서 이기는 경우가 없었는데 현재 탐색에서 졌으면 최대값으로 갱신(현재 플레이어는 지는 플레이어)
            if(result % 2 == 0 && cnt % 2 == 0) result = Math.max(result, cnt);
            // 이전 탐색에서 이기는 경우가 있었는데 현재 탐색에서 이겼으면 최소값으로 갱신
            if(result % 2 == 1 && cnt % 2 == 1) result = Math.min(result, cnt);
            // 이전 탐색에서 이기는 경우가 있었는데 현재 탐색에서 졌으면 아무것도 안한다. 왜냐면 한번이라도 이기는 경로가 있다면 이기는 플레이어기 때문
        }
        board[curr.x][curr.y] = 1;

        return result;
    }

    private class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}