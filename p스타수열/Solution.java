import java.util.*;

// 시간 복잡도: O(n)
class Solution {

    // 각 숫자의 인덱스들 저장
    private Map<Integer, List<Integer>> idxs = new HashMap<>();

    public int solution(int[] a) {
        // 각 숫자의 인덱스들 저장
        for(int i = 0; i < a.length; i++) {
            int num = a[i];

            if(idxs.get(num) == null) {
                idxs.put(num, new ArrayList<>());
            }

            idxs.get(num).add(i);
        }

        // 각 숫자를 교집합의 원소로 한 가장 긴 스타수열의 길이 구하기
        // 특정 숫자의 스타수열을 구할 때, 그 숫자의 개수만큼만 검사하기 때문에 전체 연산횟수는 <=50만 but used 배열 초기화때문에 시간초과 난다.
        // 주변에 쓸 숫자가 있는지 판별. 최대한 붙어있는 거 써야됨.
        int answer = 0;
        // 숫자마다 초기화하면 시간 초과남. 따라서 boolean이 아니라 사용하는 숫자로 사용 처리를 해줌으로써 배열 재사용
        // 초기화값이 0이면 수열의 원소도 0일 수 있으므로 오류 발생할 수 있다.
        int[] used = new int[a.length];
        Arrays.fill(used, -1);
        for(int key : idxs.keySet()) {
            List<Integer> idx = idxs.get(key);
            int cnt = 0;
            // 숫자들을 다 써서 스타수열을 만들어도 answer보다 작다면 어떻게 해도 answer보다 길이가 긴 스타수열을 만들 수 없다.
            if(idx.size() * 2 <= answer) continue;

            for(int i = 0; i < idx.size(); i++) {
                int p = idx.get(i);
                // 두 원소가 같으면 안되고, 앞에서 이미 사용했으면 안된다. 앞에 있는 거 우선으로 사용해야 뒤에서 만들 수 있는 가능성이 높아진다.
                if(p - 1 >= 0 && a[p - 1] != key && used[p - 1] != key) {
                    cnt ++;
                    used[p] = key;
                    used[p - 1] = key;
                } else if(p + 1 < a.length && a[p + 1] != key && used[p + 1] != key) {
                    cnt ++;
                    used[p] = key;
                    used[p + 1] = key;
                }
            }
            answer = Math.max(answer, cnt * 2);
        }

        return answer;
    }
}