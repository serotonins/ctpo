import java.lang.*;
import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        PriorityQueue<String> que = new PriorityQueue<>(
                (o1, o2) -> o1.length() == o2.length() ? o1.compareTo(o2) : o1.length() - o2.length());

        for (String ban : bans) {
            que.add(ban);
        }

        while (!que.isEmpty()) {
            String curr = que.peek();
            String str = makeStr(n);

            if (curr.length() < str.length() || (curr.length() == str.length() && curr.compareTo(str) <= 0)) {
                n++;
                que.poll();
                continue;
            }

            break;

        }

        return makeStr(n);
    }

    private static String makeStr(long n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            long remind = n % 26;
            n /= 26;
            if (remind == 0) {
                n--;
                sb.append('z');
            } else {
                sb.append((char) ('a' + remind - 1));
            }
        }
        return sb.reverse().toString();
    }

}