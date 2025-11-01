import java.util.*;

class Solution {
    class Algo {
        int a, c, t;
        public Algo(int a, int c, int t) {
            this.a = a;
            this.c = c;
            this.t = t;
        }
        public String toString() {
            return "[" + a + " " + c + " " + t + "]";
        }
    }
    
    boolean cando(Algo now, int a, int c) {
        return now.a >= a && now.c >= c;
    }
    
    boolean preVisit(int[][] visit, int a, int c, int t) {
        a = Math.min(a, 150);
        c = Math.min(c, 150);
        if (visit[a][c] <= t) return true;
        visit[a][c] = t;
        return false;
    }
    
    public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;
        Algo goal = new Algo(0,0,0);
        int[][] visit = new int[151][151];
        for (int i = 0; i < 151; i++) {
            Arrays.fill(visit[i], Integer.MAX_VALUE);
        }
        
        for (int[] p : problems) {
            goal.a = Math.max(p[0], goal.a);
            goal.c = Math.max(p[1], goal.c);
        }
        
        PriorityQueue<Algo> que = new PriorityQueue<>((a, b) -> {
            if (a.t == b.t) return b.a+b.c - (a.a+a.c);
            return a.t - b.t;
        });
        que.add(new Algo(alp, cop, 0));
        while (!que.isEmpty()) {
            Algo now = que.poll();
            if (cando(now, goal.a, goal.c)) {
                answer = now.t;
                break;
            }
            if (now.a < goal.a && !preVisit(visit, now.a+1, now.c, now.t+1)) {
                que.add(new Algo(now.a+1, now.c, now.t+1));
            }
            if (now.c < goal.c && !preVisit(visit, now.a, now.c+1, now.t+1)) {
                que.add(new Algo(now.a, now.c+1, now.t+1));
            }
            for (int[] p : problems) {
                if (!cando(now, p[0], p[1])) continue;
                if (preVisit(visit, now.a+p[2], now.c+p[3], now.t+p[4])) continue;
                que.add(new Algo(now.a+p[2], now.c+p[3], now.t+p[4]));
            }
        }
        
        return answer;
    }
}
