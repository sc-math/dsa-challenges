package leetcode.array;

/*
1480. Running Sum of 1d Array

Pattern: Prefix Sum / Cumulative Sum

Time: O(n)
Space: O(1)

Idea:
- acumular a soma dos elementos anteriores
- fazer in-place no array original

Key trick:
cada elemento é atualizado somando o anterior
*/

public class LC1480 {
    public int[] runningSum(int[] nums) {

        for(int i = 1; i < nums.length; i++){
            nums[i] += nums[i-1];
        }

        return nums;
    }
}
