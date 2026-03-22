package leetcode.hash;

/*
347. Top K Frequent Elements

Pattern: HashMap + Sorting

Time: O(n log n)
Space: O(n)

Idea:
- contar frequência dos números usando HashMap
- converter para lista de pares (num, freq)
- ordenar pela frequência em ordem decrescente
- retornar os k elementos mais frequentes

Key trick:
ordenar pelos valores do mapa
*/

import java.util.*;

class LC0347 {
    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> mp = new HashMap<>();

        for(int num:nums){
            int count = mp.getOrDefault(num, 0);
            mp.put(num, ++count);
        }

        List<int []> response_pair = new ArrayList<>();

        for(Map.Entry<Integer, Integer> entry : mp.entrySet()){
            int key = entry.getKey();
            int count = entry.getValue();

            response_pair.add(new int[]{key, count});
        }

        response_pair.sort((a, b) -> Integer.compare(b[1], a[1]));

        int [] response = new int[k];
        for(int i = 0; i < k; i++){
            response[i] = response_pair.get(i)[0];
        }

        return response;
    }
}
