package leetcode.utils;

import leetcode.matrix.LC3546;

public class TestRunner {
    public static void main(String[] args) {
        LC3546 sol = new LC3546();

        boolean ans = sol.canPartitionGrid(new int[][]{{28443},{33959}});
        System.out.println(ans);
    }
}
