package leetcode.matrix;

/*
3070. Count Submatrices with Top-Left Element and Sum Less Than K

Pattern: Matrix / Prefix Sum

Time: O(n * m)
Space: O(1) (in-place)

Idea:
- Queremos contar submatrizes que começam em (0,0) até (i,j) com soma <= k.
- Para isso, acumulamos prefix sum vertical:
  cada célula passa a representar a soma da coluna até aquela linha.
- Em cada linha, somamos os valores acumulados da esquerda pra direita,
  formando a soma da submatriz (0,0) → (row, col).
- Se a soma for <= k, contamos.

Key trick:
- Transformar a matriz em prefix sum vertical (in-place).
- Usar um acumulador horizontal pra obter rapidamente a soma da submatriz.
- Evita usar matriz auxiliar, mantendo O(1) espaço.
*/

public class LC3070 {
    public int countSubmatrices(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;
        int ans = 0;

        for(int row = 0; row < n; row++){
            int sums = 0;

            for(int col = 0; col < m; col++){
                if(row != 0){
                    grid[row][col] += grid[row-1][col];
                }

                sums += grid[row][col];

                if(sums <= k)
                    ans++;
            }
        }

        return ans;
    }
}
