import java.io.IOException;
import java.util.*;

public class Main {
    static int n, m;
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
        m = read();
        int inf = 1000_0001;

        int[][] floyd = new int[n+1][n+1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(floyd[i], inf);
            floyd[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            int s = read();
            int e = read();
            floyd[s][e] = Math.min(floyd[s][e], read());
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                for (int k = 1; k < n + 1; k++) {
                    floyd[j][k] = Math.min(floyd[j][k], floyd[j][i] + floyd[i][k]);
                }
            }
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                sb.append(floyd[i][j] < inf ? floyd[i][j] : 0).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}