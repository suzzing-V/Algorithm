import java.io.*;
import java.util.*;

// 시간복잡도: 400 * 1800
public class Main {

    private static int n;
    private static int m;
    private static int[][] board;
    private static int score = 0;
    private static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        for(int i = 0; i < n ;i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            Group max = new Group(0, 0, -1, -1, new ArrayList<>());
            boolean isExist = false;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(board[i][j] == -1 || board[i][j] == 0 || board[i][j] == -10) continue;
//                    System.out.println(i + " " + j + " 시작");
                    Group group = bfs(i, j);
//                    break;
                    if(group == null) {
                        continue;
                    }

                    isExist = true;
                    // 현재까지 가장 큰 블록과 비교해서 크면 갱신
                    if(max.cnt < group.cnt) {
                        max = group ;
                    } else if(max.cnt == group.cnt) {
                        if(max.rainbow < group.rainbow) {
                            max = group;
                        } else if(max.rainbow == group.rainbow) {
                            if(max.sx < group.sx) {
                                max = group;
                            } else if(max.sx == group.sx) {
                                if(max.sy < group.sy) {
                                    max = group;
                                }
                            }
                        }
                    }
                }
            }

            // 블록 그룹이 없으면 멈춘다.
            if(!isExist) {
                break;
            }

            // 선택된 블록 제거 후 점수 더하기
            score += (int)Math.pow(max.cnt, 2);
            for(Pos pos : max.blocks) {
                board[pos.x][pos.y] = -10;
            }

//            System.out.println("블록 제거 후");
//            printBoard();

            gravity();
//            System.out.println("중력 작용후");
//            printBoard();
            rotate_90();
//            System.out.println("회전 후 ");
//        printBoard();
            gravity();
//            System.out.println("중력 작용 후");
//        printBoard();
        }

        System.out.println(score);
    }

    private static void printBoard() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void rotate_90() {
        int[][] rotated = new int[n][n];
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < n; j++) {
                rotated[n - 1 - j][i] = board[i][j];
            }
        }
        board = rotated;
    }

    private static void gravity() {
        for(int j = 0; j < n; j++) {
            int i = 0;
            while(i < n) {
                // 검은색 블록 만나거나 격자 나갈 때까지 블록 넣고 그자리 비우기
                List<Integer> blocks = new ArrayList<>();
                while(true) {
                    // 보드 나가거나 검은색 블록 만나면 끝
                    if(i >= n || board[i][j] == -1) {
                        break;
                    }

                    // 블록 있을 경우 담기
                    if(board[i][j] != -10) {
                        blocks.add(board[i][j]);
                        board[i][j] = -10;
                    }
                    i ++;
                }

                int next = i + 1;
                i --;
                // 마지막으로 담은 블록부터 검은 블록이나 격자 경계 위로 쌓는다.
                for(int b = blocks.size() - 1; b >= 0; b --) {
                    board[i][j] = blocks.get(b);
                    i --;
                }
                i = next;
            }
        }
    }

    private static Group bfs(int x, int y) {
        Queue<Pos> queue = new LinkedList<>();
        queue.add(new Pos(x, y));
        boolean[][] visited = new boolean[n][n];
        visited[x][y] = true;
        int rainbow = 0;
        int cnt = 1;
        List<Pos> blocks = new ArrayList<>();
        blocks.add(new Pos(x, y));
        int sx = x;
        int sy = y;

        while(!queue.isEmpty()) {
            Pos curr = queue.remove();
//            System.out.println(curr.x + " " + curr.y + " " + sx + " "+ sy);

            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];

                // 보드 넘어가거나, 이미 방문했거나, 검은색 블록이거나, 기준 블록과 색이 다르면 포함되지 못한다.
                if(nx < 0 || nx >= n || ny < 0 || ny >= n || board[nx][ny] == -1 || visited[nx][ny] || board[nx][ny] == -10) {
                    continue;
                }

                // 일반블록인데 기준 블록과 색 다르면 안됨
                if(board[nx][ny] != 0 && board[nx][ny] != board[sx][sy]) {
                    continue;
                }

                // 무지개색 블록 아닐 경우 기준 블록 갱신
                if(board[nx][ny] != 0) {
                    if(nx < sx) {
                        sx = nx;
                        sy = ny;
                    } else if(nx == sx) {
                        if(ny < sy) {
                            sx = nx;
                            sy = ny;
                        }
                    }
                }

                queue.add(new Pos(nx, ny));
                cnt ++;
                blocks.add(new Pos(nx, ny));
                if(board[nx][ny] == 0) {
                    rainbow ++;
                }
                visited[nx][ny] = true;
            }
        }

        if(cnt < 2) {
            return null;
        }

        return new Group(cnt, rainbow, sx, sy, blocks);
    }

    private static class Group {
        int cnt;
        int rainbow;
        int sx;
        int sy;
        List<Pos> blocks;

        Group(int cnt, int rainbow, int sx, int sy, List<Pos> blocks) {
            this.cnt = cnt;
            this.rainbow = rainbow;
            this.sx = sx;
            this.sy = sy;
            this.blocks = blocks;
        }
    }

    private static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
