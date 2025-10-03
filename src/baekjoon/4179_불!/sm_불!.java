import java.lang.*;
import java.util.*;
import java.io.*;

public class Main {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int r, c, answer;
    static Character[][] map;
    static Queue<Node> fire, jh;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new Character[r][c];
        fire = new LinkedList<>();
        jh = new LinkedList<>();
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'J') {
                    jh.offer(new Node(i, j, 0));
                } else if (map[i][j] == 'F') {
                    fire.offer(new Node(i, j));
                }
            }
        }

        answer = 0;
        if (!bfs()) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(answer);
        }
    }

    private static boolean bfs() {
        while (!jh.isEmpty()) {

            int fireCnt = fire.size();
            for (int i = 0; i < fireCnt; i++) {
                Node currFire = fire.poll();

                for (int j = 0; j < 4; j++) {
                    int drow = dr[j] + currFire.r;
                    int dcol = dc[j] + currFire.c;

                    if (drow < 0 || dcol < 0 || drow >= r || dcol >= c
                            || map[drow][dcol] == 'F' || map[drow][dcol] == '#') continue;
                    map[drow][dcol] = 'F';
                    fire.offer(new Node(drow, dcol));
                }
            }

            int jhCnt = jh.size();
            for (int i = 0; i < jhCnt; i++) {
                Node currJh = jh.poll();
                for (int j = 0; j < 4; j++) {
                    int drow = dr[j] + currJh.r;
                    int dcol = dc[j] + currJh.c;

                    if (drow < 0 || dcol < 0 || drow >= r || dcol >= c) {
                        answer = currJh.cnt + 1;
                        return true;
                    }

                    if (map[drow][dcol] == 'F' || map[drow][dcol] == '#' || map[drow][dcol] == 'J') continue;
                    map[drow][dcol] = 'J';
                    jh.offer(new Node(drow, dcol, currJh.cnt + 1));
                }
            }
        }
        return false;
    }


    static class Node {
        int r, c, cnt;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Node(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
}