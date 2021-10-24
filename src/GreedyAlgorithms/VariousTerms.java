package GreedyAlgorithms;

import java.util.*;

public class VariousTerms {
    public static void maxDifferentTerms() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int i = 1;
        int sum = 0;
        List<Integer> terms = new ArrayList<>();
        for (; sum < n; i++) {
            terms.add(i);
            sum += i;
        }
        if (sum - n > 0)
            terms.remove(sum - n - 1);
        System.out.println(terms.size());
        terms.forEach(t -> System.out.print(t + " "));
    }

    public static void main(String... args) {
        maxDifferentTerms();
    }
}
