import java.lang.*;
import java.util.*;

class Solution {
    public int[] solution(int n, int s) {

        if(s/n<1){
            int[] answer = new int[1];
            answer[0] = -1;
            return answer;
        }

        int[] answer = new int[n];
        int cnt = n;
        for(int i=0; i<cnt; i++){
            answer[i]=s/n;

            s-=answer[i];
            n--;
        }

        return answer;
    }
}