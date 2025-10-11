import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int n, m, inf = Integer.MAX_VALUE;
    static int[] parent;
    static PriorityQueue<Edge> que = new PriorityQueue<>();

    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    static class Edge implements Comparable<Edge> {
        int dep, arr, cost;
        public Edge(int dep, int arr, int cost) {
            this.dep = dep;
            this.arr = arr;
            this.cost = cost;
        }
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x < y) parent[y] = x;
        else if (x > y) parent[x] = y;
    }

    static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = read();
        m = read();

        parent = new int[n+1];
        for (int i = 0; i < n + 1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            que.add(new Edge(read(), read(), read()));
        }

        int answer = 0;
        while (!que.isEmpty()) {
            Edge now = que.poll();
            if (find(now.dep) != find(now.arr)) {
                answer += now.cost;
                union(now.dep, now.arr);
            }
        }

        System.out.println(answer);
    }
}
