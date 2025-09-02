import java.util.*;

// 시간복잡도: O(n)
class Solution {
    private boolean[][] column;
    private boolean[][] bo;
    private PriorityQueue<Node> pq = new PriorityQueue<>();
    private int n;

    public int[][] solution(int n1, int[][] build_frame) {
        n = n1;
        column = new boolean[n + 1][n + 1];
        bo = new boolean[n + 1][n + 1];
        for(int t = 0; t < build_frame.length; t++) {
            int y = build_frame[t][0];
            int x = build_frame[t][1];
            int a = build_frame[t][2];
            int b = build_frame[t][3];

            // 기둥 삭제
            if(a == 0 && b == 0) {
                deleteColumn(x, y);
            } else if(a == 0 && b == 1) {
                installColumn(x, y);
            } else if(a == 1 && b == 0) {
                deleteBo(x, y);
            } else if(a == 1 && b == 1) {
                installBo(x, y);
            }

            // System.out.println(t);
            // for(int i = 0; i <= n; i++) {
            //     for(int j = 0; j <= n; j++) {
            //         System.out.print(column[i][j] ? 1 : 0);
            //     }
            //     System.out.println();
            // }
            // System.out.println();
            // for(int i = 0; i <= n; i++) {
            //     for(int j = 0; j <= n; j++) {
            //         System.out.print(bo[i][j] ? 1 : 0);
            //     }
            //     System.out.println();
            // }
        }

        // 기둥과 보 설치된 거 pq에 넣기
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= n; j++) {
                if(bo[i][j]) pq.add(new Node(j, i, 1));
                if(column[i][j]) pq.add(new Node(j, i, 0));
            }
        }

        int[][] answer = new int[pq.size()][3];
        for(int i = 0; i < answer.length; i++) {
            Node node = pq.remove();
            answer[i][0] = node.x;
            answer[i][1] = node.y;
            answer[i][2] = node.a;
        }
        return answer;
    }

    private void deleteColumn(int x, int y) {
        column[x][y] = false;
        // 위에 기둥이 있는데 이 기둥이 보의 한쪽 끝 부분 위에 있지 않으면 안된다.
        if(x + 1 <= n && column[x + 1][y]) {
            if(!isColumnOnBo(x + 1, y)) {
                column[x][y] = true;
                return;
            }
        }

        // 위에 보가 있으면 그 보는 양쪽 끝 부분이 동시에 연결되어 있거나, 다른 쪽 부분이 기둥 위에 있어야 한다.
        if(x + 1 <= n && bo[x + 1][y]) {
            if(!(isBoConnected(x + 1, y) || isBoOnColumn(x + 1, y))) {
                column[x][y] = true;
                return;
            }
        }

        if(x + 1 <= n && y - 1 >= 0 && bo[x + 1][y - 1]) {
            if(!(isBoConnected(x + 1, y - 1) || isBoOnColumn(x + 1, y - 1))) {
                column[x][y] = true;
                return;
            }
        }
    }

    private void installColumn(int x, int y) {
        if(x == 0 || isColumnOnBo(x, y) || isColumnOnColumn(x, y)) {
            column[x][y] = true;
        }
    }

    private void deleteBo(int x, int y) {
        bo[x][y] = false;

        // 위에 기둥 있다면 두 기둥은 다른 쪽 보의 끝부분 위에 있거나, 다른 기둥 위에 있어야 한다.
        if(column[x][y]) {
            if(!(isColumnOnBo(x, y) || isColumnOnColumn(x, y))) {
                bo[x][y] = true;
                return;
            }
        }

        if(y + 1 <= n && column[x][y + 1]) {
            if(!(isColumnOnBo(x, y + 1) || isColumnOnColumn(x, y + 1))) {
                bo[x][y] = true;
                return;
            }
        }

        // 옆에 보 있다면 두 보는 한쪽 끝 부분이 기둥위에 있어야 한다.
        if(y - 1 >= 0 && bo[x][y - 1]) {
            if(!isBoOnColumn(x, y - 1)) {
                bo[x][y] = true;
                return;
            }
        }

        if(y + 1 <= n && bo[x][y + 1]) {
            if(!isBoOnColumn(x, y + 1)) {
                bo[x][y] = true;
                return;
            }
        }
    }

    private void installBo(int x, int y) {
        if(isBoOnColumn(x, y) || isBoConnected(x, y)) bo[x][y] = true;
    }

    private boolean isBoConnected(int x, int y) {
        if(y - 1 >= 0 && y + 1 <= n && bo[x][y - 1] && bo[x][y + 1]) return true;
        return false;
    }

    private boolean isBoOnColumn(int x, int y) {
        if(x - 1 >= 0 && (column[x - 1][y] || (y + 1 <= n && column[x - 1][y + 1]))) return true;
        return false;
    }

    private boolean isColumnOnBo(int x, int y) {
        if(bo[x][y] || (y - 1 >= 0 && bo[x][y - 1])) return true;
        return false;
    }

    private boolean isColumnOnColumn(int x, int y) {
        if(x - 1 >= 0 && column[x - 1][y]) return true;
        return false;
    }

    private class Node implements Comparable<Node> {
        int x;
        int y;
        int a;

        Node(int x, int y, int a) {
            this.x = x;
            this.y = y;
            this.a = a;
        }

        @Override
        public int compareTo(Node n) {
            if(this.x == n.x) {
                if(this.y == n.y) {
                    return this.a - n.a;
                }
                return this.y - n.y;
            }
            return this.x - n.x;
        }
    }
}