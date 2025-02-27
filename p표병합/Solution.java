import java.util.*;

class Solution {

    private String[][] chart = new String[51][51];
    private Pos[][] parent = new Pos[51][51];


    private class Pos implements Comparable<Pos> {
        private int x;
        private int y;

        private Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if(this == o) return true;
            if(o == null || this.getClass() != o.getClass()) return false;
            Pos pos = (Pos)o;
            return this.x == pos.x && this.y == pos.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public int compareTo(Pos o) {
            if(o.x == this.x) {
                return this.y - o.y;
            }
            return this.x - o.x;
        }
    }

    public String[] solution(String[] commands) {

        List<String> result = new ArrayList<>();
        for(int i = 1; i <= 50; i++) {
            for(int j = 1; j <= 50; j++) {
                Pos pos = new Pos(i, j);
                parent[i][j] = pos;
            }
        }

        for(int i = 0; i < commands.length; i++) {
            String[] split = commands[i].split(" ");
            String cmd = split[0];
            if(cmd.equals("UPDATE")) {
                if(split.length == 4) {
                    int r = Integer.parseInt(split[1]);
                    int c = Integer.parseInt(split[2]);
                    String value = split[3];

                    Pos root = find(new Pos(r, c));
                    chart[root.x][root.y] = value;
                } else {
                    String value1 = split[1];
                    String value2 = split[2];

                    for(int a = 1; a <= 50; a++) {
                        for(int b = 1; b <= 50; b++) {
                            Pos root = find(new Pos(a, b));
                            if(chart[root.x][root.y] == null) continue;
                            if(chart[root.x][root.y].equals(value1)) chart[root.x][root.y] = value2;
                        }
                    }
                }
            } else if(cmd.equals("MERGE")) {
                int r1 = Integer.parseInt(split[1]);
                int c1 = Integer.parseInt(split[2]);
                int r2 = Integer.parseInt(split[3]);
                int c2 = Integer.parseInt(split[4]);
                Pos root1 = find(new Pos(r1, c1));
                Pos root2 = find(new Pos(r2, c2));
                if(root1.equals(root2)) continue;

                String value = null;
                if(chart[root1.x][root1.y] != null && chart[root2.x][root2.y] == null) value = chart[root1.x][root1.y];
                if(chart[root1.x][root1.y] != null && chart[root2.x][root2.y] != null) value = chart[root1.x][root1.y];
                if(chart[root1.x][root1.y] == null && chart[root2.x][root2.y] != null) value = chart[root2.x][root2.y];
                chart[root1.x][root1.y] = value;
                union(new Pos(r1, c1), new Pos(r2, c2));
                // System.out.println("merge: " + newRoot.x + " " + newRoot.x + " " + nR.x + " " + nR.y);
            } else if(cmd.equals("UNMERGE")) {
                int r = Integer.parseInt(split[1]);
                int c = Integer.parseInt(split[2]);
                Pos root = find(new Pos(r, c));
                String value = chart[root.x][root.y];

                for(int a = 1; a <= 50; a ++) {
                    for(int b = 1; b <= 50; b++) {
                        find(new Pos(a, b));
                    }
                }
                for(int a = 1; a <= 50; a++) {
                    for(int b = 1; b <= 50; b++) {
                        if(find(new Pos(a, b)).equals(root)) {
                            parent[a][b] = new Pos(a, b);
                            chart[a][b] = null;
                        }
                    }
                }
                chart[r][c] = value;
            } else if(cmd.equals("PRINT")) {
                int r = Integer.parseInt(split[1]);
                int c = Integer.parseInt(split[2]);
                Pos root = find(new Pos(r, c));
                if(chart[root.x][root.y] == null) result.add("EMPTY");
                else result.add(chart[root.x][root.y]);
            }

            //     for(int a = 1; a <= 4; a++) {
            //     for(int b = 1; b <= 4; b++) {
            //         System.out.print(chart[a][b] + " ");
            //     }
            //     System.out.println();
            // }
            //     System.out.println();
        }

        // for(int i = 1; i <= 4; i++) {
        //     for(int j = 1; j <= 4; j++) {
        //         System.out.print(chart[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        String[] answer = new String[result.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }

    private void union(Pos p1, Pos p2) {
        p1 = find(p1);
        p2 = find(p2);

        if(!p1.equals(p2)) {
            parent[p2.x][p2.y] = p1;
        }
    }

    private Pos find(Pos p) {
        if(parent[p.x][p.y].equals(p)) {
            return p;
        }
        return parent[p.x][p.y] = find(parent[p.x][p.y]);
    }
}
