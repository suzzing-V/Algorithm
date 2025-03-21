import java.util.*;

class Solution {

    private static Map<String, List<String>> user_cnt = new HashMap<>(); // 해당 제재 아이디에 해당하는 user id의 개수
    private static String[] banned_id;
    private static Set<String> result = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id1) {
        banned_id = banned_id1;

        // 제재 아이디 별 매칭되는 유저 아이디 구하기
        for(int i = 0; i < banned_id.length; i++) {
            String id = banned_id[i];
            if(user_cnt.get(id) != null) continue; // 이미 매칭되는 유저아이디를 구한 제재 아이디라면 넘어간다.
            for(int j = 0; j < user_id.length; j++) {
                if(isMatched(user_id[j], id)) {
                    if(user_cnt.get(id) == null) user_cnt.put(id, new ArrayList<>());
                    user_cnt.get(id).add(user_id[j]);
                }
            }
        }

        // 제재 아이디마다 충족되는 유저 아이디 다 매칭시켜보기
        dfs(0, new ArrayList<>());
        return result.size();
    }

    private boolean isMatched(String origin, String masked) {
        if(origin.length() != masked.length()) return false;

        for(int i = 0; i < masked.length(); i++) {
            if(masked.charAt(i) == '*') continue;
            if(masked.charAt(i) != origin.charAt(i)) return false;
        }
        return true;
    }

    private void dfs(int banned_idx, List<String> users) {
        // System.out.println(banned_idx);
        if(banned_idx == banned_id.length) {
            Collections.sort(users); // 같은 목록은 같은 취급하기 위해 정렬해서 set에 저장
            result.add(users.toString());
            // System.out.println("완성!" + users);
            return;
        }

        String ban_id = banned_id[banned_idx];
        for(String id : user_cnt.get(ban_id)) {
            if(users.contains(id)) continue;
            users.add(id);
            dfs(banned_idx + 1, users);
            users.remove(id);
        }
    }
}
