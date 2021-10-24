package DynamicProgramming;

public class MatrixMult {
    public static void multiplyMatrix(int[][] a, int[][] b) {
        int[][] res = new int[a.length][b[0].length];
        for (int i = 0; i < res.length; ++i) {
            for (int j = 0; j < res[i].length; ++j) {
                for (int k = 0; k < b.length; ++k) {
                    res[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        for (int i = 0; i < res.length; ++i) {
            for (int j = 0; j < res[i].length; ++j) {
                System.out.printf("%8d", res[i][j]);
            }
            System.out.println("\n");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] a = new int[][] {
                {1, 4, 7},
                {2, 3, 8},
                {3, 4, 9},
                {-2, 3, -4}
        };
        int[][] b = new int[][] {
                {-5, 8, 11, 2},
                {15, -2, 0, 1},
                {3, 0, 4, 4}
        };
        multiplyMatrix(a, b);
    }
}
