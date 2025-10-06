import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> que = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            que.add(Integer.parseInt(br.readLine()));
        }

        int answer = 0;
        while (que.size() > 1) {
            int one = que.poll();
            int two = que.poll();
            int sum = one + two;
            que.add(sum);
            answer += sum;
        }
        System.out.println(answer);
    }
}