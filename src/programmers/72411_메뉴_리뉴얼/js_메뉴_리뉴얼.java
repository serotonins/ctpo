import java.util.*;

class Solution {
    StringBuilder sb = new StringBuilder();
    Map<Integer, Integer> courseMap = new HashMap<>();
    Map<String, Integer> map = new HashMap<>();
    void pick(String order, int s, int l) {
        int len = order.length();
        if (l == 0) {
            String menu = sb.toString();
            map.put(menu, map.getOrDefault(menu, 0) + 1);
            return;
        }
        for (int i = s; i <= len - l; i++) {
            sb.append(order.charAt(i));
            pick(order, i+1, l-1);
            sb.deleteCharAt(sb.length()-1);
        }
    }
    public String[] solution(String[] orders, int[] course) {
        for (String order: orders) {
            char[] temp = order.toCharArray();
            Arrays.sort(temp);
            StringBuilder ob = new StringBuilder();
            for (char c : temp) {
                ob.append(c);
            }
            order = ob.toString();
            for (int cnt: course) {
                pick(order, 0, cnt);
            }
        }
        
        int[] maxi = new int[course.length];
        List<String>[] lists = new ArrayList[course.length];
        for (int i = 0; i < course.length; i++) {
            lists[i] = new ArrayList<>();
            courseMap.put(course[i], i);
        }
        
        for (String order: map.keySet()) {
            int cnt = map.get(order);
            if (cnt < 2) continue;
            int idx = courseMap.get(order.length());
            if (maxi[idx] <= cnt) {
                if (maxi[idx] < cnt) {
                    maxi[idx] = cnt;
                    lists[idx].clear();
                }
                lists[idx].add(order);
            }
        }
        
        List<String> temp = new ArrayList<>();
        for (List<String> list: lists) {
            for (String str: list) temp.add(str);
        }
        Collections.sort(temp);
        String[] answer = new String[temp.size()];
        int i = 0;
        for (String order: temp) {
            answer[i++] = order;
        }
        return answer;
    }
    
}
