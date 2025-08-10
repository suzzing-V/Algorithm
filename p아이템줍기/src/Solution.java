import java.util.*;

class Solution {

    private int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private boolean[][] visited = new boolean[51][51];
    private int[][] rectangle;

    public int solution(int[][] rectangle1, int characterX, int characterY, int itemX, int itemY) {
        rectangle = rectangle1;
        int answer = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(characterX, characterY, 0));
        visited[characterX][characterY] = true;
        while(!pq.isEmpty()) {
            Node curr = pq.remove();

            // System.out.println(curr.x + " " + curr.y + " " + curr.d);
            if(curr.x == itemX && curr.y == itemY) {
                answer = curr.d;
                break;
            }

            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];

                // System.out.println(nx + " " + ny);
                if(nx < 1 || nx > 50 || ny < 1 || ny > 50 || visited[nx][ny]) continue;
                
                // 선분이 직사각형의 변에 있어야 한다. 변이 겹치는 경우는 없으므로 하나의 직사각형 변에 존재한다.
                int line = isOnSameLine(curr.x, curr.y, nx, ny);
                // 직사각형의 변에 없을 경우 갈 수 없다.
                if(line == -1) {
                    // System.out.println("라인에 없음");
                    continue;
                }
                
                // 다른 직사각형의 범위 안에 있으면 안된다.
                if(isInOtherRec(curr.x, curr.y, nx, ny, line)) {
                    // System.out.println("다른 사각형 안에 있음");
                    continue;
                }
                visited[nx][ny] = true;
                pq.add(new Node(nx, ny, curr.d + 1));
            }
        }

        return answer;
    }

    private int isOnSameLine(int x, int y, int nx, int ny) {
        for(int i = 0; i < rectangle.length; i++) {
            int x1 = rectangle[i][0];
            int y1 = rectangle[i][1];
            int x2 = rectangle[i][2];
            int y2 = rectangle[i][3];

            if(y >= y1 && y <= y2 && ny >= y1 && ny <= y2) {
                if(x == x1 && nx == x1) return i;
                if(x == x2 && nx == x2) return i;
            }
            if(x >= x1 && x <= x2 && nx >= x1 && nx <= x2) {
                if(y == y1 && ny == y1) return i;
                if(y == y2 && ny == y2) return i;
            }
        }

        return -1;
    }

    private boolean isInOtherRec(int x, int y, int nx, int ny, int rec) {
        for(int i = 0; i < rectangle.length; i++) {
            if(i == rec) continue;
            int x1 = rectangle[i][0];
            int y1 = rectangle[i][1];
            int x2 = rectangle[i][2];
            int y2 = rectangle[i][3];

            if(x >= x1 && x <= x2 && y >= y1 && y <= y2
                    && nx >= x1 && nx <= x2 && ny >= y1 && ny <= y2) {
                return true;
            }
        }

        return false;
    }

    private class Node implements Comparable<Node> {
        int x;
        int y;
        int d;

        Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public int compareTo(Node n) {
            return this.d - n.d;
        }
    }
}