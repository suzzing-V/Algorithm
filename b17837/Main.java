import java.io.*;
import java.util.*;

// 시간복잡도: 3 * 10^4
// 파랑이나 범위 밖일 때 움직일 수 있을 경우 그냥 넘어가버렸다. 조건문 실수
public class Main {

    private static int n;
    private static int k;
    private static int[][] board;
    private static ArrayList<Horse>[][] status;
    private static Horse[] poses;
    private static int[][] dir = {{0, 0}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[n + 1][n + 1];
        status = new ArrayList[n + 1][n + 1];
        poses = new Horse[k + 1];

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 1; j <= n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                status[i][j] = new ArrayList<>();
            }
        }

        for(int i = 1; i <= k; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            Horse horse = new Horse(i, x, y, dir);
            status[x][y].add(horse);
            poses[i] = horse;
        }

        // 턴마다 말 이동하기
        int turn = 1;
        while(true) {
//            System.out.println(turn + "턴");
            boolean isFinished = false;
            for(int i = 1; i <= k; i++) {
                Horse curr = poses[i];
                int cx = curr.x;
                int cy = curr.y;
                int nx = curr.x + dir[curr.dir][0];
                int ny = curr.y + dir[curr.dir][1];

                // 보드를 넘어가거나 다음 위치가 파란색일 때
                if(nx <= 0 || nx > n || ny <= 0 || ny > n || board[nx][ny] == 2) {
//                    System.out.println("파랑");
                    // 말의 이동방향 반대로
                    if(curr.dir == 1) curr.dir = 2;
                    else if(curr.dir == 2) curr.dir = 1;
                    else if(curr.dir == 3) curr.dir = 4;
                    else if(curr.dir == 4) curr.dir = 3;

                    // 수정한 방향으로 이동
                    nx = curr.x + dir[curr.dir][0];
                    ny = curr.y + dir[curr.dir][1];

                    // 수정한 방향도 파란색이거나 벗어난다면 안 움직인다.
                    if(nx <= 0 || nx > n || ny <= 0 || ny > n || board[nx][ny] == 2) {
                        continue;
                    }

//                    // 현재 말 위에 올려져 있는 말과 함께 다음 칸으로 이동
//                    int idx = status[cx][cy].indexOf(curr);
//                    for(int a = idx; a < status[cx][cy].size(); a+=0) {
//                        Horse h = status[cx][cy].get(a);
//
//                        // 말 이동 후 정보 수정
//                        status[cx][cy].remove(a);
//                        status[nx][ny].add(h);
//                        h.x = nx;
//                        h.y = ny;
//                    }
                }
                if(board[nx][ny] == 1) {
//                    System.out.println("빨강");
                    // 다음 위치가 빨간색일 때 빨간색 위에 올려져있는 것들 맨 위에서부터 옮기기
                    int idx = status[cx][cy].size() - 1;
                    while(true) {
                        Horse h = status[cx][cy].get(idx);

                        // 말 이동 후 정보 수정
                        status[cx][cy].remove(idx);
                        status[nx][ny].add(h);
                        h.x = nx;
                        h.y = ny;

                        if(h.num == i) {
                            break;
                        }

                        idx --;
                    }
                } else if(board[nx][ny] == 0) {
//                    System.out.println("하양");
                    // 다음 위치가 하얀색일 때 하얀색부터 차례대로 위로 올라가면서 옮기기
                    int idx = status[cx][cy].indexOf(curr);
                    for(int a = idx; a < status[cx][cy].size(); a+=0) {
                        Horse h = status[cx][cy].get(a);
//                        System.out.println(h.num);
                        // 말 이동 후 정보 수정
                        status[cx][cy].remove(a);
                        status[nx][ny].add(h);
                        h.x = nx;
                        h.y = ny;
                    }
                }

//                System.out.println(turn + "턴 " + i + "번 말 이동: " + status[curr.x][curr.y].toString());
                // 옮기고 나서 그 칸의 말 수가 4개 이상이면 멈추기
                if(status[curr.x][curr.y].size() >= 4) {
                    isFinished = true;
                    break;
                }
            }

            if(isFinished) {
                break;
            }

            // 턴수 1000인데 안 끝났으면 -1 반환
            if(turn == 1000) {
                turn = -1;
                break;
            }

            turn ++;
        }


        System.out.println(turn);
    }

    private static class Horse {
        int x;
        int y;
        int num;
        int dir;

        Horse(int num, int x, int y, int dir) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        @Override
        public String toString() {
            return String.valueOf(num);
        }
    }
}
