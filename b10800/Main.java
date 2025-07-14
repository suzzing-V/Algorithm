import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        Ball[] balls = new Ball[n];
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());

            balls[i] = new Ball(i, color, size);
        }
        // 색과 크기 모두 같은 경우 처음 나온 값으로 넣어야하므로 색깔도 정렬해줘야한다.
        Arrays.sort(balls);

        int[] answer = new int[n];
        int sum = 0;
        int[] sumByColor = new int[2 * (int)Math.pow(10, 5) + 1];
        int[] sumBySize = new int[2001];
        for(int i = 0; i < n; i++) {
            Ball curr = balls[i];
//            System.out.println();
//            System.out.println(curr.idx + " " + curr.color + " " + curr.size);
            sumByColor[curr.color] += curr.size;
            sumBySize[curr.size] += curr.size;
            sum += curr.size;
            // 현재 공 포함해서 지금까지 공의 크기 합에서 현재 공과 같은 색의 공의 크기 합과 현재 공과 같은 무게의 공의 크기 합을 빼야 한다.
            // 이렇게 뺄 경우 현재공의 색크기합과 현재 공의 크기크기합 모두 같은 경우는 중복으로 빠진다. 원래는 같은 색과 크기를 가진 공의 수 * 크기 를 빼야하지만 편의를 위해 색과 크기가 같을 경우 맨 처음 등장한 값으로 세팅해주고 맨 처음 등장값만 구한다. 이렇게 될 경우 맨 처음 등장한 공의 크기와 색을 가진 공은 현재공밖에 없으므로 현재 공의 크기를 뺸다.
            answer[curr.idx] = sum - sumByColor[curr.color] - sumBySize[curr.size] + curr.size;
            if(i != 0 && balls[i - 1].color == curr.color && balls[i - 1].size == curr.size) answer[curr.idx] = answer[balls[i - 1].idx];
        }

        for(int i = 0; i < n; i++) {
            System.out.println(answer[i]);
        }
    }

    private static class Ball implements Comparable<Ball> {
        private int idx;
        private int color;
        private int size;

        Ball(int idx, int color, int size) {
            this.idx = idx;
            this.color = color;
            this.size = size;
        }

        @Override
        public int compareTo(Ball ball) {
            if(ball.size == this.size) {
                return this.color - ball.color;
            }
            return this.size - ball.size;
        }
    }
}
