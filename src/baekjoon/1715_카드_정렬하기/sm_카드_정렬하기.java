import java.util.*;
import java.lang.*;
import java.io.*;

public class sm_카드_정렬하기 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        if (pq.size() == 1) {
            System.out.println(0);
            return;
        }

        int sum = 0;
        while (!pq.isEmpty()) {
            int first = pq.poll();

            if(pq.size() == 1){
                sum += first+ pq.poll();
                break;
            }

            if (pq.size() == 0) {
                sum += first;
                break;
            }

            int second = pq.poll();
            sum += first + second;
            pq.offer(first + second);
        }

        System.out.println(sum);

    }
}