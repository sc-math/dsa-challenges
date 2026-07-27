package algorithms.math;
/*
628. Maximum Product of Three Numbers

Pattern: Single Pass (Track Top 3 Max + Bottom 2 Min)

Time: O(n)
Space: O(1)

Idea:
- diferente do maxProduct de 2 digits, aqui pode ter números negativos
- dois negativos multiplicados viram positivo, então o produto máximo
  pode vir de duas formas:
  1. Os 3 maiores números do array (caso normal, todos positivos)
  2. Os 2 menores números (podem ser bem negativos) * o maior número
- para não ordenar em O(n log n), da para manter só 5 variáveis numa
  unica passada: max1, max2, max3, min1, min2
- a cada número, atualiza essas 5 variáveis (parece gambiarra de ifs,
  mas é so uma insertion sort de tamanho fixo, continua O(n))
- resposta final = max(max1*max2*max3, max1*min1*min2)

Key trick:
não precisa ordenar o array inteiro, so rastrear os 3 maiores e os 2
menores numa unica passada
*/

public class LC0628 {
    public int maximumProduct(int[] nums) {

        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;

        for(int num : nums){

            // Guarda os 3 maiores valores
            if(num > max1){
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2){
                max3 = max2;
                max2 = num;
            } else if (num > max3){
                max3 = num;
            }

            // Guarda os 2 menores valores
            if (num < min1){
                min2 = min1;
                min1 = num;
            } else if (num < min2)
                min2 = num;
        }

        // retorna o máximo entre as 2 possibilidades
        return Math.max(max1 * max2 * max3, max1 * min1 * min2);
    }
}
