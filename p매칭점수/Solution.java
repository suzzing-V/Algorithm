import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    private Map<String, Info> infos = new HashMap<>();

    private class Info {
        private int index;
        private int base;
        private List<String> linked;
        private int ex;

        private Info(int index, int base, List<String> linked, int ex) {
            this.index = index;
            this.base = base;
            this.linked = linked;
            this.ex = ex;
        }
    }

    public int solution(String word, String[] pages) {
        // word = word.toLowerCase();
        Matcher mt = null;
        for(int i = 0; i < pages.length; i++) {
            String page = pages[i];
            mt = Pattern.compile("<meta property=\"og:url\" content=\"https://(\\S*)\"/>").matcher(page);
            String url = null;
            if(mt.find()) {
                url = mt.group(1).trim();
                List<String> linked = new ArrayList<>();
                infos.put(url, new Info(0, 0, linked, 0));
            }
            // System.out.println(page);
            // System.out.println(url);
        }

        for(int i = 0; i < pages.length; i++) {
            String page = pages[i];
            String my_url = null;
            int word_cnt = 0;
            int ex_cnt = 0;
            // 내 url 구하기
            mt = Pattern.compile("<meta property=\"og:url\" content=\"https://(\\S*)\"/>").matcher(page);
            if(mt.find()) {
                my_url = mt.group(1).trim();
            }

            // 외부 참조 url
            mt = Pattern.compile("<a href=\"https://(\\S*)\"").matcher(page);
            while(mt.find()) {
                String ex_url = mt.group(1).trim();
                // System.out.println(ex_url);
                Info ex_info = infos.get(ex_url);
                if(ex_info != null) {
                    ex_info.linked.add(my_url);
                }
                ex_cnt ++;
            }

            // 단어 세기
            mt = Pattern.compile("(?i)(?<=[^a-zA-Z])(" + word + ")[^a-zA-Z]").matcher(page);
            while(mt.find()) {
                word_cnt ++;
            }

            Info info = infos.get(my_url);
            info.index = i;
            info.base = word_cnt;
            info.ex = ex_cnt;
        }

        double max = -1;
        int idx = 0;
        for(String url : infos.keySet()) {
            double link_score = 0;
            Info info = infos.get(url);
            for(String linked_url : info.linked) {
                Info linked_info = infos.get(linked_url);
                link_score += (double)linked_info.base / linked_info.ex;
            }

            double matching_score = info.base + link_score;
            // System.out.println(url + " " + info.index + " " + info.base + " " + link_score);
            if(max < matching_score) {
                max = matching_score;
                idx = info.index;
            }
        }

        return idx;
    }
}
