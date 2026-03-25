package leetcode.utils;

import leetcode.slidingwindow.LC0239;

public class TestRunner {
    public static void main(String[] args) {
        LC0239 sol = new LC0239();

        sol.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
    }
}
