import java.lang.*;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Counsel[] counsels = new Counsel[n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            counsels[i] = new Counsel(time, price);
        }

        int[] dp = new int[n + 2];
        for (int i = n; i > 0; i--) {
            if (i + counsels[i].time > n + 1) {
                dp[i] = dp[i + 1];
            } else {
                dp[i] = Math.max(dp[i + 1], counsels[i].price + dp[i + counsels[i].time]);
            }
        }

        System.out.println(dp[1]);
    }

    static class Counsel {
        int time, price;

        public Counsel(int time, int price) {
            this.time = time;
            this.price = price;
        }
    }
}