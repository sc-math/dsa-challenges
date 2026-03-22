package leetcode.utils;

import leetcode.matrix.LC1886;

public class TestRunner {
    public static void main(String[] args) {
        LC1886 sol = new LC1886();

        System.out.println(sol.findRotation(new int[][] {{0,0},{1,0}},
                new int[][]{{1,0}, {0,0}}));
    }
}
