import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> que1 = new PriorityQueue<>(); // 작은 수부터 뽑는 큐
        PriorityQueue<Integer> que2 = new PriorityQueue<>((o1, o2) -> o2 - o1); // 큰수부터 뽑는 큐

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            //que1과 que2의 사이즈가 같다면 que2에 넣기 -> que2에서 뽑은 값을 정답으로 적을 것이기 때문에
            if (que1.size() == que2.size()) que2.offer(num);
            else que1.offer(num);

            //만약 2개가 비어있지 않고 값이 있다면 재정렬 시작
            if (!que1.isEmpty() && !que2.isEmpty()) {

                //만약 que1의 값이 que2의 값 보다 작다면 que1의 값을 que2의 값에 옮겨주기
                if (que1.peek() < que2.peek()) {
                    int temp = que1.poll();
                    que1.offer(que2.poll());
                    que2.offer(temp);
                }
            }

            sb.append(que2.peek()).append("\n");

        }

        System.out.println(sb);
    }
}