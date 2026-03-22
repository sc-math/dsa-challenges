package leetcode.stack;

/*
739. Daily Temperatures

Pattern: Monotonic Stack / Next Greater Element

Time: O(n)
Space: O(n)

Idea:
- usar stack para manter índices de temperaturas decrescentes
- quando encontrar uma temperatura maior, pop e calcula diferença de índices
- repete para todo o array

Key trick:
pilha monotônica mantém a ordem decrescente para calcular o próximo maior elemento em O(n)
*/

import java.util.Stack;

public class LC0739 {
    public int[] dailyTemperatures(int[] temperatures) {

        Stack<Integer> stack = new Stack<>();
        int[] out = new int[temperatures.length];

        for(int i = 0; i < temperatures.length; i++){
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]){
                    int indice = stack.pop();
                    out[indice] = i - indice;
                }

            stack.push(i);
        }

        return out;
    }
}