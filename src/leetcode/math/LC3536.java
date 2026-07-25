package leetcode.math;

/*
3536. Maximum Product of Two Digits

Pattern: Single Pass

Time: O(d) -> d = número de dígitos de n
Space: O(1)

Idea:
- extrair os dígitos de n com % 10 e / 10
- manter apenas os dois maiores dígitos encontrados até agora
- não precisa de brute force O(d^2), DP ou sort, já que:
  1. dígitos são sempre >= 0, não existe caso de "dois negativos" a considerar
  2. o produto máximo é sempre maior * segundoMaior (não tem subproblema pra memoizar)
- a cada dígito extraído:
  1. se for maior que o maior atual -> vira o novo maior, o maior antigo vira segundoMaior
  2. senão, se for maior que o segundoMaior -> vira o novo segundoMaior

Key trick:
não precisa ordenar nem guardar array de dígitos, uma passada com 2 variáveis já resolve
*/

public class LC3536 {
    public int maxProduct(int n) {
        int maior = 0;
        int segundo_maior = 0;

        while(n > 0){
            int digito = n % 10;
            n /= 10;

            if(digito > maior){
                segundo_maior = maior;
                maior = digito;
            }else if (digito > segundo_maior)
                segundo_maior = digito;
        }

        return maior * segundo_maior;
    }
}
