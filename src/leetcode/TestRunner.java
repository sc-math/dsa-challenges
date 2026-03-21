package leetcode;

import leetcode.matrix.P3643_FlipSquareSubmatrixVertically;

import java.util.Arrays;

public class TestRunner {
    public static void main(String[] args) {
        P3643_FlipSquareSubmatrixVertically sol = new P3643_FlipSquareSubmatrixVertically();

        System.out.println(Arrays.deepToString(sol.reverseSubmatrix(new int[][]{
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15, 16}},
                1, 0, 3)));
    }
}
