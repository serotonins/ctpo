import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int n, m, inf = Integer.MAX_VALUE;
    static int[] belt;

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

        n = read();
        m = read();

        belt = new int[2*n];
        boolean[] robots = new boolean[2*n];
        int broken = 0;
        for (int i = 0; i < 2*n; i++) {
            belt[i] = read();
            if (belt[i] == 0) broken++;
        }

        int time = 0;

        while (broken < m) {
            time++;
            int up = ((2*n) - (time % (2*n))) % (2*n);
            int down = (up + n-1) % (2*n);
            if (robots[down]) robots[down] = false;
            int semiDown = (2*n + down-1) % (2*n);
            if (robots[semiDown] && belt[down] > 0) {
                robots[semiDown] = false;
                belt[down]--;
                if (belt[down] == 0) broken++;
            }
            for (int i = 0; i < n-2; i++) {
                int now = (2*n + semiDown - 1 - i) % (2*n);
                if (!robots[now]) continue;
                int next = (now+1)%(2*n);
                if (belt[next] > 0 && !robots[next]) {
                    robots[next] = true;
                    robots[now] = false;
                    belt[next]--;
                    if (belt[next] == 0) broken++;
                }
            }
            if (belt[up] > 0 && !robots[up]) {
                robots[up] = true;
                belt[up]--;
                if (belt[up] == 0) broken++;
            }
        }

        System.out.println(time);
    }
}
