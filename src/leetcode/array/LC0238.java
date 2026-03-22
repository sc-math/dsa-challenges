package leetcode.array;

/*
238. Product of Array Except Self

Pattern: Prefix Product

Time: O(n)
Space: O(1)

Idea:
- calcular produto acumulado da esquerda (prefix)
- calcular produto acumulado da direita (suffix)
- multiplicar ambos para obter o resultado

Key trick:
armazenar prefix no array de resultado e usar uma variável para suffix
*/

public class LC0238 {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        result[0] = 1;

        for(int i = 1; i < n; i++){
            result[i] = result[i-1] * nums[i-1];
        }

        int suffix = 1;

        for(int i = n-1; i >= 0; i--){
            result[i] *= suffix;
            suffix *= nums[i];
        }

        return result;
    }
}
