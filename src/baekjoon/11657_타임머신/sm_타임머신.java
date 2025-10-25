import java.lang.*;
import java.util.*;
import java.io.*;

public class Main {
    static long[] dist;
    static int n;
    static List<BusInfo> bus;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken()); // 도시의 개수
        int m = Integer.parseInt(st.nextToken()); // 버스 노선 개수

        bus = new ArrayList<>();
        dist = new long[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            bus.add(new BusInfo(start, end, time));
        }

        Arrays.fill(dist, Long.MAX_VALUE);
        if (checkTime()) {
            for (int i = 2; i <= n; i++) {
                sb.append(dist[i] == Long.MAX_VALUE ? -1 : dist[i]).append("\n");
            }
        } else {
            sb.append(-1);
        }

        System.out.println(sb);

    }

    private static boolean checkTime() {
        dist[1] = 0;

        boolean flag;
        for (int i = 0; i <= n; i++) {
            flag = false;
            for (BusInfo info : bus) {
                if (dist[info.start] == Long.MAX_VALUE) continue;

                //거리 갱신
                if (dist[info.end] > dist[info.start] + info.time) {
                    dist[info.end] = dist[info.start] + info.time;
                    flag = true;
                }
            }

            //마지막까지 와서 갱신이 된다는 것은 음수 사이클 존재
            if (i == n && flag) {
                return false;
            }
        }

        return true;
    }

    static class BusInfo {
        int start, end, time;

        public BusInfo(int start, int end, int time) {
            this.start = start;
            this.end = end;
            this.time = time;
        }
    }
}