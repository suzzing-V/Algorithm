import java.util.*;

class Solution {

    private int[][] board;
    private int[][] dir = { {1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private boolean[][][] visited;

    private class Node {
        private int x1;
        private int y1;
        private int x2;
        private int y2;
        private int cost;
        private int isVertical;

        private Node(int x1, int y1, int x2, int y2, int cost, int isVertical) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.cost = cost;
            this.isVertical = isVertical;
        }
    }

    public int solution(int[][] board1) {
        board = board1;
        visited = new boolean[board.length][board.length][2];

        return bfs();
    }

    private int bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 0, 1, 0, 0));

        while(!queue.isEmpty()) {
            Node curr = queue.remove();
            // System.out.println(curr.x1 + " " + curr.y1 + " " + curr.x2 + " "+ curr.y2 + " " + curr.cost);
            if(curr.x1 < 0 || curr.x2 < 0 || curr.y1 < 0 || curr.y2 < 0 || curr.x1 >= board.length || curr.x2 >= board.length || curr.y1 >= board.length || curr.y2 >= board.length) {
                // System.out.println("범위");
                continue;
            }
            if(visited[curr.x1][curr.y1][curr.isVertical] && visited[curr.x2][curr.y2][curr.isVertical]) {
                // System.out.println("방문");
                continue;
            }
            if(board[curr.x1][curr.y1] == 1 || board[curr.x2][curr.y2] == 1) {
                // System.out.println("벽");
                continue;
            }

            if((curr.x1 == board.length - 1 && curr.y1 == board.length - 1)
                || (curr.x2 == board.length - 1 && curr.y2 == board.length - 1)) {
                return curr.cost;
            }

            visited[curr.x1][curr.y1][curr.isVertical] = true;
            visited[curr.x2][curr.y2][curr.isVertical] = true;
            for(int i = 0; i < 4; i++) {
                int nx1 = curr.x1 + dir[i][0];
                int ny1 = curr.y1 + dir[i][1];
                int nx2 = curr.x2 + dir[i][0];
                int ny2 = curr.y2 + dir[i][1];

                queue.add(new Node(nx1, ny1, nx2, ny2, curr.cost + 1, curr.isVertical));
            }

            // 수평인 경우
            if(curr.x1 == curr.x2) {
                int maxY = Math.max(curr.y1, curr.y2);
                int minY = Math.min(curr.y1, curr.y2);
                if(curr.x1 + 1 < board.length && board[curr.x1 + 1][curr.y1] == 0 && board[curr.x2 + 1][curr.y2] == 0) {
                    queue.add(new Node(curr.x1, minY, curr.x2 + 1, maxY - 1, curr.cost + 1, 1));
                    queue.add(new Node(curr.x2, maxY, curr.x1 + 1, minY + 1, curr.cost + 1, 1));
                }

                if(curr.x1 - 1 >= 0 && board[curr.x1 - 1][curr.y1] == 0 && board[curr.x2 - 1][curr.y2] == 0) {
                    queue.add(new Node(curr.x1, minY, curr.x2 - 1, maxY - 1, curr.cost + 1, 1));
                    queue.add(new Node(curr.x2, maxY, curr.x1 - 1, minY + 1, curr.cost + 1, 1));
                }
            }

            // 수직인 경우
            if(curr.y1 == curr.y2) {
                int maxX = Math.max(curr.x1, curr.x2);
                int minX = Math.min(curr.x1, curr.x2);
                if(curr.y1 + 1 < board.length && board[curr.x1][curr.y1 + 1] == 0 && board[curr.x2][curr.y2 + 1] == 0) {
                    queue.add(new Node(minX, curr.y1, maxX - 1, curr.y1 + 1, curr.cost + 1, 0));
                    queue.add(new Node(maxX, curr.y2, minX + 1, curr.y2 + 1, curr.cost + 1, 0));
                }

                if(curr.y1 - 1 >= 0 && board[curr.x1][curr.y1 - 1] == 0 && board[curr.x2][curr.y2 - 1] == 0) {
                    queue.add(new Node(minX, curr.y1, maxX - 1, curr.y1 - 1, curr.cost + 1, 0));
                    queue.add(new Node(maxX, curr.y2, minX + 1, curr.y2 - 1, curr.cost + 1, 0));
                }
            }
        }
        return 0;
    }
}
