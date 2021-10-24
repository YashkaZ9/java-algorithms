package DynamicProgramming;

import java.util.Arrays;

public class MinChange {

    public static int findMinCoinsToChange(int[] coins, int n) {
        int[] optimalCount = new int[n + 1];
        for (int i = 0; i < optimalCount.length; ++i) {
            optimalCount[i] = i;
        }
        for (int i = 1; i < n + 1; ++i) {
            for (int coin : coins) {
                if (coin <= i) {
                    optimalCount[i] = Math.min(optimalCount[i], optimalCount[i - coin] + 1);
                }
            }
        }
        return optimalCount[n];
    }

    public static void main(String[] args) {
        int[] coins = {1, 5, 8, 6, 3, 7, 4, 2};
        int n = 17;
        System.out.println(findMinCoinsToChange(coins, n));
    }
}
