import java.util.*;
import java.io.*;

// 시간복잡도: 10^5 * 17 + 2^17
public class Main {

    private static int[] nodes;
    private static int n;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        arr = new int[n + 1];
        int leaf_cnt = (int)Math.pow(2, (int)Math.ceil(Math.log(n) / Math.log(2)));
        int tree_h = (int)(Math.log(leaf_cnt) / Math.log(2)) + 1;
        nodes = new int[(int)Math.pow(2, tree_h) + 1];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        makeTree(1, n, 1);
//        for(int i = 1; i < nodes.length; i++) {
//            System.out.println(nodes[i]);
//        }

        int m = Integer.parseInt(bf.readLine());
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int com = Integer.parseInt(st.nextToken());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            if(com == 1) {
                arr[v1] = v2;
                updateTree(1, n, 1, v1);
//                        for(int a = 1; a < nodes.length; a++) {
//            System.out.print(arr[nodes[a]] + " ");
//        }
            } else if(com == 2) {
                System.out.println(findMin(1, n, 1, v1, v2));
            }
        }
    }

    private static int makeTree(int left, int right, int curr) {
        // 범위에 요소가 하나만 남았을 경우 리프노드이다. 여기에 인덱스 저장하고 리턴.
        if(left == right) {
            return nodes[curr] = left;
        }

        int mid = (left + right) / 2;
        // 왼쪽 자식 노드
        int idx1 = makeTree(left, mid, curr * 2);
        int idx2 = makeTree(mid + 1, right, curr * 2 + 1);

        // 두 값이 같을 경우 인덱스 작은 거 저장
        if(arr[idx1] == arr[idx2]) {
            return nodes[curr] = Math.min(idx1, idx2);
        } else if(arr[idx1] < arr[idx2]) {
            return nodes[curr] = idx1;
        } else {
            return nodes[curr] = idx2;
        }
    }

    private static int updateTree(int left, int right, int curr, int target) {
        // 범위에 요소가 하나만 남았을 경우 리프노드이다. 여기에 인덱스 저장하고 리턴.
        if(left == right) {
            return left;
        }

        int mid = (left + right) / 2;
        // 왼쪽 범위에 속해있다면 왼쪽범위 업뎃 후 오른쪽 자식 노드와 비교
        int idx1 = 0;
        int idx2 = 0;
        if(target >= left && target <= mid) {
            idx1 = updateTree(left, mid, curr * 2, target);
            idx2 = nodes[curr * 2 + 1];
        } else {
            idx1 = nodes[curr * 2];
            idx2 = updateTree(mid + 1, right, curr * 2 + 1, target);
        }

        // 두 값이 같을 경우 인덱스 작은 거 저장
        if(arr[idx1] == arr[idx2]) {
            return nodes[curr] = Math.min(idx1, idx2);
        } else if(arr[idx1] < arr[idx2]) {
            return nodes[curr] = idx1;
        } else {
            return nodes[curr] = idx2;
        }
    }

    private static int findMin(int cl, int cr, int curr, int tl, int tr) {
        // 탐색 범위가 타겟 범위 안에 완전 포함되어 있으면 그 값 리턴
        // 그 탐색 범위 안에서는 더 내려가지 않아도 이게 일짱
        if(tl <= cl && cr <= tr) {
            return nodes[curr];
        } else if(tr < cl || cr < tl) {
            // 탐색 범위가 타겟 범위를 완전 벗어나 있으면 배제시킨다.
            return -1;
        } else {
            // 걸쳐있으면 더 작은 범위로 탐색
            int mid = (cl + cr) / 2;
            int idx1 = findMin(cl, mid, curr * 2, tl, tr);
            int idx2 = findMin(mid + 1, cr, curr * 2 + 1, tl, tr);
            if(idx1 == -1) {
                return idx2;
            } else if(idx2 == -1) {
                return idx1;
            } else {
                if(arr[idx1] == arr[idx2]) {
                    return Math.min(idx1, idx2);
                } else if(arr[idx1] < arr[idx2]) {
                    // 탐색 중에 값을 갱신해 버리면 TR, TL 범위 안의 최소값을 구하고 있기 때문에 그 값으로 업뎃이 된다. 엉뚱한 값으로 업뎃된다.
                    return idx1;
                } else {
                    return idx2;
                }
            }
        }
    }
}
