package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC0239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> monoDq = new ArrayDeque<>();
        int[] ans = new int[n -k +1];

        for(int i = 0; i < n; i++) {

            // Remove quem saiu da janela
            if (!monoDq.isEmpty() && monoDq.peekFirst() == i - k) {
                monoDq.pollFirst();
            }

            // Mantém a ordem decrescente
            while (!monoDq.isEmpty() && nums[monoDq.peekLast()] < nums[i]) {
                monoDq.pollLast();
            }

            // Adiciona o valor atual
            monoDq.offerLast(i);

            // Salva o máximo
            if (i >= k - 1) {
                ans[i - k + 1] = nums[monoDq.peekFirst()];
            }
        }

        return ans;
    }
}
