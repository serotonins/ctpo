import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int n, m, inf = Integer.MAX_VALUE;
    static int[] parent;

    static StringBuilder sb = new StringBuilder();
    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = read();

        for (int t = 0; t < T; t++) {
            n = read();
            parent = new int[n+1];
            for (int i = 1; i < n + 1; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < n-1; i++) {
                int p = read();
                int c = read();
                parent[c] = p;
            }

            int one = read();
            int two = read();

            Set<Integer> set = new HashSet<>();
            while (true) {
                set.add(one);
                if (one == parent[one]) break;
                one = parent[one];
            }

            while (true) {
                if (set.contains(two)) {
                    sb.append(two).append("\n");
                    break;
                }
                two = parent[two];
            }
        }

        System.out.println(sb.toString());

    }
}
