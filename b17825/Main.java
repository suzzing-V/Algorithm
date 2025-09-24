import java.io.*;
import java.util.*;

// 시간복잡도: 4^10
public class Main {

    private static int[] dice = new int[10];
//    private static int[] m = {0, 1, 1, 1, 0, 1, 0, 0, 1, 0};
    private static int max = 0;
    // board[i][0] : 파란 호살표가 가리키는 노드
    // board[i][1] : 빨간 화살표가 가리키는 노드
    private static int[][] board = new int[33][3];
    private static boolean[] isOccupied = new boolean[101];
    private static int[] pos = new int[4];
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < 10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i <= 32; i++) {
            board[i][0] = -1;
            board[i][1] = -1;
        }

        // 21번 노드가 도착
        // 큰 원 화살표 만들기
        for(int i = 0; i <= 20; i ++) {
            board[i][1] = i + 1;
            board[i][2] = 2 * i;
        }

        // 왼쪽 선 만들기
        board[5][0] = 22;
        board[22][1] = 23;
        board[22][2] = 13;
        board[23][1] = 24;
        board[23][2] = 16;
        board[24][1] = 25;
        board[24][2] = 19;

        // 오른쪽 선 만들기
        board[26][1] = 25;
        board[26][2] = 26;
        board[27][1] = 26;
        board[27][2] = 27;
        board[28][1] = 27;
        board[28][2] = 28;
        board[15][0] = 28;

        // 밑에 선 만들기
        board[10][0] = 29;
        board[29][1] = 30;
        board[29][2] = 22;
        board[30][1] = 25;
        board[30][2] = 24;

        // 위에 선 만들기
        board[25][1] = 31;
        board[25][2] = 25;
        board[31][1] = 32;
        board[31][2] = 30;
        board[32][1] = 20;
        board[32][2] = 35;

        dfs(0, 0);
        System.out.println(max);
    }

    private static void dfs(int di, int sum) {
        if(di == 10) {
            max = Math.max(sum, max);
//            System.out.println(sum + " " + Arrays.toString(pos));
            return;
        }

        boolean isSelected = false;
        for(int i = 0; i < 4; i++) {
//            if(m[di] != i) continue;
            // 이미 해당 말이 도착칸에 있으면 움직이지 못한다.
            if(pos[i] == 21) {
                continue;
            }

            int curr_pos = pos[i];
            int move = dice[di];
            int next = 0;
            // 현재 위치에서 파란 화살표 있으면 파란 화살표 방향으로 가야한다.
            if(board[curr_pos][0] != -1) {
                // 파란화살표 방향으로 한번 움직이고 move 닳을 때까지 빨간화살표로 이동
                move --;
                next = board[curr_pos][0];
                while(move > 0) {
                    next = board[next][1];

                    if(next == 21) {
                        break;
                    }
                    move --;
                }
            } else {
                // 빨간화살표바껭 없으면 빨간화살표로 가야한다.
                next = curr_pos;
                while(move > 0) {
                    next = board[next][1];
//                    System.out.print(next + " ");
                    if(next == 21) {
                        break;
                    }
                    move --;
                }
            }
//            System.out.println();

            // 다음으로 갈 칸이 도착칸이 아닌데 누가 있으면 이거 선택 못한다.
            if(next != 21 && isOccupied[next]) continue;

            // 이동 후 다음 주사위
//            if(i == 0 || i == 1) System.out.println(i + "말 " + next + "로 이동, " + board[next][2] + "점 추가");
            pos[i] = next;
            isOccupied[next] = true;
            isOccupied[curr_pos] = false;
            dfs(di + 1, sum + board[next][2]);
            pos[i] = curr_pos;
            isOccupied[next] = false;
            isOccupied[curr_pos] = true;
            isSelected = true;
        }

        // 네 말 중 어느 말도 움직이지 않았다면 sum 안 더하고 다음 주사위
        if(!isSelected) dfs(di + 1, sum);
    }
}
