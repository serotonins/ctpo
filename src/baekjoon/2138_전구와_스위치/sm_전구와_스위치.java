import java.io.*;

public class Main {
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String now = br.readLine();
        String goal = br.readLine();

        int[] goalBulb = new int[n];
        int[] nowBulb = new int[n];
        int[] tmpBulb = new int[n];

        for (int i = 0; i < n; i++) {
            goalBulb[i] = now.charAt(i) - '0';
            nowBulb[i] = now.charAt(i) - '0';
            tmpBulb[i] = goal.charAt(i) - '0';
        }
        goalBulb[0] = 1 - goalBulb[0];
        goalBulb[1] = 1 - goalBulb[1];

        int ans1 = 1;
        int ans2 = 0;
        for (int i = 1; i < n; i++) {
            if (goalBulb[i - 1] != tmpBulb[i - 1]) {
                goalBulb[i - 1] = 1 - goalBulb[i - 1];
                goalBulb[i] = 1 - goalBulb[i];
                ans1++;
                if (i != n - 1)
                    goalBulb[i + 1] = 1 - goalBulb[i + 1];

            }
            if (nowBulb[i - 1] != tmpBulb[i - 1]) {
                nowBulb[i - 1] = 1 - nowBulb[i - 1];
                nowBulb[i] = 1 - nowBulb[i];
                ans2++;
                if (i != n - 1)
                    nowBulb[i + 1] = 1 - nowBulb[i + 1];
            }
        }

        if (goalBulb[n - 1] != tmpBulb[n - 1]) ans1 = INF;
        if (nowBulb[n - 1] != tmpBulb[n - 1]) ans2 = INF;


        if (ans1 == INF && ans2 == INF)
            System.out.println(-1);
        else
            System.out.println(Math.min(ans1, ans2));
    }
}
