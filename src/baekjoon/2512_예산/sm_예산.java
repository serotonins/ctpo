import java.util.*;
import java.lang.*;
import java.io.*;

public class sm_예산 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        int maxMoney = 0;
        int totalMoney = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            maxMoney = Math.max(maxMoney, arr[i]);
            totalMoney += arr[i];
        }

        int limitMoney = Integer.parseInt(br.readLine());

        if (totalMoney <= limitMoney) {
            System.out.println(maxMoney);
            return;
        }

        int minMoney = 0;
        int answer = 0;
        while (minMoney <= maxMoney) {
            int mid = (minMoney + maxMoney) / 2;

            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (arr[i] > mid) {
                    sum += mid;
                } else {
                    sum += arr[i];
                }
            }

            if (sum > limitMoney) {
                maxMoney = mid - 1;
            } else {
                minMoney = mid + 1;
                answer = mid;
            }
        }

        System.out.println(answer);
    }
}