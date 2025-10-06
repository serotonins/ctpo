import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr;

    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    static int binsearch(int target) {
        int s = 0;
        int e = n - 1;
        int p = (s + e) / 2;
        while (s <= e && e >= 0 && s < n) {
            p = (s + e) / 2;
            if (arr[p] >= target) e = p - 1;
            else if (arr[p] < target) s = p + 1;
        }
        return s;
    }

    public static void main(String[] args) throws IOException {
        n = read();
        int sum = 0;
        arr = new int[n];
        int[] prefix = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = read();
        }
        Arrays.sort(arr);
        prefix[0] = arr[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i-1] + arr[i];
        }
        sum = prefix[n-1];
        int maxi = arr[n - 1];
        int m = read();
        int answer = -1;
        if (m >= sum) answer = maxi;
        else {
            int s = 1;
            int e = arr[n - 1];
            int p = (s + e) / 2;
            while (s <= e && e > 0 && s <= arr[n-1]) {
                p = (s + e) / 2;
                int idx = binsearch(p);
                int use = p * (n - idx);
                if (idx > 0) use += prefix[idx - 1];
                if (use <= m) s = p + 1;
                else e = p - 1;
            }
            answer = e;
        }
        System.out.println(answer);
    }
}