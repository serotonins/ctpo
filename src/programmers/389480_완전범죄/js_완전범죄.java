import java.util.*;

class Solution {
    int inf = Integer.MAX_VALUE;
    int answer = Integer.MAX_VALUE;
    
    class Team implements Comparable<Team> {
        int idx, a, b;
        public Team(int idx, int a, int b) {
            this.idx = idx;
            this.a = a;
            this.b = b;
        }
        @Override
        public int compareTo(Team o) {
            if (this.a == o.a) return this.idx - o.idx;
            return this.a - o.a;
        }
        public String toString() {
            return "[" + idx + " " + a + " " + b + "]";
        }
    }
    
    public int solution(int[][] info, int n, int m) {
        PriorityQueue<Team> que = new PriorityQueue<>();
        que.add(new Team(0, 0, 0));
        int[][] visit = new int[n][m];
        for (int[] arr : visit) { Arrays.fill(arr, -1); }
        
        while (!que.isEmpty()) {
            Team now = que.poll();
            if (now.idx == info.length) {
                answer = Math.min(answer, now.a);
                continue;
            }
            
            int newA = now.a + info[now.idx][0];
            int newB = now.b + info[now.idx][1];
            
            if (newB < m && visit[now.a][newB] < now.idx) {
                que.add(new Team(now.idx+1, now.a, newB));
                visit[now.a][newB] = now.idx;
            }
            if (newA < n && newA < answer && visit[newA][now.b] < now.idx) {
                que.add(new Team(now.idx+1, newA, now.b));  
                visit[newA][now.b] = now.idx;
            }
        }
        
        return answer == inf ? -1 : answer;
    }
}