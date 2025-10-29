import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int parent[];
    static int N;
    static boolean find;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int test_case = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= test_case; tc++) {

            N = Integer.parseInt(br.readLine());
            parent = new int[N + 1];

            // 부모 - 자식 노드간의 연결
            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                parent[end] = start;
            }

            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            // 각 숫자의 조상을 스택에 넣어 조상까지의 길 만들기
            Stack<Integer> num1P = new Stack<>();
            int i = num1;
            while (true) {
                if (i == 0) {
                    break;
                }
                num1P.push(i);
                i = parent[i];
            }

            Stack<Integer> num2P = new Stack<>();
            i = num2;
            while (true) {
                if (i == 0) {
                    break;
                }
                num2P.push(i);
                i = parent[i];
            }

            // 스택을 뽑으며 숫자가 달라지는 순간의 전 숫자가 답이 됨
            int one = 0;
            int two = 0;
            int result = 0;
            while (true) {
                if (num1P.isEmpty() || num2P.isEmpty()) {
                    break;
                }
                one = num1P.pop();
                two = num2P.pop();
                if (one != two) {
                    break;
                }
                result = one;
            }
            sb.append(result + "\n");
        }
        System.out.println(sb);
    }
}

