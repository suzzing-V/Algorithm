import java.util.*;

// bfs 방문처리 로직 오류
// 1. 공간들과 블록들을 직사각형 모양으로 잘라서 리스트에 넣는다.
// 2. 공간들을 순회하면서 블록을 하나씩 회전시키면서 맞는 블록이 있는지 확인한다.
class Solution {

    private List<Slice> spaces = new ArrayList<>();
    private List<Slice> blocks = new ArrayList<>();
    private int n;
    private boolean[][] visited;
    private int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public int solution(int[][] game_board, int[][] table) {
        n = game_board.length;
        visited = new boolean[n][n];

        // game_board의 공간 구하기
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j] && game_board[i][j] == 0) {
                    bfs(i, j, 0, spaces, game_board);
                }
            }
        }

        visited = new boolean[n][n];
        // table의 블록 구하기
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j] && table[i][j] == 1) {
                    bfs(i, j, 1, blocks, table);
                }
            }
        }

        int answer = 0;
        for(int i = 0; i < spaces.size(); i++) {
            Slice space = spaces.get(i);

            for(int j = 0; j < blocks.size(); j++) {
                Slice block = blocks.get(j);
                if(space.cnt != block.cnt) continue;
                if((space.arr.length == block.arr.length && space.arr[0].length == block.arr[0].length)
                    || (space.arr.length == block.arr[0].length && space.arr[0].length == block.arr.length)) {
                    if(isCorrect(space.arr, block.arr)) {
                        // System.out.println(i + "  " + j);
                        answer += space.cnt;
                        blocks.remove(j);
                        break;
                    }
                }
            }
        }

        return answer;
    }

    private void bfs(int sx, int sy, int target, List<Slice> list, int[][] board) {
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(sx, sy));
        visited[sx][sy] = true;

        int minX = 100;
        int minY = 100;
        int maxX = 0;
        int maxY = 0;
        int cnt = 0;
        while(!q.isEmpty()) {
            Pos curr = q.remove();

            cnt ++;
            minX = Math.min(minX, curr.x);
            minY = Math.min(minY, curr.y);
            maxX = Math.max(maxX, curr.x);
            maxY = Math.max(maxY, curr.y);
            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];

                if(nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny] || board[nx][ny] != target) {
                    continue;
                }

                q.add(new Pos(nx, ny));
                visited[nx][ny] = true;
            }
        }

        int[][] slice = new int[maxX - minX + 1][maxY - minY + 1];
        for(int i = minX; i <= maxX; i++) {
            for(int j = minY; j <= maxY; j++) {
                slice[i - minX][j - minY] = board[i][j];
                // System.out.print(board[i][j]);
            }
            // System.out.println();
        }

        list.add(new Slice(slice, cnt));
    }

    private boolean isCorrect(int[][] space, int[][] block) {
        int[][] tmp = block;
        for(int i = 0; i < 4; i++) {
            // 가로 세로 길이가 같은 경우 비교
            if(tmp.length == space.length && tmp[0].length == space[0].length) {
                boolean flag = true;
                for(int x = 0; x < space.length; x++) {
                    for(int y = 0; y < space[0].length; y++) {
                        if(tmp[x][y] == space[x][y]) {
                            flag = false;
                            break;
                        }
                    }
                    if(!flag) break;
                }

                if(flag) return true;
            }

            // 블록 회전
            int[][] next = new int[tmp[0].length][tmp.length];
            for(int x = 0; x < next.length; x++) {
                for(int y = 0; y < next[0].length; y++) {
                    next[x][y] = tmp[next[0].length - 1 - y][x];
                }
            }
            tmp = next;
        }

        return false;
    }

    private class Slice {
        int[][] arr;
        int cnt;

        Slice(int[][] arr, int cnt) {
            this.arr = arr;
            this.cnt = cnt;
        }
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
