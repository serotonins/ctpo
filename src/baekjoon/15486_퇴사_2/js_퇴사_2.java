import java.io.IOException;

public class Main {
    static int n;
    
    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    public static void main(String[] args) throws IOException {
        n = read();

        int[] dp = new int[n+1];
        for (int i = 1; i <= n; i++) {
            int time = read();
            int pay = read();
            
            dp[i] = Math.max(dp[i], dp[i-1]);
            if (i + time <= n + 1) {
                dp[i + time - 1] = Math.max(dp[i + time - 1], dp[i - 1] + pay);
            }
        }

        System.out.println(dp[n]);
    }
}