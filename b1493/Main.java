import java.util.*;
import java.io.*;

// 경우가 없을 경우 바로 시스템종료하는 것도 시간 복잡도를 줄이는 방법 중 하나
// 재귀가 한번 실행될때마다 직육면체의 크기가 크게 줄어들기 때문에 log3(10^)의 시간복잡도 갖는다.
public class Main {

    private static int l;
    private static int w;
    private static int h;
    private static int n;
    private static int[] quan;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        l = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(bf.readLine());
        quan = new int[n];

        for(int i = 0; i< n; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            quan[a] = b;
        }

        int cnt = getCubeCnt(l, w, h);
        System.out.println(cnt);
    }

    private static int getCubeCnt(int l, int w, int h) {
//        System.out.println(l + " " + w + " " + h);
        if(l == 0 || w == 0 || h == 0) return 0;

        int min = Math.min(l, Math.min(w, h));
        int kind = -1;
        // 넣을 수 있는 2제곱의 상자가 주어진 큐브 크기보다 클 수 있으므로 그럴 경우는 n- 1을 선택해준다.
        int max = Math.min((int)(Math.log(min) / Math.log(2)), n - 1);
        for(int i = max; i >= 0; i --) {
            // 수량 없을 시 넘어가기
            if(quan[i] == 0) {
                continue;
            }

            quan[i] --;
            kind = i;
            break;
        }

//        System.out.println(l + " " + w + " " + h + "큐브선택: " + cube);
        if(kind == -1) {
            System.out.println("-1");
            System.exit(0);
        }
        int cube = (int)Math.pow(2, kind);
        int s1 = getCubeCnt(l, w, h - cube);
        if(s1 == -1) return -1;
        int s2 = getCubeCnt(cube, w - cube, cube);
        if(s2 == -1) return -1;
        int s3 = getCubeCnt(l - cube, w, cube);
        if(s3 == -1) return -1;

//        System.out.println(l + " " + w + " " + h + "큐브 개수: " + s1 + " " + s2 + " "+ s3);
        return s1 + s2 + s3 + 1;
    }
}
