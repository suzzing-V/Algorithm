import java.io.*;
import java.util.*;

public class Main {

    static double[] power = new double[5];
    static double[][][] percent = new double[5][5][3]; // 이기고/비기고/지고
    static int[][] dir = { {1, 2} , {1, 3}, {1, 4}, {2, 3}, {2, 4}, {3, 4} };
    static double result = 0;
    static int[] board = new int[6];

    static class Score implements Comparable<Score> {
        int rein;
        int score;

        Score(int rein, int score) {
            this.rein = rein;
            this.score = score;
        }

        @Override
        public int compareTo(Score o) {
            if(o.score == this.score) {
                return this.rein - o.rein;
            }
            return o.score - this.score;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 1; i <= 4; i++) {
            power[i] = Double.parseDouble(st.nextToken());
        }

        for(int i = 1; i <= 4; i++) {
            for(int j = i + 1; j <= 4; j++) {
                percent[i][j][0] = (4 * power[i]) / (5 * power[i] + 5 * power[j]);
                percent[i][j][1] = (power[i] + power[j]) / (5 * power[i] + 5 * power[j]);
                percent[i][j][2] = (4 * power[j]) / (5 * power[i] + 5 * power[j]);
            }
        }

        play(0, new Score[]{new Score(0, 0), new Score(1, 0), new Score(2, 0), new Score(3, 0), new Score(4, 0)});
        bw.write(String.format("%.3f", result * 100));
        bw.close();
    }

    private static void play(int pos, Score[] scores) {
        if(pos >= 6) {
            // for(int i =0; i < 5; i++) {
            //     System.out.print(scores[i].score + " ");
            // }
            Score[] tmp = scores.clone();
            Arrays.sort(tmp);

            // for(int i = 0; i < 6; i ++) {
            //     System.out.print(board[i] + " ");
            // }
            // System.out.println();
            if(tmp[0].rein == 1 || tmp[1].rein == 1) {
                result += calPercent();
            }
            return;
        }
        // 승리
        scores[dir[pos][0]].score += 3;
        board[pos] = 1;
        play(pos + 1, scores);
        scores[dir[pos][0]].score -= 3;

        // 비김
        scores[dir[pos][0]].score += 1;
        scores[dir[pos][1]].score += 1;
        board[pos] = 0;
        play(pos + 1, scores);
        scores[dir[pos][0]].score -= 1;
        scores[dir[pos][1]].score -= 1;

        // 짐
        scores[dir[pos][1]].score += 3;
        board[pos] = -1;
        play(pos + 1, scores);
        scores[dir[pos][1]].score -= 3;
    }

    private static double calPercent() {
        double total = 1;

        for(int i = 0; i < 6; i++) {
            if(board[i] == 1) {
                total *= percent[dir[i][0]][dir[i][1]][0];
            } else if(board[i] == 0) {
                total *= percent[dir[i][0]][dir[i][1]][1];
            } else {
                total *= percent[dir[i][0]][dir[i][1]][2];
            }
        }
        return total;
    }
}

