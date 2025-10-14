import java.util.*;

class Solution {
    public long str2num(String str) {
        long num = 0;
        for (char c : str.toCharArray()) num = num * 26 + (c - 'a' + 1);
        return num;
    }
    public String num2str(long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            num--;
            sb.append((char) ('a' + (num % 26)));
            num /= 26;
        }
        return sb.reverse().toString();
    }
    public String solution(long n, String[] bans) {
        int minus = 0;
        long[] banNums = Arrays.stream(bans).mapToLong(this::str2num).sorted().toArray();
        for (long b : banNums) {
            if (b <= n) n++;
        }
        return num2str(n);
    }
}
