import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static char[][] tree = new char[26][2];
    static boolean[] visit = new boolean[26];
    static StringBuilder sb = new StringBuilder();
    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    static void back(char now, int mode) {
        char l = tree[now-'A'][0];
        char r = tree[now-'A'][1];
        if (mode == 1) {
            sb.append(now);
            if (l != '.') back(l, mode);
            if (r != '.') back(r, mode);
        } else if (mode == 2) {
            if (l != '.') back(l, mode);
            sb.append(now);
            if (r != '.') back(r, mode);
        } else {
            if (l != '.') back(l, mode);
            if (r != '.') back(r, mode);
            sb.append(now);
        }
    }
    public static void main(String[] args) throws IOException {
        n = read();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; // = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            int idx = str.charAt(0) - 'A';
            tree[idx][0] = str.charAt(2);
            tree[idx][1] = str.charAt(4);
        }

        back('A', 1);
        sb.append("\n");
        back('A', 2);
        sb.append("\n");
        back('A', 0);

        System.out.println(sb.toString());
    }
}