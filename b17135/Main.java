import java.util.*;
import java.io.*;

public class Main {

    private static List<Pos> enemies = new ArrayList<>();
    private static int n;
    private static int m;
    private static int d;
    private static int max;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        visited = new boolean[m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < m; j++) {
                int isEnemy = Integer.parseInt(st.nextToken());
                if(isEnemy == 1) enemies.add(new Pos(i, j));
            }
        }

        // 궁수 위치 조합 만들기
        dfs(0, new Pos[3]);
        System.out.println(max);
    }

    private static void dfs(int idx, Pos[] attacks) {
        if(idx == 3) {
            int result = game(attacks);
//            if(result == 14) {
//                for(int i = 0; i < 3; i++) {
//                    System.out.print(attacks[i].x + "," + attacks[i].y + " ");
//                }
//                System.out.println();
//            }
            max = Math.max(max, result);
            return;
        }

        for(int j = 0; j < m; j++) {
            if(!visited[j]) {
                attacks[idx] = new Pos(n, j);
                visited[j] = true;
                dfs(idx + 1, attacks);
                visited[j] = false;
            }
        }
    }

    private static int game(Pos[] attacks) {
        List<Pos> currEnemies = new ArrayList<>();
        for(Pos pos : enemies) {
            currEnemies.add(new Pos(pos.x, pos.y));
        }

        int cnt = 0;
        while(!currEnemies.isEmpty()) {
            boolean[][] isAttacked = new boolean[n][m];
            // 각 궁수가 쏠 적 설정
            for(int i = 0; i < 3; i ++) {
                Pos target = findTarget(attacks[i], currEnemies);
                if(target == null) continue; // 맞출 적이 없는 경우 넘어감
                if(!isAttacked[target.x][target.y]) cnt ++; // 이미 다른 궁수에 의해 맞춰지지 않았다면 맞힌 적 숫자 카운트
                isAttacked[target.x][target.y] = true;
            }

            // 활에 맞거나 다음 턴에 성인 적 삭제
            for(int i = 0; i < currEnemies.size(); i++) {
                Pos enemy = currEnemies.get(i);
                if(enemy.x + 1 == n) {
                    currEnemies.remove(enemy);
                    i --;
                    continue;
                }
                if(isAttacked[enemy.x][enemy.y]) {
                    currEnemies.remove(enemy);
                    i --;
                    continue;
                }
                enemy.x ++;    // 아래로 전진
            }
        }

        return cnt;
    }

    private static Pos findTarget(Pos attack, List<Pos> enemies) {
        int rd = Integer.MAX_VALUE;
        int rx = 20;
        int ry = 20;

        for(Pos pos : enemies) {
            int dist = Math.abs(pos.x - attack.x) + Math.abs(pos.y - attack.y);
            if(dist > d) continue; // 거리 제한에 걸림
            if(dist < rd || (dist == rd && pos.y < ry)) { // 현재 최소 거리보다 더 가깝거나, 같고 y좌표가 더 작을 경우 갱신
                rd = dist;
                rx = pos.x;
                ry = pos.y;
            }
        }

        if(rd == Integer.MAX_VALUE) return null;
        return new Pos(rx, ry);
    }

    private static class Pos {
        private int x;
        private int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
