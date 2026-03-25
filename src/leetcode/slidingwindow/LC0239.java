package leetcode.slidingwindow;

/*
239. Sliding Window Maximum

Pattern: Sliding Window + Monotonic Deque

Time: O(n)
Space: O(k)

Idea:
- manter uma deque com índices dos elementos da janela
- a deque é monotonicamente decrescente (valores maiores na frente)
- o primeiro elemento da deque é sempre o máximo da janela
- a cada passo:
  1. remover índices que saíram da janela
  2. remover da cauda valores menores que o atual
  3. adicionar o índice atual
  4. salvar o máximo quando a janela tiver tamanho k

Key trick:
usar uma deque monotônica para garantir que cada elemento entra e sai no máximo uma vez
*/

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
