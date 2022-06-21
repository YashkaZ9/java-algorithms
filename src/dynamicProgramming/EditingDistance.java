package dynamicProgramming;

import java.io.*;
import java.util.Random;

public class EditingDistance {
    public static int min3(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public static int diff(char a, char b) {
        return a == b ? 0 : 1;
    }

    public static void editingDistance(String a, String b) {
        int n = b.length();
        int m = a.length();
        int[][] d = new int[n + 1][m + 1];
        for (int i = 0; i < m + 1; ++i) {
            d[0][i] = i;
        }
        for (int j = 0; j < n + 1; ++j) {
            d[j][0] = j;
        }
        for (int i = 1; i < n + 1; ++i) {
            for (int j = 1; j < m + 1; ++j) {
                int ins = d[i][j - 1] + 1;
                int del = d[i - 1][j] + 1;
                int diff = d[i - 1][j - 1] + diff(a.charAt(j - 1), b.charAt(i - 1));
                d[i][j] = min3(ins, del, diff);
            }
        }
        StringBuilder ae = new StringBuilder();
        StringBuilder be = new StringBuilder();
        int i = n;
        int j = m;
        while (i > 0 || j > 0) {
            if (j == 0) {
                while (i-- > 0) {
                    be.append(b.charAt(i));
                    ae.append('-');
                }
            } else if (i == 0) {
                while (j-- > 0) {
                    ae.append(a.charAt(j));
                    be.append('-');
                }
            } else {
                char atA = a.charAt(j - 1);
                char atB = b.charAt(i - 1);
                int min = min3(d[i][j - 1], d[i - 1][j], d[i - 1][j - 1]);
                if (d[i - 1][j - 1] == min) {
                    ae.append(atA);
                    be.append(atB);
                    i--;
                    j--;
                } else if (d[i][j - 1] == min) {
                    ae.append(atA);
                    be.append('-');
                    j--;
                } else {
                    ae.append('-');
                    be.append(atB);
                    i--;
                }
            }
        }
        System.out.println(d[n][m]);
        System.out.println(ae.reverse().toString());
        System.out.println(be.reverse().toString());
    }

    public static void generateTest() throws FileNotFoundException {
        PrintWriter pr = new PrintWriter(new File("input.txt"));
        Random random = new Random();
        int n = 10_000;
        for (int i = 0; i < n; ++i) {
            pr.print((char)('a' + random.nextInt(26)));
        }
        pr.println();
        for (int i = 0; i < n; ++i) {
            pr.print((char)('a' + random.nextInt(26)));
        }
        pr.println();
        pr.close();
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        generateTest();
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String a = br.readLine();
        String b = br.readLine();
        long start = System.currentTimeMillis();
        editingDistance(a, b);
        long finish = System.currentTimeMillis();
        System.out.println(finish - start + " ms");
    }
}
