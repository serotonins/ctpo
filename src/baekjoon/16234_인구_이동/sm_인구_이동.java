import java.lang.*;
import java.util.*;
import java.io.*;

public class Main {
    static int n, l, r;
    static int[][] countries;
    static boolean[][] open;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        countries = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                countries[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        while (true) {
            open = new boolean[n][n];
            flag = false;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!open[i][j]) {
                        bfs(i, j);
                    }
                }
            }

            if (!flag) {
                break;
            }

            day++;
        }

        System.out.println(day);
    }

    private static void bfs(int row, int col) {
        Queue<Node> que = new LinkedList<>();
        List<Node> list = new ArrayList<>();
        open[row][col] = true;

        que.offer(new Node(row, col));
        list.add(new Node(row, col));
        int sum = countries[row][col];
        int countryCnt = 1;
        while (!que.isEmpty()) {
            Node currCountry = que.poll();

            for (int i = 0; i < 4; i++) {
                int drow = currCountry.row + dr[i];
                int dcol = currCountry.col + dc[i];

                if (drow < 0 || drow >= n || dcol < 0 || dcol >= n || open[drow][dcol]) continue;

                int diff = Math.abs(countries[currCountry.row][currCountry.col] - countries[drow][dcol]);
                if (diff <= r && diff >= l) {
                    flag = true;
                    open[drow][dcol] = true;
                    list.add(new Node(drow, dcol));
                    que.offer(new Node(drow, dcol));
                    sum += countries[drow][dcol];
                    countryCnt++;
                }
            }
        }

        int peopleCnt = sum / countryCnt;
        for (Node node : list) {
            countries[node.row][node.col] = peopleCnt;
        }
    }

    static class Node {
        int row, col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}