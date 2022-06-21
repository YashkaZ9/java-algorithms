package divideAndConquer;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class SegmentsCoverPoints {
    public static Random random = new Random();

    public static void swap(int[] arr, int a, int b) {
        int t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }

    public static void quickSort(int[] arr) {
        if (arr.length <= 1) return;
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int l, int r) {
        if (l >= r) return;
        int pivot = l + random.nextInt(r - l + 1);
        swap(arr, l, pivot);
        pivot = l;
        int j = pivot;
        for (int i = l + 1; i <= r; ++i) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, ++j);
            }
        }
        swap(arr, pivot, j);
        if (j > l) quickSort(arr, l, j - 1);
        if (j < r) quickSort(arr, j + 1, r);
    }

    public static int getSegmentsCount(int[] arr, int target, Comparator<Integer> comparator) {
        int l = -1;
        int r = arr.length;
        while (l + 1 < r) {
            int m = (l + r) >> 1;
            if (comparator.compare(arr[m], target) < 0) {
                l = m;
            } else {
                r = m;
            }
        }
        return l;
    }

    public static int countCoveringSegments(int[] begins, int[] ends, int point) {
        int lefts = getSegmentsCount(begins, point, new CompareLessOrEqual());
        int rightsLessLefts = getSegmentsCount(ends, point, new CompareLess());
        return lefts - rightsLessLefts;
    }

    static class CompareLessOrEqual implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 <= o2 ? -1 : 1;
        }
    }

    static class CompareLess implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 < o2 ? -1 : 1;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        long start = System.currentTimeMillis();
//        Scanner scanner = new Scanner(new File("input.txt"));
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = scanner.nextInt();
            b[i] = scanner.nextInt();
        }
        quickSort(a);
        quickSort(b);
        for (int i = 0; i < m; ++i) {
            int point = scanner.nextInt();
            System.out.print(countCoveringSegments(a, b, point) + " ");
        }
        long end = System.currentTimeMillis();
        System.out.println();
        System.out.println(end - start + " ms");
    }
}
