import java.lang.*;
import java.util.*;

public class sm_완전범죄 {
    public int solution(int[][] info, int n, int m) {
        int[][] score = new int[info.length + 1][m];

        for (int i = 0; i < score.length; i++) {
            Arrays.fill(score[i], 987654321);
        }

        score[0][0] = 0;

        for (int i = 1; i < score.length; i++) {
            int aScore = info[i - 1][0];
            int bScore = info[i - 1][1];

            for (int j = 0; j < m; j++) {
                score[i][j] = Math.min(score[i][j], score[i - 1][j] + aScore);

                if (j + bScore < m) {
                    score[i][j + bScore] = Math.min(score[i][j + bScore], score[i - 1][j]);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            answer = Math.min(answer, score[info.length][i]);
        }

        return answer >= n ? -1 : answer;
    }
}