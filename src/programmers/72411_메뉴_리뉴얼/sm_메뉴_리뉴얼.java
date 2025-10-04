import java.lang.*;
import java.util.*;

class Solution {
    static char[] courses;
    static Map<String, Integer> map;
    static int result;

    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();
        for (int cnt : course) {
            courses = new char[cnt];
            map = new HashMap<>();
            result = 0;
            for (String order : orders) {
                if (order.length() < cnt) continue;
                comb(0, order, 0);

            }
            //map을 돌면서 코스요리 제일 많은것 확인
            for (String key : map.keySet()) {
                //최대 개수랑 같다면 정답에 넣기
                if (result != 1 && map.get(key) == result) {
                    answer.add(key);
                }
            }
        }

        Collections.sort(answer);
        return answer.toArray(new String[0]);
    }

    private static void comb(int idx, String order, int cnt) {
        if (idx == courses.length) {
            //코스에 담긴 요리 오름차순
            char[] temp = courses.clone();
            Arrays.sort(temp);

            //map에 담기
            StringBuilder sb = new StringBuilder();
            for (char s : temp) {
                sb.append(s);
            }
            String menu = sb.toString();
            map.put(menu, map.getOrDefault(menu, 0) + 1);
            result = Math.max(result, map.get(menu));
            return;
        }

        for (int i = cnt; i < order.length(); i++) {
            courses[idx] = order.charAt(i);
            comb(idx + 1, order, i + 1);
        }
    }
}