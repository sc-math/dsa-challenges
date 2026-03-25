package leetcode.matrix;

/*
3546. Can Partition Grid

Pattern: Prefix Sum (1D aplicado em linhas/colunas)

Time: O(m * n)
Space: O(m + n)

Idea:
- calcular a soma total da matriz
- calcular a soma de cada linha e cada coluna
- testar cortes possíveis:
  1. horizontal (entre linhas)
  2. vertical (entre colunas)
- usar prefix sum para acumular e comparar:
  prefix == total - prefix

Key trick:
reduzir o problema 2D para prefix sum 1D (linhas/colunas)
e testar divisões válidas sem precisar recomputar somas
*/

public class LC3546 {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        long[] sum_row = new long[m];
        long[] sum_col = new long[n];
        long total = 0;

        // soma total
        for (int[] row : grid) {
            for (int val : row) {
                total += val;
            }
        }

        // présum das linhas
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                sum_row[i] += grid[i][j];
            }
        }

        // presum das colunas
        for(int j = 0; j < n; j++){
            for(int i = 0; i < m; i++){
                sum_col[j] += grid[i][j];
            }
        }

        // Testa divisão nas linhas
        long prefix = 0;
        for(int i = 0; i < m-1; i++){
            prefix += sum_row[i];
            if(prefix == total - prefix)
                return true;
        }

        // Testa divisão nas colunas
        prefix = 0;
        for(int j = 0; j < n-1; j++){
            prefix += sum_col[j];
            if(prefix == total - prefix)
                return true;
        }

        return false;
    }
}
