package leetcode.matrix;

/*
1878. Get Biggest Three Rhombus Sums in a Grid

Pattern: Brute Force / Geometry / Set / Sorting

Time: O(n * m * min(n,m))
Space: O(n * m) (para armazenar somas distintas no Set)

Idea:
- Cada célula do grid é considerada como o topo de um rhombus.
- Para cada tamanho k possível, calcula a soma das bordas do rhombus (quatro diagonais).
- Soma apenas os elementos da borda, sem repetir cantos.
- Guarda todas as somas em um Set para evitar duplicatas.
- No final, ordena as somas em ordem decrescente e retorna os 3 maiores valores distintos.

Key trick:
- Usar Set para manter apenas valores distintos, evitando contar múltiplos rhombus com a mesma soma.
- Loop de diagonais evita recalcular o centro e os cantos.
- O problema pode TLE para grids grandes; otimização com prefix sums diagonais reduziria tempo.
*/

import java.util.*;

public class LC1878 {
    public int[] getBiggestThree(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        Set<Integer> st = new HashSet<>();

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                st.add(grid[i][j]);

                int k = 1;

                while (true){

                    int leftX = i + k;
                    int leftY =  j - k;

                    int rightX = i + k;
                    int rightY = j + k;

                    int botX = i + 2*k;
                    int botY = j;

                    if((leftX>=n) || (leftY<0) || (rightX>=n) || (rightY>=m) || (botX>=n))
                        break;

                    int sum = 0;

                    // Diagonal para baixo e para direita
                    for(int t = 0; t < k; t++){
                        sum += grid[i+t][j+t];
                    }

                    // Diagonal para baixo e para esquerda
                    for(int t = 0; t < k; t++){
                        sum += grid[i+k+t][j+k-t];
                    }

                    // Diagonal para cima e para esquera
                    for(int t = 0; t < k; t++){
                        sum += grid[i+2*k-t][j-t];
                    }

                    // Diagonal para cima e para direita
                    for(int t = 0; t < k; t++){
                        sum += grid[i+k-t][j-k+t];
                    }

                    st.add(sum);

                    k += 1;
                }
            }
        }

        List<Integer> list = new ArrayList<>(st);
        list.sort(Collections.reverseOrder());

        int n_size = Math.min(3, list.size());
        int[] top3 = new int[n_size];

        for (int i = 0; i < list.size() && i < 3; i++) {
            top3[i] = list.get(i);
        }

        return top3;
    }
}
