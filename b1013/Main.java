import java.util.*;
import java.io.*;
import java.util.regex.*;

public class Main {

    private static int t;
    private static String REGEX = "^(100+1+|01)+$"; // * : 0개이상 +: 1개이상 []: 단일문자중 하나
    // 한자리 숫자에 적용
    // | : or
    // 정규식 공백 함부로 쓰지 마라. 공백도 포함된다.
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        t = Integer.parseInt(bf.readLine());
        Pattern p = Pattern.compile(REGEX);

        for(int i = 0; i < t; i ++) {
            Matcher mt = p.matcher(bf.readLine());
            if(mt.matches()) bw.write("YES\n");
            // matcher.matches(regex, str), string.matches(regex): 해당 정규식과 완전히 일치하는지. matcher을 이용한 방법은 문자열 안에 존재하는지 확인할 때 사용한다.
            else bw.write("NO\n");
        }

        bw.close();
    }
}
