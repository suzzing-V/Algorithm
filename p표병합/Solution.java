import java.util.*;
import java.io.*;

class Solution {

    private Pos[][] parents = new Pos[51][51];
    private String[][] chart = new String[51][51];

    public String[] solution(String[] commands) {
        for(int i = 0; i < 51; i++) {
            for(int j = 0; j < 51; j++) {
                parents[i][j] = new Pos(i, j);
            }
        }

        List<String> result = new ArrayList<>();
        for(int a = 0; a < commands.length; a++) {
            StringTokenizer st = new StringTokenizer(commands[a]);
            String command = st.nextToken();

            if(command.equals("UPDATE")) {
                if(st.countTokens() == 3) {
                    int r = Integer.parseInt(st.nextToken());
                    int c = Integer.parseInt(st.nextToken());
                    String value = st.nextToken();

                    Pos root = find(new Pos(r, c));
                    chart[root.x][root.y] = value;
                } else {
                    String value1 = st.nextToken();
                    String value2 = st.nextToken();

                    updateValue(value1, value2);
                }
            } else if(command.equals("MERGE")) {
                int r1 = Integer.parseInt(st.nextToken());
                int c1 = Integer.parseInt(st.nextToken());
                int r2 = Integer.parseInt(st.nextToken());
                int c2 = Integer.parseInt(st.nextToken());

                // 같은 셀일 경우 무시
                if(find(new Pos(r1, c1)).equals(find(new Pos(r2, c2)))) continue;
                union(new Pos(r1, c1), new Pos(r2, c2));
            } else if(command.equals("UNMERGE")) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                Pos root = find(new Pos(r, c));
                String value = chart[root.x][root.y];
                List<Pos> poses = new ArrayList<>();
                for(int i = 1; i < 51; i ++) {
                    for(int j = 1; j < 51; j++) {
                        Pos rt = find(new Pos(i, j));
                        // 바로 처리해버리면 parents가 바뀌므로 뒤에 있는 것들의 root가 바뀔 수 있다. 따라서 일단 리스트에 넣고 한번에 처리한다.
                        if(rt.x == root.x && rt.y == root.y) {
                            poses.add(new Pos(i, j));
                        }
                    }
                }
                for(Pos pos : poses) {
                    chart[pos.x][pos.y] = null;
                    parents[pos.x][pos.y] = new Pos(pos.x, pos.y);
                }
                chart[r][c] = value;
            } else if(command.equals("PRINT")) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                Pos root = find(new Pos(r, c));
                if(chart[root.x][root.y] == null) result.add("EMPTY");
                else result.add(chart[root.x][root.y]);
            }
            // printParents();
        }

        String[] answer = new String[result.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }

    private void union(Pos p1, Pos p2) {
        p1 = find(p1);
        p2 = find(p2);

        if(chart[p1.x][p1.y] != null && chart[p2.x][p2.y] != null) {
            parents[p2.x][p2.y] = new Pos(p1.x, p1.y);
            chart[p2.x][p2.y] = null;
        } else if(chart[p1.x][p1.y] != null) {
            parents[p2.x][p2.y] = new Pos(p1.x, p1.y);
            chart[p2.x][p2.y] = null;
        } else {
            parents[p1.x][p1.y] = new Pos(p2.x, p2.y);
            chart[p1.x][p1.y] = null;
        }
    }

    private Pos find(Pos p) {
        // System.out.println(p.x + " " + p.y + "의 부모: " + parents[p.x][p.y].x + " " + parents[p.x][p.y].y);
        if(p.equals(parents[p.x][p.y])) {
            // System.out.println();
            // System.out.println();
            return p;
        }

        Pos root = find(parents[p.x][p.y]);
        return parents[p.x][p.y] = root;
    }

    private void updateValue(String curr, String change) {
        for(int i = 0; i < 51; i++) {
            for(int j = 0; j < 51; j++) {
                if(chart[i][j] != null && chart[i][j].equals(curr)) {
                    chart[i][j] = change;
                }
            }
        }
    }

    class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj) return true;
            if(obj == null || getClass() != obj.getClass()) return false;

            Pos p = (Pos) obj;
            return this.x == p.x && this.y == p.y;
        }

        @Override
        public int hashCode() {
            return x + y;
        }
    }
}