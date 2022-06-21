package dynamicProgramming;

import java.io.*;
import java.util.Arrays;
import java.util.Random;

public class LCMS {
    public static int findLCMS(int[] arr) {
        int[] tempLCMS = new int[arr.length];
        int res = 0;
        for (int i = 0; i < arr.length; ++i) {
            tempLCMS[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (arr[i] % arr[j] == 0 && tempLCMS[j] + 1 > tempLCMS[i]) {
                    tempLCMS[i] = tempLCMS[j] + 1;
                }
            }
            res = Math.max(res, tempLCMS[i]);
        }
        return res;
    }

    public static void printLNIS(int[] arr) {
        int maxPathIdx = 0;
        int[] pathsArr = new int[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            pathsArr[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (arr[i] <= arr[j] && pathsArr[j] + 1 > pathsArr[i]) {
                    pathsArr[i] = pathsArr[j] + 1;
                }
            }
            maxPathIdx = pathsArr[i] > pathsArr[maxPathIdx] ? i : maxPathIdx;
        }
        int neededPathLength = pathsArr[maxPathIdx];
        int[] res = new int[neededPathLength];
        --neededPathLength;
        res[neededPathLength] = maxPathIdx;
        for (int i = maxPathIdx - 1; i >= 0 && neededPathLength > 0; --i) {
            if (pathsArr[i] == neededPathLength && arr[i] >= arr[res[neededPathLength]]) {
                neededPathLength--;
                res[neededPathLength] = i;
            }
        }
        System.out.println(res.length);
        Arrays.stream(res).forEach(i -> System.out.print(i + 1 + " "));
    }

    public static void generateTest() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File("input.txt"));
        Random random = new Random();
        int n = 100_000;
        pw.println(n);
        for (int i = 0; i < n; ++i) {
            int num = random.nextInt((int)1e9);
            pw.print(num + " ");
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        generateTest();
        long start = System.currentTimeMillis();
        BufferedReader bfr = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(bfr.readLine());
        int[] arr = Arrays.stream(bfr.readLine().split(" ")).limit(n).mapToInt(Integer::valueOf).toArray();
        printLNIS(arr);
        long finish = System.currentTimeMillis();
        System.out.println("\n" + (finish - start) + " ms");
    }
}
