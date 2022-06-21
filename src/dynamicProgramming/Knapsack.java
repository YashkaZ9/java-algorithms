package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Knapsack {
    public static void findMaxCostIter(int[] items, int W) {
        int[][] d = new int[items.length + 1][W + 1];
        for (int j = 0; j < W + 1; ++j) {
            d[0][j] = 0;
        }
        for (int i = 0; i < items.length + 1; ++i) {
            d[i][0] = 0;
        }
        for (int i = 1; i < items.length + 1; ++i) {
            for (int j = 1; j < W + 1; ++j) {
                d[i][j] = d[i - 1][j];
                if (items[i - 1] <= j) {
                    d[i][j] = Math.max(d[i][j], d[i - 1][j - items[i - 1]] + items[i - 1]);
                }
            }
        }
        List<Integer> costs = new ArrayList<>();
        int i = items.length;
        int j = W;
        while (i > 0 && j > 0) {
            if (j - items[i - 1] >= 0 && d[i][j] == d[i - 1][j - items[i - 1]] + items[i - 1]) {
                costs.add(items[i - 1]);
                j -= items[i - 1];
            }
            i--;
        }
        System.out.println(d[items.length][W]);
        Collections.reverse(costs);
        costs.forEach(cost -> System.out.print(cost + " "));
    }

    public static int findMaxCostRepsIter(int[] items, int W) {
        int maxCost = 0;
        int[] costs = new int[W + 1];
        for (int i = 0; i < W + 1; ++i) {
            for (int j = 0; j < items.length; ++j) {
                if (items[j] <= i) {
                    costs[i] = Math.max(costs[i], costs[i - items[j]] + items[j]);
                }
            }
        }
        return costs[W];
    }

//    public static int findMaxCostRec(int i, int W, int[] items, Map<Integer, Integer> optimalCosts) {
//        if (W <= 0 || i < 0) {
//            return 0;
//        }
//        if (!optimalCosts.containsKey(W)) {
//            int maxCost = Math.max(0, findMaxCostRec(i - 1, W, items, optimalCosts));
//            if (items[i] <= W) {
//                maxCost = Math.max(maxCost, findMaxCostRec(i - 1, W - items[i], items, optimalCosts) + items[i]);
//            }
//            optimalCosts.put(W, maxCost);
//    }
//        return optimalCosts.get(W);
//    }

    public static int findMaxCostRepsRec(int[] items, int W, Map<Integer, Integer> optimalCosts) {
        if (!optimalCosts.containsKey(W)) {
            int maxCost = 0;
            for (int i : items) {
                if (i <= W) {
                    maxCost = Math.max(maxCost, findMaxCostRepsRec(items, W - i, optimalCosts) + i);
                }
            }
            optimalCosts.put(W, maxCost);
        }
        return optimalCosts.get(W);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        int W = Integer.parseInt(info[0]);
        int n = Integer.parseInt(info[1]);
        int[] items = Arrays.stream(br.readLine().split(" ")).limit(n).mapToInt(Integer::valueOf).toArray();
        findMaxCostIter(items, W);
    }
}
