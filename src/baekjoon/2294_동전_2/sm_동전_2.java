import java.util.*;
import java.lang.*;
import java.io.*;

public class sm_동전_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dp = new int[k + 1];
        Arrays.fill(dp, 987654321);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            int value = Integer.parseInt(br.readLine());

            for (int j = value; j <= k; j++) {
                dp[j] = Math.min(dp[j - value] + 1, dp[j]);
            }
        }

        System.out.println(dp[k] == 987654321 ? -1 : dp[k]);
    }
}