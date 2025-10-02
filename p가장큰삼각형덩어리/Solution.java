// -1인데 1번 칠하면 밑/왼쪽 중 안 간 데로 간다.
// 밑에 모양이 -1이면 2번 칠하고 밑에 모양이 1이면 1번 칠한다.
// 왼쪽 모양이 -1이면 2번 칠하고 1이어도 2번 칠한다.
// -1인데 2번 칠하면 위/오른쪽 중 안 간 데로 간다.
// 위의 모양이 -1이면 1번, 1이면 2번 칠힌다.
// 오른쪽 모양이 -1이면 1번, 1이면 1번 칠한다.

// 1인데 1번 칠하면 위/왼쪽 중 안 간 데로 간다.ju8
// 위의 모양이 -1이면 1번, 1이면 2번 칠한다.
// 왼쪽 모양이 -1이면 2번, 1이면 2번 칠한다.
// 1인데 2번 칠하면 아래/오른쪽 중 안 간 데로 간다.
// 아래 모양이 -1이면 2번, 1이면 1번 칠한다.
// 오른쪽 모양이 -1이면 1번, 1이면 1번 칠한다.

import java.util.*;

// 시간복잡도: 200000
// 한 삼각형이 포함될 수 있는 그룹은 단 하나다. bfs로 그룹을 만들고 그 그룹의 크기가 가장 큰 그룹 크기 구하기

class Solution {

    private int[][] grid;
    private int[][][] visited;
    private boolean[][][] total_visited;
    private int max = 0;

    public int solution(int[][] grid1) {
        grid = grid1;
        visited = new int[grid.length][grid[0].length][3];

        int group = 1;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                // System.out.println(i + " " + j);
                if(visited[i][j][1] == 0) {
                    max = Math.max(max, bfs(i, j, 1, group ++));
                    // printTotal();
                }
                if(visited[i][j][2] == 0) {
                    // System.out.println("애용");
                    max= Math.max(max, bfs(i, j, 2, group ++));
                    // printTotal();
                }
            }
        }
        return max;
    }

    private int bfs(int x, int y, int t, int group) {
        Queue<Node> queue = new LinkedList<>();
        visited[x][y][t] = group;
        queue.add(new Node(x, y, t));

        int cnt = 1;
        while(!queue.isEmpty()) {
            Node curr = queue.remove();
            // System.out.println(curr.x + " " + curr.y + " " + curr.t + " " + grid[curr.x][curr.y]);

            if(grid[curr.x][curr.y] == -1 && curr.t == 1) {
                // 밑으로 가는 경우
                Node next = goDown(curr.x, curr.y, group);
                if(next != null) {
                    queue.add(next);
                    cnt ++;
                }
                // 왼쪽으로 가는 경우
                next = goLeft(curr.x, curr.y, group);
                if(next != null) {
                    queue.add(next);
                    cnt ++;
                }
            } else if(grid[curr.x][curr.y] == -1 && curr.t == 2) {
                // 밑으로 가는 경우
                Node next = goUp(curr.x, curr.y, group);
                if(next != null) {
                    queue.add(next);
                    cnt ++;
                }
                // 왼쪽으로 가는 경우
                next = goRight(curr.x, curr.y, group);
                if(next != null) {
                    queue.add(next);
                    cnt ++;
                }
            } else if(grid[curr.x][curr.y] == 1 && curr.t == 1) {
                // 밑으로 가는 경우
                Node next = goUp(curr.x, curr.y, group);
                if(next != null) {
                    queue.add(next);
                    cnt ++;
                }
                // 왼쪽으로 가는 경우
                next = goLeft(curr.x, curr.y, group);
                if(next != null) {
                    queue.add(next);
                    cnt ++;
                }
            } else if(grid[curr.x][curr.y] == 1 && curr.t == 2) {
                // 밑으로 가는 경우
                Node next = goDown(curr.x, curr.y, group);
                if(next != null) {
                    queue.add(next);
                    cnt ++;
                }
                // 왼쪽으로 가는 경우
                next = goRight(curr.x, curr.y, group);
                if(next != null) {
                    queue.add(next);
                    cnt ++;
                }
            }
        }

        // System.out.println("cnt: " + cnt);
        return cnt;
    }

    private Node goLeft(int x, int y, int group) {
        int nx = x;
        int ny = y - 1;

        if(ny < 0 || visited[nx][ny][2] == group || visited[nx][ny][1] == group) {
            return null;
        }
        visited[nx][ny][2] = group;
        return new Node(nx, ny, 2);
    }

    private Node goRight(int x, int y, int group) {
        int nx = x;
        int ny = y + 1;

        if(ny >= grid[0].length || visited[nx][ny][2] == group || visited[nx][ny][1] == group) {
            return null;
        }
        visited[nx][ny][1] = group;
        return new Node(nx, ny, 1);
    }

    private Node goDown(int x, int y, int group) {
        int nx = x + 1;
        int ny = y;

        if(nx >= grid.length || visited[nx][ny][2] == group || visited[nx][ny][1] == group) {
            // System.out.println("밑으로 가는데 막힘");
            return null;
        }

        if(grid[nx][ny] == -1) {
            visited[nx][ny][2] = group;
            return new Node(nx, ny, 2);
        }
        visited[nx][ny][1] = group;
        return new Node(nx, ny, 1);
    }

    private Node goUp(int x, int y, int group) {
        int nx = x - 1;
        int ny = y;

        // 같은 그룹이 방문한 칸이면 안된다.
        if(nx < 0 || visited[nx][ny][2] == group || visited[nx][ny][1] == group) {
            return null;
        }
        if(grid[nx][ny] == -1) {
            visited[nx][ny][1] = group;

            return new Node(nx, ny, 1);
        }
        visited[nx][ny][2] = group;

        return new Node(nx, ny, 2);
    }

    private class Node {
        int x;
        int y;
        int t;

        Node(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }
}
