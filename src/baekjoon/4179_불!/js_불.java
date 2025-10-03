import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] dr = {{0,0,-1,1}, {-1,1,0,0}};
    static char[][] map;
    static boolean[][] visit;
    static PriorityQueue<Man> que = new PriorityQueue<>();
    static ArrayDeque<Fire> fires = new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();
    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    static boolean isOut(int y, int x) {return y < 0 || y >= n || x < 0 || x >= m;}

    static abstract class WV {
        int y, x;
        public WV(int y, int x) {
            this.y = y;
            this.x = x;
        }

    }
    static class Man extends WV implements Comparable<Man> {
        int c;
        public Man(int y, int x, int c) {
            super(y, x);
            this.c = c;
        }
        @Override
        public int compareTo(Man o) {
            return this.c - o.c;
        }
    }

    static class Fire extends WV {
        public Fire(int y, int x) {
            super(y, x);
        }
        public void burn() {
            for (int i = 0; i < 4; i++) {
                int w = y + dr[0][i];
                int v = x + dr[1][i];
                if (isOut(w,v) || map[w][v] == '#' || map[w][v] == 'F') continue;
                map[w][v] = 'F';
                fires.add(new Fire(w,v));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = read();
        m = read();

        map = new char[n][m];
        visit = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'J') {
                    que.add(new Man(i, j, 0));
                    visit[i][j] = true;
                    map[i][j] = '.';
                } else if (map[i][j] == 'F') {
                    fires.add(new Fire(i,j));
                }
            }
        }

        int time = 0;
        int answer = Integer.MAX_VALUE;
        while (!que.isEmpty()) {
            Man now = que.poll();
            if (isOut(now.y, now.x)) {
                answer = now.c;
                break;
            }
            if (map[now.y][now.x] == 'F') continue;

            if (time == now.c) {
                int size = fires.size();
                for (int i = 0; i < size; i++) {
                    fires.pop().burn();
                }
                time++;
            }

            for (int i = 0; i < 4; i++) {
                int y = now.y + dr[0][i];
                int x = now.x + dr[1][i];
                if (isOut(y, x)) {
                    que.add(new Man(y,x,now.c+1));
                    break;
                }
                if (visit[y][x] || map[y][x] == '#' || map[y][x] == 'F') continue;
                visit[y][x] = true;
                que.add(new Man(y,x,now.c+1));
            }
        }

        System.out.println(answer == Integer.MAX_VALUE ? "IMPOSSIBLE" : answer);
    }
}
