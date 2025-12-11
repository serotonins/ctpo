import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int n;
    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    static class Lecture implements Comparable<Lecture> {
        int s, e;
        public Lecture(int s, int e) {
            this.s = s;
            this.e = e;
        }
        public int compareTo(Lecture o) {
            if (this.s == o.s) return this.e - o.e;
            return this.s - o.s;
        }
    }

    public static void main(String[] args) throws IOException {

        n = read();
        PriorityQueue<Lecture> lectures = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            lectures.add(new Lecture(read(), read()));
        }

        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        rooms.add(0);

        while(!lectures.isEmpty()) {
            Lecture lec = lectures.poll();
            if (rooms.peek() <= lec.s) rooms.poll();
            rooms.add(lec.e);
        }

        System.out.println(rooms.size());
    }
}
