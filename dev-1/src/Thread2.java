import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Thread1 extends Thread {
    @Override
    public void run() {
        System.out.println("print....."+Thread.currentThread().getName());
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while(true) {
            try {
                if (!((line = bf.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            StringTokenizer st = new StringTokenizer(line);
            String com = st.nextToken();
            List<File> files = new ArrayList<>();
            if(!com.equals("dgrep")) {
                System.out.println("명령어가 올바르지 않습니다.");
                continue;
            }
            String keyword = st.nextToken();
            String relativePath = st.nextToken();

            File path = new File(relativePath);
            // 파일 리스트에 추가
            Function.addFiles(path, files);

            for(File f : files) {
//            System.out.println(f);
                try {
                    Function.grepKeyword(keyword, f.getPath());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
