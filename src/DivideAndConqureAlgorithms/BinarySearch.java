package DivideAndConqureAlgorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BinarySearch {
    public static int binarySearch(int target, int[] arr) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr[m] == target)
                return m + 1;
            if (target < arr[m])
                r = m - 1;
            else
                l = m + 1;
        }
        return -1;
    }

    public static int findLeftBound(int target, int[] arr) {
        int l = -1;
        int r = arr.length;
        while (l + 1 < r) {
            int m = (r + l) >> 1;
            if (arr[m] >= target)
                r = m;
            else
                l = m;
        }
        if (r < arr.length && arr[r] == target)
            return r + 1;
        return -1;
    }

    public static int findRightBound(int target, int[] arr) {
        int l = -1;
        int r = arr.length;
        while (l + 1 < r) {
            int m = (r + l) >> 1;
            if (arr[m] <= target)
                l = m;
            else
                r = m;
        }
        if (l >= 0 && arr[l] == target)
            return l + 1;
        return -1;
    }

    public static void run() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input.txt"));
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();
        for (int i = 0; i < m; ++i) {
            System.out.print(findLeftBound(scanner.nextInt(), arr) + " ");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        BinarySearch.run();
    }
}
