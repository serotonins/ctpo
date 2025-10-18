import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int n, m, inf = Integer.MAX_VALUE;

    static PriorityQueue<Integer> left = new PriorityQueue<>(Comparator.reverseOrder());
    static PriorityQueue<Integer> right = new PriorityQueue<>();

    static StringBuilder sb = new StringBuilder();
    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    static void print() {
        int i = left.poll();
        left.add(i);
        sb.append(i).append("\n");
    }

    static void balance(int now) {
        int l = left.poll();
        int r = right.poll();

        if (now <= r) {
            if (now <= l) left.add(now);
            else {
                left.add(l);
                l = now;
            }
        } else if (now >= l) {
            if (now >= r) right.add(now);
            else {
                right.add(r);
                r = now;
            }
        }

        if (left.size() > right.size() + 1) {
            right.add(l);
            right.add(r);
        } else if (left.size() < right.size()) {
            left.add(l);
            left.add(r);
        } else {
            left.add(l);
            right.add(r);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(br.readLine());

        int one = Integer.parseInt(br.readLine());
        left.add(one);
        sb.append(one).append("\n");
        if (n > 1) {
            int two = Integer.parseInt(br.readLine());
            if (one <= two) {
                right.add(two);
                sb.append(one).append("\n");
            } else {
                left.poll();
                left.add(two);
                right.add(one);
                sb.append(two).append("\n");
            }
        }
        for (int i = 2; i < n; i++) {
            int now = Integer.parseInt(br.readLine());
            balance(now);
            print();
        }

        System.out.println(sb.toString());


    }
}
