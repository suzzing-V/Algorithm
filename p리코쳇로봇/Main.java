import java.util.*;

class Solution {
    boolean[][] visited;
    String[][] b;
    public int solution(String[] board) {
        visited = new boolean[board.length][board[0].length()];
        b = new String[board.length][board[0].length()];

        Pos start = null;
        for(int i = 0; i < board.length; i++) {
            String[] split = board[i].split("");
            for(int j = 0; j < board[i].length(); j++) {
                b[i][j] = split[j];
                if(b[i][j].equals("R")) {
                    start = new Pos(i, j, 0);
                }
            }
        }
        int answer = bfs(start);
        return answer;
    }

    class Pos {
        int x;
        int y;
        int cnt;

        Pos(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    private int bfs(Pos start) {
        Queue<Pos> queue = new LinkedList<>();
        queue.add(start);

        int min = -1;
        while(!queue.isEmpty()) {
            Pos curr = queue.remove();
            // System.out.println(curr.x + " " + curr.y + " " + curr.cnt);

            if(b[curr.x][curr.y].equals("G")) {
                min = curr.cnt;
                // System.out.println("도달");
                break;
            }

            if(visited[curr.x][curr.y]) {
                continue;
            }
            visited[curr.x][curr.y] = true;

            Pos left = slideLeft(curr);
            // System.out.println("left: " + left.x + " " + left.y + " " + left.cnt);
            Pos right = slideRight(curr);
            // System.out.println("right: " + right.x + " " + right.y + " " + right.cnt);
            Pos up = slideUp(curr);
            // System.out.println("up: " + up.x + " " + up.y + " " + up.cnt);
            Pos down = slideDown(curr);
            // System.out.println("down: " + down.x + " " + down.y + " " + down.cnt);

            if(left != null) {
                queue.add(left);
            }
            if(right != null) {
                queue.add(right);
            }
            if(up != null) {
                queue.add(up);
            }
            if(down != null) {
                queue.add(down);
            }
        }

        return min;
    }

    private Pos slideLeft(Pos curr) {
        int i = curr.y;
        Pos pos = null;
        while(true) {
            if(i + 1 < b[0].length && b[curr.x][i].equals("D")) {
                pos = new Pos(curr.x, i + 1, curr.cnt + 1);
                break;
            }
            if(i <= 0) {
                pos = new Pos(curr.x, i, curr.cnt + 1);
                break;
            }
            i --;
        }
        return pos;
    }

    private Pos slideRight(Pos curr) {
        int i = curr.y;
        Pos pos = null;
        while(true) {
            if(i - 1 >= 0 && b[curr.x][i].equals("D")) {
                pos = new Pos(curr.x, i - 1, curr.cnt + 1);
                break;
            }
            if(i >= b[0].length - 1) {
                pos = new Pos(curr.x, i, curr.cnt + 1);
                break;
            }
            i ++;
        }
        return pos;
    }

    private Pos slideUp(Pos curr) {
        int i = curr.x;
        Pos pos = null;
        while(true) {
            if(i + 1 < b.length && b[i][curr.y].equals("D")) {
                pos = new Pos(i + 1, curr.y, curr.cnt + 1);
                break;
            }
            if(i <= 0) {
                pos = new Pos(i, curr.y, curr.cnt + 1);
                break;
            }
            i--;
        }
        return pos;
    }

    private Pos slideDown(Pos curr) {
        int i = curr.x;
        Pos pos = null;
        while(true) {
            if(i - 1 >= 0 && b[i][curr.y].equals("D")) {
                pos = new Pos(i - 1, curr.y, curr.cnt + 1);
                break;
            }
            if(i >= b.length - 1) {
                pos = new Pos(i, curr.y, curr.cnt + 1);
                break;
            }
            i++;
        }
        return pos;
    }
}
