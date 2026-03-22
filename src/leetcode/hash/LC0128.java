package leetcode.hash;

/*
128. Longest Consecutive Sequence

Pattern: HashSet / Sequence Expansion

Time: O(n)
Space: O(n)

Idea:
- colocar todos os números em um HashSet
- iniciar sequência apenas quando o número não possui predecessor (num - 1)
- expandir a sequência enquanto os próximos números existirem

Key trick:
só iniciar contagem em números que começam uma sequência
*/

import java.util.HashSet;
import java.util.Set;

public class LC0128 {
    public int longestConsecutive(int[] nums) {

        Set<Integer> numSet = new HashSet<>();

        for(int num : nums){
            numSet.add(num);
        }

        int longest = 0;

        for(int num : numSet){
            if(!numSet.contains(num - 1)){
                int length = 1;
                while(numSet.contains(num + length)){
                    length++;
                }

                longest = Math.max(longest, length);
            }
        }

        return longest;
    }
}
