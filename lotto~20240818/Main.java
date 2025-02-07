import java.util.*;

public class Main {

    public static void main(String[] args) {
        Set<Integer> hits = new HashSet<>();
        Random random = new Random();

        // 6개의 숫자를 중복 없이 뽑기
        while (hits.size() < 6) {
            int number = random.nextInt(45) + 1; // 1부터 45까지의 숫자
            hits.add(number);
        }

        // 결과 출력
        System.out.println("당첨 숫자: " + hits.stream().sorted().toList());
    }
}
