package divideAndConquer;

import java.util.Random;

public class HeapSort {
    public static void swap(int[] arr, int a, int b) {
        int t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }

    public static void heapSort(int[] arr) {
        buildHeap(arr);
        int size = arr.length;
        while (size > 1) {
            swap(arr, 0, size - 1);
            size--;
            siftDown(arr, 0, size);
        }
    }

    public static void siftDown(int[] arr, int i, int size) {
        int childIndex = i;
        while (i * 2 < size) {
            if (arr[i * 2] > arr[i])
                childIndex = i * 2;
            if (i * 2 + 1 < size && arr[i * 2 + 1] > arr[childIndex])
                childIndex = i * 2 + 1;
            if (childIndex == i)
                break;
            swap(arr, i, childIndex);
            i = childIndex;
        }
    }

    public static void buildHeap(int[] arr) {
        for (int i = arr.length / 2; i >= 0; --i) {
            siftDown(arr, i, arr.length);
        }
    }

    public static void generateArray(int[] arr) {
        Random random = new Random();
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = random.nextInt(1_000_000);
        }
    }

    public static void main(String[] args) {
//        int[] arr = {8, -1, 4, 5, 3, 10, 100};
        int[] arr = new int[1_000_000];
        generateArray(arr);
        int[] arrCopy = new int[1_000_000];
        System.arraycopy(arr, 0, arrCopy, 0, arr.length);
        long start = System.currentTimeMillis();
        heapSort(arr);
        long finish = System.currentTimeMillis();
        System.out.println("Heap sort: " + (finish - start) + " ms");
        start = System.currentTimeMillis();
        SegmentsCoverPoints.quickSort(arrCopy);
        finish = System.currentTimeMillis();
        System.out.println("Quick sort: " + (finish - start) + " ms");
    }
}
