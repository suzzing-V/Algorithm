import java.util.*;

// get연산은 O(n)으로 느리다.
// linkedlist와 deque는 앞부분과 뒷부분을 O(1)만에 삽입, 삭제, 조회할 수 있다.
// 시간복잡도: 7 * 10^5
class Solution {
    private Deque<Integer> left_col = new ArrayDeque<>();
    private Deque<Integer> right_col = new ArrayDeque<>();
    private LinkedList<Deque<Integer>> rows = new LinkedList<>();
    private int n;
    private int m;

    public int[][] solution(int[][] rc, String[] operations) {
        n = rc.length;
        m = rc[0].length;

        // 1열 left_col에 저장
        // c열 right_col에 저장
        for(int i = 0; i < rc.length; i ++) {
            left_col.addLast(rc[i][0]);
            right_col.addLast(rc[i][m - 1]);
        }

        // 중간 행들 rows에 넣기
        for(int i = 0; i < n; i++) {
            Deque<Integer> row = new ArrayDeque<>();
            for(int j = 1; j < m - 1; j++) {
                row.addLast(rc[i][j]);
            }
            rows.add(row);
        }

        // System.out.println("left_col: " + left_col.toString());
        //     System.out.println("right_col: " + right_col.toString());
        //     for(int x = 0; x < n; x++) {
        //     System.out.println(rows.get(x).toString());
        //         }

        for(int i = 0; i < operations.length; i++) {
            if(operations[i].equals("Rotate")) {
                rotate();
            } else if(operations[i].equals("ShiftRow")) {
                shift();
            }
            // System.out.println("left_col: " + left_col.toString());
            // System.out.println("right_col: " + right_col.toString());
            // for(int x = 0; x < n; x++) {
            // System.out.println(rows.get(x).toString());
            // }
        }

        int[][] answer = new int[n][m];
        for(int i = 0; i < n; i++) {
            answer[i][0] = left_col.removeFirst();
            answer[i][m - 1] = right_col.removeFirst();
            Deque<Integer> row = rows.removeFirst();
            for(int j = 1; j < m - 1; j++) {
                answer[i][j] = row.removeFirst();
            }
        }
        return answer;
    }

    private void shift() {
        int left_last = left_col.removeLast();
        int right_last = right_col.removeLast();
        left_col.addFirst(left_last);
        right_col.addFirst(right_last);

        Deque<Integer> remove = rows.removeLast();
        // O(1)
        rows.addFirst(remove);
    }

    private void rotate() {
        // left_col의 맨 첫번째 0행의 제일 처음에 넣기
        int first = left_col.removeFirst();
        rows.peekFirst().addFirst(first);
        // 0행의 제일 마지막을 right_col의 맨 첫번째에 넣기
        int last = rows.peekFirst().removeLast();
        right_col.addFirst(last);
        // right_col의 제일 마지막을 마지막 행의 마지막에 넣기
        last = right_col.removeLast();
        rows.peekLast().addLast(last);
        // 마지막 행의 가장 첫번째를 left_col의 제일 마지막에 넣기
        first = rows.peekLast().removeFirst();
        left_col.addLast(first);
    }
}