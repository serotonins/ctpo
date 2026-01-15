import java.util.*;

class Solution {
    int inf = 100000;
    State[] dp;
    static int target;
    
    class State {
        int cnt, sb;
        public State(int cnt, int sb) {
            this.cnt = cnt;
            this.sb = sb;
        }
        public State() {
            this.cnt = inf;
            this.sb = -1;
        }
    }
    
    void setting() {
        dp[0] = new State(0, 0);
        for (int i = 1; i <= target; i++) {
            dp[i] = new State();
        }
    }
    
    void countDown(int now, int from) {
        if (from < 0 || dp[from].cnt == inf) return;
        int cnt = dp[from].cnt + 1;
        int sb = dp[from].sb;
        if (now - from <= 20 || now - from == 50) sb++;
        boolean lowCnt = dp[now].cnt > cnt;
        boolean highSb = dp[now].cnt == cnt && dp[now].sb < sb;
        if (lowCnt || highSb) {
            dp[now].cnt = cnt;
            dp[now].sb = sb;
        }
    }
    
    int startNum(int num, int mode) {
        if (mode == 1) {
            return Math.min(num, 20);
        } else if (mode == 2) {
            if (num < 40) return num - (num % 2);
            else return 40;
        } else {
            if (num < 60) return num - (num % 3);
            else return 60;
        }
    }
    
    public int[] solution(int target) {
        int[] answer = new int[2];
        Solution.target = target;
        dp = new State[100001];
        setting();
        for (int i = 1; i <= target; i++) {
            countDown(i, i-50);
            for (int j = startNum(i, 1); j >= 1; j--) {
                countDown(i, i-j);
            }
            for (int j = startNum(i, 2); j > 20; j-=2) {
                countDown(i, i-j);
            }
            for (int j = startNum(i, 3); j > 20; j-=3) {
                countDown(i, i-j);
            }
        }
        answer[0] = dp[target].cnt;
        answer[1] = dp[target].sb;
        return answer;
    }
}
