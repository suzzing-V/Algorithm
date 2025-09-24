import java.io.*;
import java.util.*;

public class Main {

    private static int[] dice = new int[10];
    private static int max = 0;
    // board[i][0] : 파란 호살표가 가리키는 노드
    // board[i][1] : 빨간 화살표가 가리키는 노드
    private static int[][] board = new int[41][2];
    private static boolean[] isOccupied = new boolean[101];
    private static int[] pos = new int[4];
    private static int[] prev_pos = new int[4];
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < 10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < 41; i++) {
            board[i][0] = -1;
            board[i][1] = -1;
        }

        // 보드 만들기
        for(int i = 2; i <= 38; i += 2) {
            board[i][1] = i + 2;
        }
        board[13][1] = 16;
        board[16][1] = 19;
        board[19][1] = 25;

        board[22][1] = 24;
        board[24][1] = 25;

        board[28][1] = 27;
        board[27][1] = 26;
        board[26][1] = 25;
        for(int i = 25; i <= 35; i+= 5) {
            board[i][1] = i + 5;
        }

        board[10][0] = 13;
        board[20][0] = 22;
        board[0][0] = 2;
        board[40][1] = 100;

        dfs(0, 0);
        System.out.println(max);
    }

    private static void dfs(int di, int sum) {
        if(di == 10) {
            max = Math.max(sum, max);
            System.out.println(sum + " " + Arrays.toString(pos));
            return;
        }

        boolean isSelected = false;
        for(int i = 0; i < 4; i++) {
            // 이미 해당 말이 도착칸에 있으면 움직이지 못한다.
            if(pos[i] == 100) {
                continue;
            }

            int prev = prev_pos[i];
            int curr_pos = pos[i];
            int move = dice[di];
            // 현재 위치에서 파란 화살표 있으면 파란 화살표 방향으로 가야한다.
            if(board[curr_pos][0] != -1) {
                // 파란화살표 방향으로 한번 움직이고 move 닳을 때까지 빨간화살표로 이동
                move --;
                pos[i] = board[curr_pos][0];
                while(move > 0) {
                    prev_pos[i] = pos[i];
                    pos[i] = board[pos[i]][1];

                    if(pos[i] == 100) {
                        break;
                    }
                    move --;
                }
            } else {
                // 빨간화살표바껭 없으면 빨간화살표로 가야한다.
                while(move > 0) {
                    if(pos[i] == 16) {
                        if(prev_pos[i] == 13) {
                            prev_pos[i] = 16;
                            pos[i] = 19;
                        } else {
                            prev_pos[i] = 16;
                            pos[i] = 18;
                        }
                    } else if(pos[i] == 22) {
                        if(prev_pos[i] == 20) {
                            prev_pos[i] = 22;
                            pos[i] = 24;
                        } else {
                            prev_pos[i] = 22;
                            pos[i] = 24;
                        }
                    } else if(pos[i] == 24) {
                        if(prev_pos[i] == 20) {
                            prev_pos[i] = 22;
                            pos[i] = 24;
                        } else {
                            prev_pos[i] = 22;
                            pos[i] = 24;
                        }
                    }
                    prev_pos[i] = pos[i];
                    pos[i] = board[pos[i]][1];
                    System.out.print(pos[i] + " ");
                    // 도착칸에 도착하면 멈춘다.
                    if(pos[i] == 100) {
                        break;
                    }

                    move --;
                }
                System.out.println();
            }

            // 다음으로 갈 칸이 도착칸이 아닌데 누가 있으면 이거 선택 못한다.
            if(next != 100 && isOccupied[next]) continue;

            // 이동 후 다음 주사위
            System.out.println(i + "말 " + next + "로 이동");
            int before = pos[i];
            int before_prev = prev_pos[i];
            pos[i] = next;
            prev_pos[i] = before;
            isOccupied[next] = true;
            dfs(di + 1, sum + next);
            pos[i] = before;
            prev_pos[i] = before_prev;
            isOccupied[next] = false;
            isSelected = true;
        }

        // 네 말 중 어느 말도 움직이지 않았다면 sum 안 더하고 다음 주사위
        if(!isSelected) dfs(di + 1, sum);
    }
}
