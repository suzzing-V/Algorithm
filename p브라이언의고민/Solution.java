import java.util.*;

// 대문자가 붙어있는 경우 사이에 공백이 있다고 가정. 꼭 대문자끼리 단어 안 만들어도 된다.
// 대문자 뒤의 소문자의 개수가 2개면 대문자를 분리시키고, 소문자의 개수가 2개가 아니면 대문자를 붙인다.
class Solution {

    private boolean[] isUsed = new boolean[26];
    public String solution(String sentence) {
        StringBuilder answer = new StringBuilder();

        int i = 0;
        while(i < sentence.length()) {
            char first = sentence.charAt(i);
            // System.out.println("첫글자: " + first);
            if(isLower(first)) { // 첫 글자가 특수기호면 감싸는 규칙
                // System.out.println("첫글자 소문자");
                if(isUsed[(int)(first - 'a')]) return "invalid";
                isUsed[(int)(first - 'a')] = true;
                List<Integer> lower_idx = new ArrayList<>();
                for(int j = i; j < sentence.length(); j ++) {
                    if(sentence.charAt(j) == first) lower_idx.add(j);
                }

                if(lower_idx.size() != 2) return "invalid"; // 무조건 두개여야 감쌈
                String word = sentence.substring(lower_idx.get(0) + 1, lower_idx.get(1));
                if(word.equals("")) return "invalid";
                // System.out.println("첫글자 소문자: " + word);
                if(!checkRule1(word)) return "invalid";
                answer.append(word + " ");
                i = lower_idx.get(1);
            } else { // 감싸는 규칙 없음
                if(i + 1 >= sentence.length() || isUpper(sentence.charAt(i + 1))) { // 다음 문자가 없거나 다음 문자가 대문자면 한글자로 이루어진 단어
                    answer.append(sentence.charAt(i) + " ");
                    // System.out.println("대문자 하나: " + sentence.charAt(i));
                } else {
                    char second = sentence.charAt(i + 1);
                    List<Integer> lower_idx = new ArrayList<>();
                    for(int j = i; j < sentence.length(); j++) {
                        if(sentence.charAt(j) == second) { // 타겟 소문자와 같으면 리스트에 넣기
                            lower_idx.add(j);
                        }
                    }

                    if(lower_idx.size() == 2) { // 타겟 소문자의 수가 2면 맨앞 대문자 하나로 떼고, 규칙 2로 단어 만들기
                        answer.append(first + " ");
                    } else { // 타겟 소문자의 수가 2가 아니면 규칙 1에 해당하는 단어 만들기
                        if(lower_idx.get(lower_idx.size() - 1) == sentence.length() - 1) return "invalid"; // 타겟 소문자가 문자열의 마지막이라면 규칙 1에 부합할 수 없다
                        String word = sentence.substring(lower_idx.get(0) - 1, lower_idx.get(lower_idx.size() - 1) + 2);
                        if(!checkRule1(word)) return "invalid";
                        answer.append(word + " ");
                        // System.out.println("타겟 소문자가 두개 아님: " + word);
                        i = lower_idx.get(lower_idx.size() - 1) + 1;
                    }
                }
            }
            i ++;
        }
        return answer.toString().replaceAll("[a-z]", "").trim();
    }

    private boolean isUpper(char c) {
        if(c >= 'A' && c <= 'Z') return true;
        return false;
    }

    private boolean isLower(char c) {
        if(c >= 'a' && c <= 'z') return true;
        return false;
    }

    private boolean checkRule1(String str) {
        if(str.length() == 1 || isUpper(str.charAt(1))) { // 한글자이거나 두번째 글자가 대문자면 다 대문자여야함
            for(int i = 0; i < str.length(); i++) {
                if(isLower(str.charAt(i))) return false;
            }
        } else if(str.length() % 2 == 0) { // 길이는 홀수여야 함.
            return false;
        } else {
            char target = str.charAt(1);
            for(int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if(i % 2 == 0 && isLower(c)) return false;
                if(i % 2 == 1 && isUpper(c)) return false;
                if(i % 2 == 1 && (target != c)) return false;
            }
            if(isUsed[(int)(target - 'a')]) return false;
            isUsed[(int)(target - 'a')] = true;
        }

        return true;
    }
}
