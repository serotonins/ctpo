import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int n, m;//, inf = Integer.MAX_VALUE;
    static long inf = Long.MAX_VALUE;
    static long[] bf;
    static List<Node>[] map;

    static StringBuilder sb = new StringBuilder();
    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    static class Node {
        int s, e, cost;
        public Node(int s, int e, int cost) {
            this.s = s;
            this.e = e;
            this.cost = cost;
        }
    }

    static boolean bellmanFord() {
        boolean change = false;

        for (int i = 1; i < n; i++) {
            change = false;
            for (int j = 0; j < n+1; j++) {
                for (Node now: map[j]) {
                    if (bf[j] == inf) break;
                    if (bf[now.e] > bf[j] + now.cost) {
                        bf[now.e] = bf[j] + now.cost;
                        change = true;
                    }
                }
            }
            if (!change) break;
        }

        if (change) {
            for (int j = 0; j < n+1; j++) {
                for (Node now: map[j]) {
                    if (bf[j] == inf) break;
                    if (bf[now.e] > bf[j] + now.cost) return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        bf = new long[n+1];
        map = new List[n+1];
        for (int i = 0; i < n+1; i++) {
            map[i] = new ArrayList();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            map[s].add(new Node(s, e, cost));
        }

        Arrays.fill(bf, inf);
        bf[1] = 0;

        if (bellmanFord()) sb.append("-1\n");
        else {
            for (int i = 2; i < n+1; i++) {
                if (bf[i] == inf) sb.append("-1\n");
                else sb.append(bf[i]).append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}
