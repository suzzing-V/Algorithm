import java.util.*;
import java.io.*;

public class Main {

    private static Shark[] sharks;
    private static List<Smell> smells;
    private static Shark[][] shark_pos;
    private static Smell[][] smell_pos;
    private static int n;
    private static int m;
    private static int k;
    private static int[][][] priority;
    private static int[][] dir = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        sharks = new Shark[m + 1];
        smells = new ArrayList<>();
        smell_pos = new Smell[n + 1][n + 1];
        shark_pos = new Shark[n + 1][n + 1];
        priority = new int[m + 1][5][4];

        // 상어 정보 저장
        for(int i = 1; i <= n; i ++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 1; j <= n; j ++) {
                int num = Integer.parseInt(st.nextToken());

                if(num != 0) {
                    Shark shark = new Shark(num, i, j);
                    sharks[num] = shark;
                    shark_pos[i][j] = shark;

                    // 냄새 남기기
                    Smell smell = new Smell(num, i, j, k);
                    smells.add(smell);
                    smell_pos[i][j] = smell;
                }
            }
        }

        // 상어 방향 저장
        st = new StringTokenizer(bf.readLine());
        for(int i = 1; i <= m; i++) {
            int d = Integer.parseInt(st.nextToken());
            sharks[i].dir = d;
        }

        // 상어 이동 우선순위 저장
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= 4; j++) {
                st = new StringTokenizer(bf.readLine());
                for(int a = 0; a < 4; a++) {
                    priority[i][j][a] = Integer.parseInt(st.nextToken());
                }
            }
        }

        int time = 1;
        while(true) {
            // 다음 상어 위치
            Shark[][] next_sharks = new Shark[n + 1][n + 1];
            // 다음 향기
            Smell[][] next_smells = new Smell[n + 1][n + 1];
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n ;j++) {
                    next_smells[i][j] = smell_pos[i][j];
                }
            }

            // 상어 이동하면서 향기 남기기
            for(int i = 1; i <= m; i++) {
                if(sharks[i] == null) {
                    continue;
                }
                Shark shark = sharks[i];

                // 현재 보고있는 방향의 우선순위
                int[] pri = priority[i][shark.dir];
                boolean canMove = false;
                for(int d = 0; d < 4; d ++) {
                    int nd = pri[d];
                    int nx = shark.x + dir[nd][0];
                    int ny = shark.y + dir[nd][1];

                    if(nx <= 0 || nx > n || ny <= 0 || ny > n) {
                        continue;
                    }

                    // 향기가 없으면 갈 수 있다.
                    if(smell_pos[nx][ny] == null) {
                        // 그런데 그 자리에 이번 턴에 이동한 상어가 있으면 소멸한다.
                        if(next_sharks[nx][ny] != null) {
                            shark_pos[shark.x][shark.y] = null;
                            sharks[i] = null;
                            canMove = true;
                            break;
                        }

                        Smell smell = new Smell(i, nx, ny, k + 1);
                        next_smells[nx][ny] = smell;
                        smells.add(smell);

                        shark_pos[shark.x][shark.y] = null;
                        next_sharks[nx][ny] = shark;
                        shark.x = nx;
                        shark.y = ny;
                        shark.dir = nd;

                        canMove = true;
                        break;
                    }
                }

                // 만약 움직일 수 있는 방법이 없으면 내 냄새가 있는 곳으로 간다. (현재 방향과 반대 방향)
                if(!canMove) {
//                    System.out.println("갈수 있는 곳 없음; "+ shark.num + " " + shark.x + " "+ shark.y);
                    for(int d = 0; d < 4; d++) {
                        int nd = pri[d];
                        int nx = shark.x + dir[nd][0];
                        int ny = shark.y + dir[nd][1];

                        if(nx <= 0 || nx > n || ny <= 0 || ny > n) {
                            continue;
                        }

//                        System.out.println(nd + " " + nx + " "+ ny);
                        // 내 냄새가 있는 곳이면 간다.
                        if(smell_pos[nx][ny].num == shark.num) {
                            shark.dir = nd;
                            next_sharks[nx][ny] = shark;
                            shark_pos[shark.x][shark.y] = null;
                            shark.x = nx;
                            shark.y = ny;
                            next_smells[shark.x][shark.y].rest = k + 1;
                            break;
                        }
                    }
                }

            }

            // 향기 시간 줄이기
            for(int i = 0; i < smells.size(); i++) {
                Smell smell = smells.get(i);
                // 방금 생긴 건 안 건든다.
                smell.rest --;

                if(smell.rest == 0) {
                    smells.remove(i);
                    next_smells[smell.x][smell.y] = null;
                    i --;
                }
            }

            shark_pos = next_sharks;
            smell_pos = next_smells;
//            System.out.println("상어");
//            for(int i = 1; i <= n; i++) {
//                for(int j = 1; j <= n ;j++) {
//                    System.out.print((shark_pos[i][j] == null ? "0" : shark_pos[i][j].num) + " ");
//                }
//                System.out.println();
//            }
//            System.out.println("향기");
//            for(int i = 1; i <= n; i++) {
//                for(int j = 1; j <= n ;j++) {
//                    System.out.print((smell_pos[i][j] == null ? "0" : smell_pos[i][j].rest) + " ");
//                }
//                System.out.println();
//            }
            // 상어 한마리만 남아있으면 끝낸다.
            int cnt = 0;
            for(int i = 1; i <= m; i++) {
                if(sharks[i] != null) {
//                    System.out.println(i);
                    cnt ++;
                    if(cnt == 2) {
                        break;
                    }
                }
            }
            if(cnt == 1) {
                break;
            }

            // 다음이 1001초인데 아직 상어 2마리 이상 남아있으면 -1 반환
            if(time == 1000) {
                time = -1;
                break;
            }

            time ++;
        }

        System.out.println(time);
    }

    private static class Smell {
        int x;
        int y;
        int rest;
        int num;

        Smell(int num, int x, int y, int rest) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.rest = rest;
        }
    }

    private static class Shark {
        int x;
        int y;
        int num;
        int dir;

        Shark(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }
}
