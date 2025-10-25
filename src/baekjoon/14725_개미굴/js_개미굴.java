import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int n, m, inf = Integer.MAX_VALUE;
    static TreeSet<Node>[] region = new TreeSet[15];

    static StringBuilder sb = new StringBuilder();
    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    static class Node implements Comparable<Node> {
        String parent, me, fullMe;
        public Node(String parent, String me) {
            this.parent = parent;
            this.me = me;
            if (!parent.equals("")) this.fullMe = parent + me;
            else this.fullMe = me;
        }
        public int compareTo(Node o) {
            if (this.parent.equals(o.parent)) return this.me.compareTo(o.me);
            return this.parent.compareTo(o.parent);
        }

        @Override
        public int hashCode() {
            return Objects.hash(parent, me);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (!(other instanceof Node)) return false;
            Node o = (Node) other;
            return this.hashCode() == o.hashCode();
        }
    }

    static void print(int f, Node now) {
        for (int i = 0; i < f; i++) {
            sb.append("--");
        }
        sb.append(now.me);
        sb.append("\n");
    }

    static void track(int f, Node now) {
        print(f, now);
        if (f == 14) return;
        Node child = null;
        while (!region[f + 1].isEmpty()) {
            child = region[f+1].pollFirst();
            if (child.parent.equals(now.fullMe + " ")) {
                track(f+1, child);
            } else {
                region[f+1].add(child);
                break;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < 15; i++) {
            region[i] = new TreeSet<>();
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            StringBuilder parent = new StringBuilder();
            String me;
            for (int j = 0; j < m; j++) {
                me = st.nextToken();
                region[j].add(new Node(parent.toString(), me));
                parent.append(me).append(" ");
            }
        }

        while (!region[0].isEmpty()) {
            Node now = region[0].pollFirst();
            track(0, now);
        }

        System.out.println(sb.toString());
    }
}
