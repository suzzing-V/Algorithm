import java.util.*;
import java.io.*;

// 시간복잡도: 100 * 10^5
// 냄새를 굳이 pq에 따로 저장해서 관리하지 않아도 됐다. 어짜피 map의 크기는 4*4로 아주 작기 때문이다.
// 냄새가 겹칠 경우 같은 위치의 냄새를 pq에 따로 넣어서 문제가 발생했다. 그냥 int배열로 냄새가 겹칠 경우 다시 갱신해주는 방식으로 수정했다.
public class Main {

    private static int fi = 0;
    private static List<List<Integer>> combi = new ArrayList<>();
    private static int[][] sdir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    private static List<Fish> copy = new ArrayList<>();
    private static int sx;
    private static int sy;
    private static int m;
    private static int s;
    private static int[][] dir = {{0, 0}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
    private static Map<Integer, Fish> fishes = new HashMap<>();
    private static List<Fish>[][] fishMap = new ArrayList[5][5];
    private static int[][] smellMap = new int[5][5];
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        m = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        for(int i = 1; i <= 4; i++) {
            for(int j = 1; j <= 4; j++) {
                fishMap[i][j] = new ArrayList<>();
            }
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            Fish fish = new Fish(fi, x, y, d);
            fishes.put(fi, fish);
            fishMap[x][y].add(fish);
            fi++;
        }

        st = new StringTokenizer(bf.readLine());
        sx = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());

        // 상어 이동 조합 만들기
        makeCombi(new ArrayList<>());

        while(s > 0) {
            // 복제할 물고기 저장
            saveOriFish();
            // 물고기 이동하기
            moveFish();
//            System.out.println("물고기 이동 후");
//            printFishMap();
            // 상어 이동하기
            moveShark();
//            System.out.println("먹고난 후");
            // 두번전 연습에서 생긴 냄새 제거
//            printFishMap();
            // 물고기 냄새 시간 까기
            addSmellTime();
            // 물고기 복제하기
            copyFishes();

//            printFishMap();
//            printSmell();
            s--;
        }

//        printFishMap();

        System.out.println(fishes.size());
    }

    private static void printFishMap() {
        for(int i = 1; i <= 4; i++) {
            for(int j = 1; j<=4; j++) {
                if(fishMap[i][j].isEmpty()) System.out.print("x");
                for(Fish f : fishMap[i][j]) {
                    System.out.print(f.id + ",");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    private static void printSmell() {
        for(int i = 1; i <= 4; i++) {
            for(int j = 1;j <= 4; j++) {
                System.out.print(smellMap[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void makeCombi(List<Integer> com) {
        if(com.size() == 3) {
            List<Integer> copy = new ArrayList<>(com);
//            System.out.println(copy);
            combi.add(copy);
            return;
        }

        for(int i = 0; i < 4; i ++) {
            com.add(i);
            makeCombi(com);
            com.remove(com.size() -1);
        }
    }

    private static void copyFishes() {
        for(int i = 0; i < copy.size(); i++) {
            Fish fish = copy.get(i);

            fishMap[fish.x][fish.y].add(fish);
            fishes.put(fish.id, fish);
        }
    }

    private static void addSmellTime() {
        for(int i = 1; i<=4; i++) {
            for(int j = 1;j <= 4; j++) {
                if(smellMap[i][j] != 0) smellMap[i][j] --;
            }
        }
    }

    private static void moveShark() {
        int max = -1;
        int md = -1;
        for(int i = 0; i < combi.size(); i++) {
            int cnt = 0;
            boolean canGo = true;
            List<Integer> com = combi.get(i);

            boolean[][] visited = new boolean[5][5];
//            System.out.println(com);

            int nx = sx;
            int ny = sy;
            for(int j = 0; j < 3; j++) {
                nx += sdir[com.get(j)][0];
                ny += sdir[com.get(j)][1];
//                System.out.println(nx + " " + ny);

                if(nx < 1 || nx > 4 || ny < 1 || ny > 4) {
                    canGo = false;
                    break;
                }

                if(!visited[nx][ny]) cnt += fishMap[nx][ny].size();
                visited[nx][ny] = true;
            }

//            System.out.println(com + " " + cnt);
            if(!canGo) continue;
            if(cnt > max) {
                max = cnt;
                md = i;
            }
        }

//        System.out.println(combi.get(md).toString());

        // 정해진 방향의 물고기들 없애고 냄새 남기고 이동하기
        int nx = sx;
        int ny = sy;
        for(int i = 0; i < 3; i++) {
            nx += sdir[combi.get(md).get(i)][0];
            ny += sdir[combi.get(md).get(i)][1];


            for(int j = 0; j < fishMap[nx][ny].size(); j++) {
                Fish fish = fishMap[nx][ny].get(j);
                fishes.remove(fish.id);
//                System.out.println("어흥: "+ nx + " " + ny);
            }

            if(!fishMap[nx][ny].isEmpty()) {
                smellMap[nx][ny] = 3;
            }
            fishMap[nx][ny].clear();
        }

        sx = nx;
        sy = ny;
//        System.out.println("상어 이동: " + sx + " " + sy);
    }

    private static void moveFish() {
        List<Fish>[][] newMap = new ArrayList[5][5];
        for(int i = 1; i <= 4; i++) {
            for(int j = 1; j <= 4; j++) {
                newMap[i][j] = new ArrayList<>();
            }
        }

        for(int i : fishes.keySet()) {
            Fish fish = fishes.get(i);
//            System.out.println(fish.d);

            int d = fish.d;
            while(true) {
                int nx = fish.x + dir[d][0];
                int ny = fish.y + dir[d][1];

                if(nx < 1 || nx > 4 || ny < 1 || ny > 4 || (nx == sx && ny == sy)
                || smellMap[nx][ny] != 0) {
                    d --;
                    if(d == 0) d = 8;
                    if(d == fish.d) {
//                        System.out.println(i + " 이동불가");
                        newMap[fish.x][fish.y].add(fish);
                        break;
                    }
                    continue;
                }

                // 그 칸으로 이동
                fish.d = d;
                fish.x = nx;
                fish.y = ny;
//                System.out.println(i + " 이동: " + fish.x + " " + fish.y + " " + fish.d);
                newMap[nx][ny].add(fish);
                break;
            }
        }

        fishMap = newMap;
    }

    private static void saveOriFish() {
        copy = new ArrayList<>();
        for(int id : fishes.keySet()) {
            Fish fish = fishes.get(id);
            copy.add(new Fish(fi ++, fish.x, fish.y, fish.d));
//            System.out.print(fish.x + "," + fish.y + " ");
        }
    }

    private static class Fish {
        int id;
        int x;
        int y;
        int d;

        Fish(int id, int x, int y, int d) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    private static class Smell {
        int x;
        int y;
        int time;

        Smell(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
