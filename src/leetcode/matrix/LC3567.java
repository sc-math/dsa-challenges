package leetcode.matrix;

/*
3567. Minimum Absolute Difference in Submatrix

Pattern: Matrix / Sorting

Time: O((n* m) * (k^2 log(k)))
Space: O(k^2)

Idea:
- Para cada posição (i, j), consideramos a submatriz k x k iniciando ali.
- Coletamos todos os elementos dessa submatriz.
- Como precisamos da menor diferença absoluta entre DOIS valores distintos,
  ordenamos os elementos.

- Após ordenar, a menor diferença absoluta sempre estará entre elementos vizinhos.
- Percorremos o array ordenado e calculamos a diferença entre elementos consecutivos,
  ignorando valores iguais.

- Se todos os valores forem iguais (nenhum par distinto), a resposta é 0.

Key trick:
- Ordenar permite reduzir de O(n^2) comparações para O(n).
- Submatriz é tratada como um array temporário para simplificar a lógica.
*/

import java.util.TreeSet;

public class LC3567 {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] ans = new int[m-k+1][n-k+1];

        for(int row = 0; row < m - k +1; row++){
            for(int col = 0; col < n - k +1; col++){

                TreeSet<Integer> nums = new TreeSet<>();

                for(int i = 0; i < k; i++){
                    for(int j = 0; j < k; j++){
                        nums.add(grid[row + i][row + j]);
                    }
                }

                int prev = -1;
                boolean first = true;
                int minDiff = Integer.MAX_VALUE;

                if(nums.size() == 1){
                    ans[row][col] = 0;
                    continue;
                }

                for(int x : nums){
                    if(first){
                        prev = x;
                        first = false;
                        continue;
                    }

                    minDiff = Math.min(minDiff, x - prev);
                    prev = x;
                }

                ans[row][col] = minDiff;
            }
        }

        return ans;
    }
}
