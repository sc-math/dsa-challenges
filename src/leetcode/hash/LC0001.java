package leetcode.hash;

/*
1. Two Sum

Pattern: HashMap / Complement lookup

Time: O(n)
Space: O(n)

Idea:
- percorrer o array
- calcular o complemento (target - nums[i])
- verificar se o número atual já apareceu como complemento
- usar HashMap para lookup O(1)

Key trick:
guardar o complemento esperado no HashMap
*/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC0001 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            int diff = target - nums[i];
            if(map.containsKey(nums[i])){

                int [] response = new int[]{i, map.get(nums[i])};
                Arrays.sort(response);
                return response;
            }

            map.put(diff, i);
        }

        return nums;
    }
}
