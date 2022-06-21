package divideAndConquer;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

public class CountSort {
    public static int[] countSort(int[] arr) {
        int[] sorted = new int[arr.length];
        int[] nums = new int[10];
        Arrays.stream(arr).forEach(i -> nums[i]++);
        for (int i = 1; i < nums.length; ++i) {
            nums[i] += nums[i - 1];
        }
        for (int i = arr.length - 1; i >= 0; --i) {
            int pos = nums[arr[i]] - 1;
            sorted[pos] = arr[i];
            nums[arr[i]]--;
        }
        return sorted;
    }

    public static void countSortByIndex(int[] arr, int idx) {
        int[] sorted = new int[arr.length];
        int[] digits = new int[10];
        int radixNumber = (int)Math.pow(10, idx);
        Arrays.stream(arr).forEach(num -> digits[(num / radixNumber) % 10]++);
        for (int i = 1; i < digits.length; ++i) {
            digits[i] += digits[i - 1];
        }
        int radixDigit;
        for (int i = arr.length - 1; i >= 0; --i) {
            radixDigit = (arr[i] / radixNumber) % 10;
            int pos = digits[radixDigit] - 1;
            sorted[pos] = arr[i];
            digits[radixDigit]--;
        }
        System.arraycopy(sorted, 0, arr, 0, sorted.length);
    }

    public static void radixSort(int[] arr) {
        int radixCount = String.valueOf(arr[0]).length();
        for (int i = 0; i < radixCount; ++i) {
            countSortByIndex(arr, i);
        }
    }

    public static void generateArray(int[] arr) {
        Random random = new Random();
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = 100_000 + random.nextInt(900_000);
        }
    }

    public static void benchmark(int[] arr, Consumer<int[]> sorter) {
        long start = System.currentTimeMillis();
        sorter.accept(arr);
        long finish = System.currentTimeMillis();
        System.out.println(Arrays.toString(Arrays.stream(arr).limit(10).toArray()));
        System.out.println(finish - start + " ms");
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        int n = Integer.parseInt(reader.readLine());
//        int[] nums = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
//        long start = System.currentTimeMillis();
//        int[] sortedNums = countSort(nums);
//        Arrays.stream(sortedNums).forEach(i -> System.out.print(i + " "));
//        long finish = System.currentTimeMillis();
//        System.out.println("\n" + (finish - start) + " ms");
        int[] arr = new int[1_000_000];
        generateArray(arr);
        System.out.println("Built-in sort");
        benchmark(arr.clone(), Arrays::sort);
        System.out.println("Quick sort: ");
        benchmark(arr.clone(), SegmentsCoverPoints::quickSort);
        System.out.println("Radix sort: ");
        benchmark(arr.clone(), CountSort::radixSort);
    }
}
