import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int mid;
    static PriorityQueue<Integer> left = new PriorityQueue<>((o1, o2) -> {return o2 - o1; });
    static PriorityQueue<Integer> right = new PriorityQueue<>((o1, o2) -> {return o1 - o2; });
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(bf.readLine());

        mid = Integer.parseInt(bf.readLine());
        bw.write(mid + "\n");
        for(int i = 1; i < n; i++) {
            int num = Integer.parseInt(bf.readLine());
            if(mid < num) { // num이 mid의 뒤에 위치할 경우
                if(i % 2 == 0) { // 현재 길이가 짝수면 mid 값 변경
                    right.add(num);
                    left.add(mid);
                    mid = right.remove();
                } else { // 홀수면 mid 값 유지
                    right.add(num);
                }
            } else if(mid > num) {  // num이 mid의 앞에 위치할 경우
                if(i % 2 == 0) {
                    left.add(num);
                } else {
                    right.add(mid);
                    left.add(num);
                    mid = left.remove();
                }
            } else { // num과 mid가 같을 경우
                if(i % 2 == 0) left.add(num); // 현재 길이가 짝수면 작은 쪽에 넣기
                else right.add(num); // 현재 길이가 홀수면 큰 쪽에 넣기
            }
            bw.write(mid + "\n");
        }

        bw.close();
    }
}
