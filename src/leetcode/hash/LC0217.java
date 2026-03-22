package leetcode.hash;

/*
217. Contains Duplicate

Pattern: HashSet / Duplicate Detection

Time: O(n)
Space: O(n)

Idea:
- percorrer o array
- usar um HashSet para armazenar números já vistos
- se um número já estiver no set, existe duplicata

Key trick:
HashSet permite verificar existência em O(1)
*/

import java.util.HashSet;
import java.util.Set;

public class LC0217 {

    public boolean hasDuplicate(int[] nums) {

        Set<Integer> seen = new HashSet<>();

        for(int num : nums){
            if(seen.contains(num))
                return true;

            seen.add(num);
        }

        return false;
    }
}
