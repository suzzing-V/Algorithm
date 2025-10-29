import java.io.*;
import java.util.*;

// 36진수중 k개를 고르는 방식이 까다로운 문제였다.
// 각 36진수 digit마다 몇째 자리에서 증가값이 어떻게 되는지 저장한다. (50 * 50);
// 가장 자릿수가 높은 곳에서 증가값이 더 큰 36진수 digit가 먼저 뽑히도록 비교함수를 구현해준다. 이를 위해서 각 node마다 이 digit의 자릿수별 증가값을 저장하는 배열을 두었다. 그리고 비교 시 맨 뒤부터 비교하면서 증가값이 큰 수가 등장했을 경우 그 수의 우선순위를 높게 설정한다.
// 우선순위 큐에서 k만큼 뽑아준다. (log36 * 50 + 36)
// 선택된 수들을 문자열에서 z로 대체해준다. ((36 * 50 + 100) * 50)
// 대체해준 문자열을 Result에 더해준다. 낮은 자릿수부터 result와 문자열의 10진수로 변환한 값을 더해준다. 그 값이 36 이상일 경우 다음 자릿수로 올림해준다.
// 구한 result 배열을 뒤에서부터 돌면서 답을 만들어준다.
// 큰 수를 사용할 수 없어서 우선순위를 정하는 데 어려움을 겪었다.
// 비교 함수를 다채롭게 사용해보자.
public class Main {

    private static int n;
    private static String[] nums;
    private static int k;
    private static int[] result;
    private static Node[] alpha;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        nums = new String[n];
        alpha = new Node[36];
        for(int i = 0; i < n; i++) {
            nums[i] = bf.readLine();
        }
        k = Integer.parseInt(bf.readLine());

        // 각 36진수의 가장 작은 자릿수, 그 자릿수에 있는 36진수의 개수 저장
        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < nums[i].length(); j++) {
                int pos = nums[i].length() - j;
                int c = nums[i].charAt(j);
                int digit10 = toDigit10(c);
                int increase = 35 - digit10;
//                System.out.println((char)c + " " + digit10);
                if(alpha[digit10] == null) {
                    alpha[digit10] = new Node(c, new int[55]);
                    alpha[digit10].inc[pos] += increase;
                } else {
                    alpha[digit10].inc[pos] += increase;
                }
            }
        }

        // 증가하는 수치가 36이상이면 올림을 적용해준다.
        for(int i = 0; i < 36; i++) {
            if(alpha[i] != null) {
                for(int j = 1; j < 54; j ++) {
                    int rest = alpha[i].inc[j] % 36;
                    int mok = alpha[i].inc[j] / 36;
                    alpha[i].inc[j] = rest;
                    alpha[i].inc[j + 1] += mok;
                }
//                System.out.println((char)alpha[i].c + " " + Arrays.toString(alpha[i].inc));
            }
        }

        Set<Integer> selected = new HashSet<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 0; i < 36; i ++) {
                if(alpha[i] != null) pq.add(alpha[i]);
        }

        while(!pq.isEmpty() && selected.size() < k) {
            Node curr = pq.remove();
//            System.out.println((char)curr.c);
//            System.out.println((char)curr.c + " " + curr.increase + " " + curr.cnt + " " + curr.pos);
            selected.add(curr.c);
        }

        // 선택한 k개의 수를 각 문자에서 z로 변경 후 연산
        result = new int[100]; // 각 자리수의 값을 10진수로 표현
        for(int i = 0; i < n; i ++) {
            for(int s : selected) {
//                System.out.print((char)s);
                nums[i] = nums[i].replace((char)s, 'Z');
            }

//            System.out.println(nums[i]);
            // result에 현재 값 더하기
            sum(nums[i]);
//            for(int j = 50; j < result.length; j++) {
//                System.out.print(result[j] + " ");
//            }
//            System.out.println();
        }

        // result의 수를 36진수로 바꿔서 출력
        int start = result.length - 1;
        while(start >= 0 && result[start] == 0) {
            start --;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = start; i >= 1; i --) {
            sb.append((char)toDigit36(result[i]));
        }
        if(sb.toString().length() == 0) sb.append("0");
        System.out.println(sb.toString());
    }

    private static void sum(String num) {
        int nidx = num.length() - 1;
//        for(int i = 0; i < num.length(); i++) {
//            System.out.println(toDigit10(num.charAt(i)));
//        }
        for(int i = 1; i < 54; i ++) {
            int nd = nidx < 0 ? 0 : toDigit10(num.charAt(nidx));
            result[i] += nd;
//            System.out.println("더한 후: " + result[i]);
            int mok = result[i]/36;
            int rest = result[i]%36;
            result[i] = rest;
            result[i + 1] += mok;
//            System.out.println("올림한 후: " + result[i]);
            nidx --;
        }
    }

    private static int toDigit36(int origin) {
        if(origin >= 0 && origin <= 9) {
            return '0' + origin;
        }

        return origin + 55;
    }

    private static int toDigit10(int origin) {
        if(origin >= '0' && origin <= '9') {
            return origin - '0';
        }

        return origin - 55;
    }

    private static class Node implements Comparable<Node> {
        int c;
        int[] inc;

        Node(int c, int[] inc) {
            this.inc = inc;
            this.c = c;
        }

        @Override
        public int compareTo(Node n) {
            for(int i = 54; i > 0; i--) {
                // 만약 같은 자리수에서 증가값이 차이난다면 증가값이 큰 값을 우선으로 뽑도록.
                if(n.inc[i] > inc[i]) return 1;
                if(n.inc[i] < inc[i]) return -1;
            }
            return 0;
        }
    }
}
