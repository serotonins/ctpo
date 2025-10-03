import java.lang.*;
import java.util.*;
import java.io.*;

public class sm_플로이드 {
    static final int MAX_NUM = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        //초기 세팅
        int[][] costs = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) costs[i][j] = 0;
                else costs[i][j] = MAX_NUM;
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            costs[a][b] = Math.min(costs[a][b], c);
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    costs[i][j] = Math.min(costs[i][j], costs[i][k] + costs[k][j]);
                }
            }
        }

        for (int[] cost : costs) {
            for (int result : cost) {
                sb.append(result == MAX_NUM ? 0 : result).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}