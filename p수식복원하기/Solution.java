import java.util.*;

class Solution {

    private List<Integer> formats = new ArrayList<>();
    private List<String> incompleted = new ArrayList<>();
    private List<String> completed = new ArrayList<>();

    public String[] solution(String[] expressions) {
        // 가장 큰 숫자 구하기 -> 1 더하면 가능한 진법, 비어있는 수식 모으기
        int maxDigit = 0;
        for(int i = 0; i < expressions.length; i++) {
            String formula = expressions[i];
            if(formula.contains("X")) incompleted.add(formula);
            else completed.add(formula);

            for(int j = 0; j < formula.length(); j++) {
                int digit = formula.charAt(j);
                if(digit >= '0' && digit <= '9') {
                    maxDigit = Math.max(maxDigit, digit - '0');
                }
            }
        }
        maxDigit ++;

        // 가능한 진법 담기
        for(int i = maxDigit; i <= 9; i ++) {
            formats.add(i);
        }

        // 온전한 식에 맞지 않는 진법들 삭제하기
        for(String formula : completed) {
            for(int i = 0; i < formats.size(); i++) {
                int format = formats.get(i);
                StringTokenizer st = new StringTokenizer(formula);
                int n1 = toTen(st.nextToken(), format);
                String sign = st.nextToken();
                int n2 = toTen(st.nextToken(), format);
                st.nextToken();
                int answer = toTen(st.nextToken(), format);

                if((sign.equals("+") && (n1 + n2 != answer)) || sign.equals("-") && (n1 - n2 != answer)) {
                    formats.remove(i);
                    i --;
                }
            }
        }

        // 불완전한 수식 답 여러 개면 ?, 아니면 답 넣기
        for(int i = 0; i < incompleted.size(); i++) {
            String formula = incompleted.get(i);
            int answer = -1;
            for(int format : formats) {
                StringTokenizer st = new StringTokenizer(formula);
                int n1 = toTen(st.nextToken(), format);
                String sign = st.nextToken();
                int n2 = toTen(st.nextToken(), format);
                st.nextToken();
                int result = Integer.parseInt(toN(sign.equals("+") ? n1 + n2 : n1 - n2, format));
                if(answer == -1) {
                    answer = result;
                } else if(result != answer) { // 앞의 값들과 다르다면 ?
                    answer = -2;
                    break;
                }
            }

            String x = (answer == -2 ? "?" : String.valueOf(answer));
            incompleted.set(i, formula.replaceAll("X", x));
        }

        String[] answer = incompleted.toArray(new String[0]);
        return answer;
    }

    private int toTen(String num, int format) {
        int result = 0;
        int pow = 0;

        for(int i = num.length() - 1; i >= 0; i--) {
            int digit = num.charAt(i) - '0';
            result += digit * Math.pow(format, pow);
            pow ++;
        }

        return result;
    }

    private String toN(int num, int format) {
        if(num == 0) return "0";

        return toN(num / format, format) + String.valueOf(num % format);
    }
}
