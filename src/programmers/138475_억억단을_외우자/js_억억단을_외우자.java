import java.util.*;

class Solution {
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];
        int[] count = new int[e+1];
        for (int i = 1; i <= (int) Math.sqrt(e); i++) {
            for (int j = i; j <= e; j+=i) {
                count[j]++;
                if (j/i > i && j/i > (int) Math.sqrt(e)) count[j]++;
            }
        }
        
        int[] maxi = new int[e+1];
        int m = -1;
        for (int i = e; i > 0; i--) {
            if (count[i] >= m) maxi[i] = i;
            else maxi[i] = maxi[i+1];
            if (count[i] > m) m = count[i];
        }
        
        for (int i = 0; i < starts.length; i++) {
            answer[i] = maxi[starts[i]];
        }
        
        return answer;
    }
}
