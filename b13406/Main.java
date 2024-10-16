import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static String[][] map;
    static int min = 15;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new String[n][m];

        int rx = 0;
        int ry = 0;
        int bx = 0;
        int by = 0;
        for(int i = 0; i < n; i++) {
            String[] split = bf.readLine().split("");
            for(int j = 0; j < m; j++) {
                if(split[j].equals("R")) {
                    rx = i;
                    ry = j;
                    map[i][j] = ".";
                } else if(split[j].equals("B")) {
                    bx = i;
                    by = j;
                    map[i][j] = ".";
                } else {
                    map[i][j] = split[j];
                }
            }
        }

        up(1, rx, ry, bx, by);
        down(1, rx, ry, bx, by);
        left(1, rx, ry, bx, by);
        right(1, rx, ry, bx, by);

        if(min == 15) {
            min = -1;
        }
        System.out.println(min);
    }

    public static void up(int count, int rx, int ry, int bx, int by) {
//        System.out.println("up: " + count + " " + rx + " " + ry + " " + bx + " " + by);
        if(count > 10) {
            return;
        }

        rx --;
        bx --;
        boolean goal = false;
        if(rx < bx) {
            while(rx >= 0) {
                if(map[rx][ry].equals("#")) {
                    map[rx + 1][ry] = "R";
                    rx ++;
                    break;
                } else if(map[rx][ry].equals("O")) {
                    goal = true;
                    break;
                }
                rx--;
            }

            while(bx >= 0) {
                if(map[bx][by].equals("#") || map[bx][by].equals("R")) {
                    map[bx + 1][by] = "B";
                    bx ++;
                    break;
                } else if(map[bx][by].equals("O")) {
                    if(!map[rx][ry].equals("O")) {
                        map[rx][ry] = ".";
                    }
                    return;
                }
                bx--;
            }
        } else {
            while(bx >= 0) {
                if(map[bx][by].equals("#")) {
                    map[bx + 1][by] = "B";
                    bx ++;
                    break;
                } else if(map[bx][by].equals("O")) {
                    return;
                }
                bx--;
            }
            while(rx >= 0) {
                if(map[rx][ry].equals("#")|| map[rx][ry].equals("B")) {
                    map[rx + 1][ry] = "R";
                    rx ++;
                    break;
                } else if(map[rx][ry].equals("O")) {
                    map[bx][by] = ".";
                    goal = true;
                    break;
                }
                rx--;
            }
        }

        if(goal) {
//            System.out.println("count: " + count);
            min = Math.min(count, min);
            return;
        }

        map[rx][ry] = ".";
        map[bx][by] = ".";

//        System.out.println("fin: " + count + " " + rx + " " + ry + " " + bx + " " + by);
        down(count + 1, rx, ry, bx, by);
        left(count + 1, rx, ry, bx, by);
        right(count + 1, rx, ry, bx, by);
    }

    public static void down(int count, int rx, int ry, int bx, int by) {
//        System.out.println("down: " + count + " " + rx + " " + ry + " " + bx + " " + by);
        if(count > 10) {
            return;
        }

        rx ++;
        bx ++;
        boolean goal = false;
        if(rx > bx) {
            while(rx < n) {
                if(map[rx][ry].equals("#")) {
                    map[rx - 1][ry] = "R";
                    rx --;
                    break;
                } else if(map[rx][ry].equals("O")) {
                    goal = true;
                    break;
                }
                rx++;
            }

            while(bx < n) {
                if(map[bx][by].equals("#")|| map[bx][by].equals("R")) {
                    map[bx - 1][by] = "B";
                    bx --;
                    break;
                } else if(map[bx][by].equals("O")) {
                    if(!map[rx][ry].equals("O")) {
                        map[rx][ry] = ".";
                    }
                    return;
                }
                bx++;
            }
        } else {
            while(bx < n) {
                if(map[bx][by].equals("#")) {
                    map[bx - 1][by] = "B";
                    bx --;
                    break;
                } else if(map[bx][by].equals("O")) {
                    return;
                }
                bx++;
            }
            while(rx < n) {
                if(map[rx][ry].equals("#")|| map[rx][ry].equals("B")) {
                    map[rx - 1][ry] = "R";
                    rx --;
                    break;
                } else if(map[rx][ry].equals("O")) {
                    map[bx][by] = ".";
                    goal = true;
                    break;
                }
                rx++;
            }
        }

        if(goal) {
//            System.out.println("count: " + count);
            min = Math.min(count, min);
            return;
        }

        map[rx][ry] = ".";
        map[bx][by] = ".";

//        System.out.println("fin: " + count + " " + rx + " " + ry + " " + bx + " " + by);
        up(count + 1, rx, ry, bx, by);
        left(count + 1, rx, ry, bx, by);
        right(count + 1, rx, ry, bx, by);
    }

    public static void left(int count, int rx, int ry, int bx, int by) {
//        System.out.println("left: " + count + " " + rx + " " + ry + " " + bx + " " + by);
        if(count > 10) {
            return;
        }

        boolean goal = false;
        ry --;
        by --;
        if(ry < by) {
            while(ry >= 0) {
                if(map[rx][ry].equals("#")) {
                    map[rx][ry + 1] = "R";
                    ry ++;
                    break;
                } else if(map[rx][ry].equals("O")) {
                    goal = true;
                    break;
                }
                ry--;
            }

            while(by >= 0) {
                if(map[bx][by].equals("#")|| map[bx][by].equals("R")) {
                    map[bx][by + 1] = "B";
                    by ++;
                    break;
                } else if(map[bx][by].equals("O")) {
                    if(!map[rx][ry].equals("O")) {
                        map[rx][ry] = ".";
                    }
                    return;
                }
                by--;
            }
        } else {
            while(by >= 0) {
                if(map[bx][by].equals("#")) {
                    map[bx][by + 1] = "B";
                    by ++;
                    break;
                } else if(map[bx][by].equals("O")) {
                    return;
                }
                by--;
            }

            while(ry >= 0) {
                if(map[rx][ry].equals("#")|| map[rx][ry].equals("B")) {
                    map[rx][ry + 1] = "R";
                    ry ++;
                    break;
                } else if(map[rx][ry].equals("O")) {
                    map[bx][by] = ".";
                    goal = true;
                    break;
                }
                ry--;
            }
        }

        if(goal) {
//            System.out.println("count: " + count);
            min = Math.min(count, min);
            return;
        }

        map[rx][ry] = ".";
        map[bx][by] = ".";

//        System.out.println("fin: " + count + " " + rx + " " + ry + " " + bx + " " + by);
        down(count + 1, rx, ry, bx, by);
        up(count + 1, rx, ry, bx, by);
        right(count + 1, rx, ry, bx, by);
    }

    public static void right(int count, int rx, int ry, int bx, int by) {
//        System.out.println("right: " + count + " " + rx + " " + ry + " " + bx + " " + by);
        if (count > 10) {
            return;
        }

        ry ++;
        by ++;
        boolean goal = false;
        if (ry > by) {
            while (ry < m) {
                if (map[rx][ry].equals("#")) {
                    map[rx][ry - 1] = "R";
                    ry--;
                    break;
                } else if (map[rx][ry].equals("O")) {
                    goal = true;
                    break;
                }
                ry++;
            }

            while (by < m) {
                if (map[bx][by].equals("#")|| map[bx][by].equals("R")) {
                    map[bx][by - 1] = "B";
                    by--;
                    break;
                } else if (map[bx][by].equals("O")) {
                    if(!map[rx][ry].equals("O")) {
                        map[rx][ry] = ".";
                    }
                    return;
                }
                by++;
            }
        } else {
            while (by < m) {
                if (map[bx][by].equals("#")) {
                    map[bx][by - 1] = "B";
                    by--;
                    break;
                } else if (map[bx][by].equals("O")) {
                    return;
                }
                by++;
            }

            while (ry < m) {
                if (map[rx][ry].equals("#")|| map[rx][ry].equals("B")) {
                    map[rx][ry - 1] = "R";
                    ry--;
                    break;
                } else if (map[rx][ry].equals("O")) {
                    map[bx][by] = ".";
                    goal = true;
                    break;
                }
                ry++;
            }
        }

        if (goal) {
//            System.out.println("count: " + count);
            min = Math.min(count, min);
            return;
        }

        map[rx][ry] = ".";
        map[bx][by] = ".";

//        System.out.println("fin: " + count + " " + rx + " " + ry + " " + bx + " " + by);
        down(count + 1, rx, ry, bx, by);
        up(count + 1, rx, ry, bx, by);
        left(count + 1, rx, ry, bx, by);
    }
}
