import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int n, l, r, inf = Integer.MAX_VALUE;
    static int[][] dr = {{0,0,-1,1}, {-1,1,0,0}};
    static int[][] map;
    static int[][] visit;
    static ArrayDeque<WV> que = new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();
    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    static boolean isOut(int y, int x) {return y < 0 || y >= n || x < 0 || x >= n;}

    static class WV {
        int y, x;
        public WV(int y, int x) {
            this.y = y;
            this.x = x;
        }
        public int seg() {return y * n + x;}
        @Override
        public int hashCode() {return y * 1000 + x;}
        @Override
        public boolean equals(Object o) {return this.hashCode() == o.hashCode();}

    }



    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = read();
        l = read();
        r = read();

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = read();
            }
        }

        int time = 0;
        while (true) {
            visit = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(visit[i], inf);
            }
            Map<Integer, Integer> resultMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visit[i][j] != inf) continue;
                    int idx = i * n + j;
                    int sum = map[i][j];
                    int cnt = 1;
                    ArrayDeque<WV> que = new ArrayDeque<>();
                    que.add(new WV(i,j));
                    visit[i][j] = idx;
                    while (!que.isEmpty()) {
                        WV now = que.poll();
                        for (int d = 0; d < 4; d++) {
                            int y = now.y + dr[0][d];
                            int x = now.x + dr[1][d];
                            if (isOut(y,x) || visit[y][x] != inf) continue;
                            int diff = Math.abs(map[now.y][now.x] - map[y][x]);
                            if (diff < l || diff > r) continue;
                            que.add(new WV(y,x));
                            visit[y][x] = idx;
                            sum += map[y][x];
                            cnt++;
                        }
                    }
                    resultMap.put(idx, sum/cnt);
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visit[i][j] != inf) {
                        map[i][j] = resultMap.get(visit[i][j]);
                    }
                }
            }
            if (resultMap.size() == n*n) break;
            time++;
        }
        System.out.println(time);
    }
}
