import java.io.*;
import java.util.*;

// 시간복잡도: 200000 * log(2 * 200000)
// 가장 긴 증가하는 부분 수열 구하기. 그 수열은 놔두고 나머지 원소들만 위치 바꿔주면 최소 횟수로 정렬할 수 있다.
// lis의 마지막 요소보다 새로운 요소가 더 크면 그냥 이 요소 더해주고, 아니면 이진탐색으로 새로운 요소보다 큰 가장 앞 위치에 새로운 요소를 넣는다.
// 이렇게 함으로써 뒤에 더 많은 요소를 넣을 수 있도록 공간을 넓힐 수 있다. 하지만 이 방법은 길이를 구하는 방법이고 실제 lis는 아니다.
public class Main {

    private static int n;
    private static int[] arr;
    private static List<Integer> lis = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lis.add(arr[0]);
        for(int i = 1; i < n; i++) {
            int last = lis.get(lis.size() - 1);
            if(last < arr[i]) {
                lis.add(arr[i]);
            } else {
                // 새로운 요소보다 큰 가장 앞의 위치 구하기
                int pos = findPos(0, lis.size() - 1, arr[i]);
                lis.set(pos, arr[i]);
            }
//            System.out.println(lis.toString());
        }

        System.out.println(n - lis.size());
    }

    private static int findPos(int start, int end, int target) {
        if(start == end) {
            return start;
        }

        int mid = (start + end) / 2;
        int midVal = lis.get(mid);

        if(target >= midVal) {
            return findPos(mid + 1, end, target);
        } else {
            return findPos(start, mid, target);
        }
    }
}
