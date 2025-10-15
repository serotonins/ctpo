import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        int len = A.length;
        
        int a = 0;
        int b = 0;
        int answer = 0;
        while (a < len && b < len) {
            if (A[a] < B[b]) {
                answer++;
                a++;
            } 
            b++;
        }
        
        return answer;
    }
}
