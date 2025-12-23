import java.util.*;
import java.io.*;

public class Main {
    static int n, m, k, answer, len, inf = Integer.MAX_VALUE;
    static StringBuilder sb = new StringBuilder();
    static int[] arr;
    static boolean[][] dp;

    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    static void setting() {
        for (int i = 1; i <= n; i++) {
            dp[i][i] = true;
        }
        for (int i = 1; i < n; i++) {
            if (arr[i] == arr[i+1]) dp[i][i+1] = true;
        }
        for (int i = 2; i < n; i++) {
            for (int j = 1; j <= n-i; j++) {
                if (dp[j+1][j+i-1] && arr[j] == arr[j+i]) dp[j][j+i] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        n = read();
        arr = new int[n+1];
        dp = new boolean[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            arr[i] = read();
        }
        setting();

        m = read();
        for (int t = 0; t < m; t++) {
            int s = read();
            int e = read();
            sb.append(dp[s][e] ? "1\n" : "0\n");
        }

        System.out.print(sb.toString());
    }
}


