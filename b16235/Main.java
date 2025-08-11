import java.util.*;
import java.io.*;

public class Main {

    private static int[][] a;
    private static int[][] yum;
    private static int n;
    private static int m;
    private static int k;
    private static PriorityQueue<Tree> live_trees1 = new PriorityQueue<>();
    private static PriorityQueue<Tree> live_trees2 = new PriorityQueue<>();
    private static List<Tree> dead_trees = new ArrayList<>();
    private static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        a = new int[n + 1][n + 1];
        yum = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 1; j <= n; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
                yum[i][j] = 5;
            }
        }

        for(int i = 0; i < m ;i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());

            live_trees1.add(new Tree(x, y, age));
        }

        for(int i = 0; i < k; i++) {
            // 봄
            while(!live_trees1.isEmpty()) {
                Tree curr = live_trees1.remove();
                // 양분 부족시 죽음
                if(yum[curr.x][curr.y] - curr.age < 0) {
                    dead_trees.add(curr);
                    continue;
                }

                yum[curr.x][curr.y] -= curr.age;
                curr.age ++;
                live_trees2.add(curr);
            }

            // 여름
            for(Tree tree : dead_trees) {
                yum[tree.x][tree.y] += tree.age / 2;
            }
            dead_trees.clear();

            // 가을
            while(!live_trees2.isEmpty()) {
                Tree curr = live_trees2.remove();
                // 나이가 5의 배수면 번식
                if(curr.age % 5 == 0) {
                    for(int j = 0; j < 8; j++) {
                        int nx = curr.x + dir[j][0];
                        int ny = curr.y + dir[j][1];

                        if(nx < 1 || nx > n || ny < 1 || ny > n) {
                            continue;
                        }
                        live_trees1.add(new Tree(nx, ny, 1));
                    }
                }

                live_trees1.add(curr);
            }

            // 겨울
            for(int x = 1; x <= n; x++) {
                for(int y = 1; y <= n; y++) {
                    yum[x][y] += a[x][y];
                }
            }
        }

        System.out.println(live_trees1.size());
    }

    private static class Tree implements Comparable<Tree> {
        int x;
        int y;
        int age;

        Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }

        @Override
        public int compareTo(Tree t) {
            return this.age - t.age;
        }
    }
}
