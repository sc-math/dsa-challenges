package leetcode;

import leetcode.matrix.P1886_DetermineWheterMatrixCanBeObtainedByRotation;

public class TestRunner {
    public static void main(String[] args) {
        P1886_DetermineWheterMatrixCanBeObtainedByRotation sol = new P1886_DetermineWheterMatrixCanBeObtainedByRotation();

        System.out.println(sol.findRotation(new int[][] {{0,0},{1,0}},
                new int[][]{{1,0}, {0,0}}));
    }
}
