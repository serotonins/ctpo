import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        boolean[] robot = new boolean[n];
        int[] belts = new int[n * 2];
        for (int i = 0; i < n * 2; i++) {
            belts[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        while (true) {
            cnt++;

            int belt = belts[n * 2 - 1];
            for (int i = n * 2 - 1; i > 0; i--) {
                belts[i] = belts[i - 1];
            }
            belts[0] = belt;

            for (int i = n - 1; i > 0; i--) {
                robot[i] = robot[i - 1];
            }
            robot[0] = false;
            robot[n - 1] = false;

            for (int i = n - 1; i > 0; i--) {
                if (robot[i - 1] && !robot[i] && belts[i] > 0) {
                    robot[i - 1] = false;
                    robot[i] = true;
                    belts[i]--;
                    robot[n - 1] = false;
                }
            }

            if (belts[0] > 0) {
                robot[0] = true;
                belts[0]--;
            }

            int tmp = 0;
            for (int i = 0; i < n * 2; i++) {
                if (belts[i] == 0) {
                    tmp++;
                }
            }

            if (tmp >= k) break;
        }
        System.out.println(cnt);
    }
}
