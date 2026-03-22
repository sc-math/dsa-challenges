package leetcode.matrix;

/*
3212. Count Submatrices with Equal Frequency of X and Y

Pattern: Matrix / Prefix Sum 2D

Time: O(n * m)
Space: O(n * m)

Idea:
- Transformamos o problema convertendo:
  X → +1
  Y → -1
  outros → 0
- Assim, uma submatriz tem mesma quantidade de X e Y quando a soma for 0.

- Usamos prefix sum 2D para calcular rapidamente a soma da submatriz
  do canto (0,0) até (row, col).
- A fórmula usa:
  cima + esquerda - diagonal + valor atual
  para evitar contar áreas duplicadas.

- Também mantemos um grid booleano (has_x) para garantir que a submatriz
  contém pelo menos um 'X'.

- Para cada posição, se:
  soma == 0 && contém X → contamos.

Key trick:
- Reduzir o problema para soma zero usando +1 / -1.
- Usar prefix sum 2D para evitar recalcular somas de submatrizes.
- Usar matriz auxiliar booleana para validar presença de 'X'.
*/

public class LC3212 {
    public int numberOfSubmatrices(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int [][] grid_diff = new int [n+1][m+1];
        boolean [][] has_x = new boolean[n+1][m+1];

        int count = 0;

        for(int row = 1; row <= n; row++){
            for(int col = 1; col <= m; col++){

                int val = 0;

                if(grid[row-1][col-1] == 'X'){
                    val = 1;
                }
                else if(grid[row-1][col-1] == 'Y')
                    val = -1;

                grid_diff[row][col] = grid_diff[row-1][col] + grid_diff[row][col-1] - grid_diff[row-1][col-1] + val;

                has_x[row][col] = has_x[row-1][col] || has_x[row][col-1] || has_x[row-1][col-1] || (grid[row-1][col-1] == 'X');

                if(has_x[row][col] && grid_diff[row][col] == 0)
                    count++;
            }
        }

        return count;
    }
}
