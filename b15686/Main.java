import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] city;
    static int result = 2100000000;
    static List<Pos> chicken;

    public static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Pos) {
                Pos st = (Pos)obj;
                return x == st.x && y == st.y;
            }
            else return false;
        }
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        chicken = new ArrayList<>();
        city = new int[n][n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < n; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                if(city[i][j] == 2) {
                    chicken.add(new Pos(i, j));
                }
            }
        }

        List<Pos> opened = new ArrayList<>(chicken);
            dfs(m, 0, opened);

        bw.write(String.valueOf(result));
        bw.close();
    }

    public static void dfs(int num, int index, List<Pos> opened) {
        if(opened.size() == num) {
            result = Math.min(getChickenDistance(opened), result);
            return;
        }
        if(index >= chicken.size()) return;

        city[chicken.get(index).x][chicken.get(index).y] = 0;
        opened.remove(new Pos(chicken.get(index).x, chicken.get(index).y));
        dfs(num, index + 1, opened);
        opened.add(new Pos(chicken.get(index).x, chicken.get(index).y));
        city[chicken.get(index).x][chicken.get(index).y] = 2;

        dfs(num, index + 1, opened);
    }

    public static int getChickenDistance(List<Pos> opened) {
        int total = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(city[i][j] == 1) {
                    int min = 2100000000;
                    for(Pos pos : opened) {
                        int distance = Math.abs(pos.x - i) + Math.abs(pos.y - j);
                        min = Math.min(distance, min);
                    }
                    total += min;
                }
            }
        }
        return total;
    }
}
