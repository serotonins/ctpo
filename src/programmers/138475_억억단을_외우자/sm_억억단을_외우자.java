import java.lang.*;
import java.util.*;

class Solution {
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];

        int[][] info = new int[2][e+1];
        info[0][e] = e;
        info[1][e] = getPrimeCnt(e);
        for(int i=e-1 ; i>0 ;i--){
            int primeCnt = getPrimeCnt(i);

            if(primeCnt >= info[1][i+1]){
                info[0][i] = i;
                info[1][i] = primeCnt;
            }else{
                info[0][i] = info[0][i+1];
                info[1][i] = info[1][i+1];
            }
        }

        for(int i=0; i<starts.length; i++){
            answer[i] = info[0][starts[i]];
        }
        return answer;
    }

    private static int getPrimeCnt(int num){
        Map<Integer, Integer> primes = new HashMap<>();
        for(int i=2 ;i<=Math.sqrt(num) ; i++){
            while(num % i == 0){
                primes.put(i, primes.getOrDefault(i, 0)+1);
                num/=i;
            }
        }

        if(num != 1) primes.put(num, primes.getOrDefault(num, 0)+1);

        int primeCnt = 1;
        for(int value : primes.values()){
            primeCnt *= (value+1);
        }

        return primeCnt;
    }
}