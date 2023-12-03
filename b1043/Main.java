import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] root;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        //첫 루트노드 자기자신으로 초기화
        root = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            root[i] = i;
        }

        //진실을 아는 사람 리스트 만들기
        st = new StringTokenizer(bf.readLine());
        int knowNum = Integer.parseInt(st.nextToken());
        List<Integer> know = new ArrayList<>();
        for(int i = 0; i < knowNum; i++) {
            know.add(Integer.parseInt(st.nextToken()));
        }

        //같은 파티에 참가한 사람들 연결하기
        List<Integer>[] party = new ArrayList[m];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int personNum = Integer.parseInt(st.nextToken());
            party[i] = new ArrayList<>();

            int first = Integer.parseInt(st.nextToken());
            party[i].add(first);
            for(int j = 1; j < personNum; j++) {
                int rest = Integer.parseInt(st.nextToken());

                union(first, rest);
                party[i].add(rest);
            }
        }

        int count = 0;
        for(int i = 0; i < m; i++) {
            count += isFake(party[i].get(0), know);
        }
        bw.write(String.valueOf(count));
        bw.close();
    }

    
    public static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        root[rootY] = rootX;
    }

    public static int find(int x) {
        if(root[x] == x) return x;
        else return root[x] = find(root[x]);
    }

    public static int isFake(int represent, List<Integer> know) {
        for(int person : know) {
            if(find(represent) == find(person)) return 0;
        }
        return 1;
    }
}