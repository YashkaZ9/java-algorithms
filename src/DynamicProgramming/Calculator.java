package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Calculator {
    public static void interResults(int n) {
        int[] preN = new int[n + 1];
        for (int i = 2; i < n + 1; ++i) {
            preN[i] = preN[i - 1];
            if (i % 2 == 0) {
                preN[i] = Math.min(preN[i], preN[i / 2]);
            }
            if (i % 3 == 0) {
                preN[i] = Math.min(preN[i], preN[i / 3]);
            }
            preN[i]++;
        }
        int neededNum = n;
        int[] res = new int[preN[n] + 1];
        res[preN[n]] = n;
        for (int i = n - 1; i >= 0; --i) {
            if (preN[i] == preN[neededNum] - 1 && (i + 1 == neededNum || i * 2 == neededNum || i * 3 == neededNum)) {
                neededNum = i;
                res[preN[neededNum]] = neededNum;
            }
        }
        System.out.println(preN[n]);
        Arrays.stream(res).forEach(i -> System.out.print(i + " "));
    }

    public static void interResultsOptimized(int n) {
        int[] preN = new int[n + 1];
        for (int i = 2; i < n + 1; ++i) {
            preN[i] = preN[i - 1];
            if (i % 2 == 0) {
                preN[i] = Math.min(preN[i], preN[i / 2]);
            }
            if (i % 3 == 0) {
                preN[i] = Math.min(preN[i], preN[i / 3]);
            }
            preN[i]++;
        }
        int neededNum = n;
        int[] res = new int[preN[n] + 1];
        res[preN[n]] = n;
        while(neededNum > 1) {
            if (neededNum % 2 == 0 && preN[neededNum / 2] < preN[neededNum]) {
                neededNum /= 2;
                res[preN[neededNum]] = neededNum;
            }
            else if (neededNum % 3 == 0 && preN[neededNum / 3] < preN[neededNum]) {
                neededNum /= 3;
                res[preN[neededNum]] = neededNum;
            }
            else {
                neededNum -= 1;
                res[preN[neededNum]] = neededNum;
            }
        }
        System.out.println(preN[n]);
        Arrays.stream(res).forEach(i -> System.out.print(i + " "));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long start = System.currentTimeMillis();
        interResults(n);
        long finish = System.currentTimeMillis();
        System.out.println("\nNon: " + (finish - start) + " ms");
        start = System.currentTimeMillis();
        interResultsOptimized(n);
        finish = System.currentTimeMillis();
        System.out.println("\nOpt: " + (finish - start) + " ms");
    }
}
