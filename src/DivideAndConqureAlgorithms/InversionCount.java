package DivideAndConqureAlgorithms;

import java.io.*;
import java.util.Random;

public class InversionCount {
    private long inversionsCount;

    public long getInversionsCount() {
        return inversionsCount;
    }

    public void mergeSort(int[] arr) {
        inversionsCount = 0;
        mergeSort(arr, 0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int l, int r) {
        if (l >= r) return;
        int m = (r + l) >> 1;
        mergeSort(arr, l, m);
        mergeSort(arr, m + 1, r);
        merge(arr, l, m, r);
    }

    private void merge(int[] arr, int l, int m, int r) {
        int i = l, j = m + 1, k = 0;
        int[] temp = new int[r - l + 1];
        while (i <= m && j <= r) {
            if (arr[j] < arr[i]) {
                temp[k++] = arr[j++];
                inversionsCount += m - i + 1;
            }
            else {
                temp[k++] = arr[i++];
            }
        }
        while (i <= m) {
            temp[k++] = arr[i++];
        }
        while (j <= r) {
            temp[k++] = arr[j++];
        }
        System.arraycopy(temp, 0, arr, l, r - l + 1);
    }

    public static void generateInversionsCountTest() throws FileNotFoundException {
        PrintWriter pr = new PrintWriter(new File("input.txt"));
        Random random = new Random();
        int n = random.nextInt((int)1e5);
        int t = random.nextInt((int)1e9);
        pr.println(n);
        for (int i = 0; i < n; ++i) {
            t -= (1 + random.nextInt(100));
            pr.print(t + " ");
        }
        pr.close();
    }

    public static void main(String[] args) throws IOException {
        //generateInversionsCountTest();
        long start = System.currentTimeMillis();
        BufferedReader scanner = new BufferedReader(new FileReader("input.txt"));
//        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.readLine());
        int[] arr = new int[n];
//        IntStream.range(0, n).forEach(i -> arr[i] = scanner.nextInt());
        String[] tokens = scanner.readLine().split(" ");
        for (int i = 0; i < tokens.length; ++i) {
            arr[i] = Integer.parseInt(tokens[i]);
        }
        InversionCount res = new InversionCount();
        res.mergeSort(arr);
//        System.out.println(Arrays.toString(arr));
        System.out.println(res.getInversionsCount());
        long finish = System.currentTimeMillis();
        System.out.println(finish - start + " ms");
    }
}
