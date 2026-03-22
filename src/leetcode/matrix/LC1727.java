package leetcode.matrix;

/*
1727. Largest Submatrix With Rearrangements

Pattern: Matrix / Greedy / Sorting / Histogram

Time: O(n * m log m)
Space: O(m)

Idea:
- Para cada linha, transformamos a matriz em alturas acumuladas de 1s (como um histograma).
- Cada valor representa quantos 1s consecutivos existem acima (incluindo a linha atual).
- Podemos rearranjar as colunas livremente em cada linha, então ordenamos a linha em ordem decrescente.
- Para cada posição j, calculamos a área possível como altura * largura,
  onde largura = j + 1 (usando as maiores alturas disponíveis).

Key trick:
- Transformar a matriz em histogramas acumulados.
- Ordenar cada linha para simular o melhor rearranjo possível das colunas.
- Calcular a área máxima usando as maiores alturas primeiro.
*/

import java.util.Arrays;

public class LC1727 {
    public int largestSubmatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int ans = 0;

        // Alterar os valores internos da matrix pela quantidade de 1s seguidos
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(matrix[i][j] != 0 && i > 0){
                    matrix[i][j] += matrix[i-1][j];
                }
            }

            // Copiar a linha atual e ordenar as colunas inversamente
            int [] curr_row = matrix[i].clone();
            Arrays.sort(curr_row);

            for (int l = 0, r = curr_row.length - 1; l < r; l++, r--) {
                int temp = curr_row[l];
                curr_row[l] = curr_row[r];
                curr_row[r] = temp;
            }

            // Calcular o retângulo com a maior área
            for(int j = 0; j < curr_row.length; j++){
                ans = Math.max(ans, curr_row[j] * (j + 1));
            }
        }

        return ans;
    }
}
