package algorithms.math;
/*
1464. Maximum Product of Two Elements in an Array

Pattern: Single Pass (Track Top 2 Max)

Time: O(n)
Space: O(1)

Idea:
- mesmo padrão dos problemas "max product of digits" e "three numbers"
- so precisa dos 2 maiores elementos do array, não precisa de ordenar o array inteiro
- não tem pegadinha de negativo aqui (constraints garantem nums[i] >= 1),
  então diferente do problema de 3 números, nao precisa rastrear os menores
- a cada elemento:
  1. se for maior que o maior atual -> vira o novo maior, o maior antigo vira segundoMaior
  2. senão, se for maior que o segundoMaior -> vira o novo segundoMaior
- resposta = (max1 - 1) * (max2 - 1), conforme pedido no enunciado

Key trick:
igual aos últimos 2 dias, single pass com 2 variáveis bate sort O(n log n)
*/

public class LC1464 {
    public int maxProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;

        for(int num : nums){
            if (num > max1) {
                max2 = max1;
                max1 = num;
            }
            else if (num > max2)
                max2 = num;
        }

        return (max1-1)*(max2-1);
    }
}
