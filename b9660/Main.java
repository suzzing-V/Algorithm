import java.util.*;
import java.io.*;

// 시간복잡도: O(1)
// 게임이론에서 무조건 한 사람을 기준으로 최선의 선택을 한다. 그 말은 상대가 지는 경우가 하나라도 있으면, 무조건 이 사람이 이긴다는 의미이다.
public class Main {

    private static long n;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Long.parseLong(bf.readLine());

        // tfttttf 의 주기가 반복된다.
        if(n % 7 == 0 || n % 7 == 2) System.out.println("CY");
        else System.out.println("SK");
    }
}
