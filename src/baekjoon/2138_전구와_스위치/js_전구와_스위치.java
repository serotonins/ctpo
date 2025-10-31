import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int n, m, inf = Integer.MAX_VALUE;
    static boolean[] originZero, originOne, want;


    static StringBuilder sb = new StringBuilder();
    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(br.readLine());
        String originStr = br.readLine();
        String wantStr = br.readLine();
        originZero = new boolean[n+2];
        originOne = new boolean[n+2];
        want = new boolean[n+2];
        for (int i = 0; i < n; i++) {
            originZero[i+1] = originStr.charAt(i) == '1';
            originOne[i+1] = originStr.charAt(i) == '1';
            want[i+1] = wantStr.charAt(i) == '1';
        }

        int zero = 0;
        int one = 1;
        originOne[1] ^= true;
        originOne[2] ^= true;
        for (int i = 2; i <= n; i++) {
            if (originZero[i-1] != want[i-1]) {
                zero++;
                originZero[i-1] ^= true;
                originZero[i] ^= true;
                originZero[i+1] ^= true;
            }
            if (originOne[i-1] != want[i-1]) {
                one++;
                originOne[i-1] ^= true;
                originOne[i] ^= true;
                originOne[i+1] ^= true;
            }
        }

        if (originZero[n] != want[n]) zero = inf;
        if (originOne[n] != want[n]) one = inf;

        int answer = Math.min(zero, one);
        if (answer == inf) answer = -1;
        System.out.println(answer);
    }
}
