package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Staircase {
    public int maxSumStaircaseIter(int[] a, int n) {
        if (n == 1) return a[0];
        int[] stairs = new int[n];
        stairs[1] = a[0];
        for (int i = 2; i < n; ++i) {
            stairs[i] = Math.max(stairs[i - 2] + a[i - 1], stairs[i - 1] + a[i - 1]);
        }
        return Math.max(stairs[n - 2], stairs[n - 1]) + a[n - 1];
    }

    public int maxSumStaircaseOptimized(int[] a, int n) {
        int prev2 = 0;
        int prev1 = 0;
        for (int cur, i = 0; i < n - 1; ++i) {
            cur = Math.max(prev1 + a[i], prev2 + a[i]);
            prev2 = prev1;
            prev1 = cur;
        }
        return Math.max(prev1, prev2) + a[n - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        System.out.println(new Staircase().maxSumStaircaseOptimized(a, n));
    }
}
