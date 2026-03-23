package leetcode.dp;

/*
1594. Maximum Non Negative Product in a Matrix

Pattern: Dynamic Programming (Grid) / Min-Max Tracking

Time: O(m * n)
Space: O(m * n)

Idea:
- para cada célula, guardar o maior e o menor produto possível até ali
- isso é necessário porque números negativos podem virar positivos
- a cada passo, considerar os 4 casos:
  (max cima, min cima, max esquerda, min esquerda)

Key trick:
manter dois estados (max e min) por célula para lidar com sinais negativos
*/

public class LC1594 {
    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        long[][] max = new long[m][n];
        long[][] min = new long[m][n];

        max[0][0] = grid[0][0];
        min[0][0] = grid[0][0];

        // Primeira Coluna
        for(int i = 1; i < m; i++){
            max[i][0] = max[i-1][0] * grid[i][0];
            min[i][0] = min[i-1][0] * grid[i][0];
        }

        // Primeira Linha
        for(int j = 1; j < n; j++){
            max[0][j] = max[0][j-1] * grid[0][j];
            min[0][j] = min[0][j-1] * grid[0][j];
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                long val = grid[i][j];

                long maxUp = max[i-1][j] * val;
                long minUp = min[i-1][j] * val;
                long maxLeft = max[i][j-1] * val;
                long minLeft = min[i][j-1] * val;

                max[i][j] = Math.max(Math.max(maxUp, minUp), Math.max(maxLeft, minLeft));
                min[i][j] = Math.min(Math.min(maxUp, minUp), Math.min(maxLeft, minLeft));
            }
        }

        long ans = max[m-1][n-1];

        return ans < 0 ? -1 : (int)(ans % 1_000_000_007);
    }
}
