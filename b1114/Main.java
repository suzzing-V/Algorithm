import java.util.*;
import java.io.*;

// 시간 복잡도: 20000 * log(10^9)
// 공간 복잡도: O(k)
// 가장 긴 통나무의 길이를 X로 했을 때, C번 이하로 자르면서 모든 통나무를 X보다 작거나 크게 자를 수 있는가?
// 통나무를 X보다 작거나 같도록 자르되, X와 가깝게 잘라야 C번 안에 조건을 만족할 수 있는 가능성이 높아진다.
// 가장 첫번째로 자르는 위치를 구하기 쉽게 하기 위해 오른쪽부터 자른다.
public class Main {

    private static int l;
    private static int k;
    private static int c;
    private static int[] cut;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        l = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        // 자르는 위치는 중복된 값이 나올 수 있으므로 중복값을 제거하고, 차례대로 자르기 위해 정렬한다.
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < k; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }
        cut = new int[set.size()];
        k = set.size();
        int i = 0;
        for(int s : set) {
            cut[i ++] = s;
        }
        Arrays.sort(cut);

        int max_length = binarySearch(1, l);

        System.out.println(max_length + " " + get_first_cut(max_length));
    }

    private static int binarySearch(int start, int end) {
        if(start >= end) return start;

        int mid = (start + end) / 2;
//        System.out.println("mid: " + mid);

        // 만들 수 없으면 -1, 아니면 가장 처음으로 자르는 위치 반환
        int first_cut = get_first_cut(mid);
//        System.out.println("first_cut: "+ first_cut);
        // 만들 수 없는 경우 MID를 더 늘려줘야 한다.
        if(first_cut == -1) {
            return binarySearch(mid + 1, end);
            // 만들 수 있는 가장 긴 통나무의 길이 중 가장 작은 값을 구해야 하므로 왼쪽 탐색
        } else {
            return binarySearch(start, mid);
        }
    }

    private static int get_first_cut(int max_length) {
        int cnt = c;
        int last_cut = l;

        // 자를 수 있는 구간이 가장 긴 통나무 길이보다 길면 어떻게 해도 모든 통나무가 이 길이보다 작게 만들 수 없으므로 -1 반환
        if(cut[0] > max_length || l - cut[k - 1] > max_length) return -1;
        for(int i = 0; i < k - 1; i++) {
            if(cut[i + 1] - cut[i] > max_length) return -1;
        }

        for(int i = k - 1; i >= 0 && cnt > 0; i --) {
            // 가장 긴 통나무의 길이를 넘는 가장 첫 위치의 오른쪽 위치를 자른다. -> MAX_LENGTH보다 작거나 같으면서 가장 가까운 길이로 자른다.
            if(last_cut - cut[i] > max_length) {
                cnt --;
                last_cut = cut[i + 1];
            }
        }

        // 횟수가 남았다는 건 I == 0이 될때까지 조건에 맞게 다 잘랐다는 의미이다. 더 잘라도 조건에 맞으므로 가장 왼쪽을 자른다.
        if(cnt > 0) {
            return cut[0];
        }

        // 횟수를 다 사용한 경우, 남은 길이가 MAX_LENGTH보다 작거나 같으면 조건에 맞다. 그러므로 마지막으로 자른 위치를 반환한다.
        if(last_cut <= max_length) {
            return last_cut;
        }
        // 자를 수 있는 최대 크기로 자르면서 횟수도 다 사용했음에도 남은 길이가 mAX_LENGTH보다 크면, 가장 긴 통나무의 길이를 MAX_LENGTH로 하면서 자를 수 없다. 따라서 -1 반환
        return -1;
    }
}
