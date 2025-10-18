import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, k, inf = 10001;
    static int[] dp;
    static List<Integer> coins = new ArrayList<>();
    static Set<Integer> set = new TreeSet<>();
    static StringBuilder sb = new StringBuilder();
    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    public static void main(String[] args) throws IOException {
        n = read();
        k = read();

        dp = new int[k+1];
        Arrays.fill(dp, inf);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            int coin = read();
            if (coin > k) continue;
            set.add(coin);
            dp[coin] = 1;
        }
        coins.addAll(set);

        for (int i = 0; i < k + 1; i++) {
            for (int c : coins) {
                if (i + c <= k && dp[i] + 1 < dp[i+c]) dp[i+c] = dp[i] + 1;
            }
        }

        System.out.println(dp[k] == inf ? -1 : dp[k]);
    }
}