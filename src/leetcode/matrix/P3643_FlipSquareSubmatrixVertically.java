package leetcode.matrix;

/*
3643. Reverse Submatrix Vertically

Pattern: Matrix

Time: O(k^2)
Space: O(1)

Idea:
- Precisamos inverter verticalmente uma submatriz k x k começando em (x, y).
- Isso equivale a trocar as linhas de cima com as de baixo dentro da submatriz.

- Iteramos apenas até metade das linhas (k / 2), pois cada troca resolve duas posições.
- Para cada coluna da submatriz, trocamos:
    (x + i, y + j) ↔ (x + k - 1 - i, y + j)

Key trick:
- Trabalhar com índices locais (0 → k) evita erros de cálculo.
- O acesso à matriz original é feito usando offset: (x + i, y + j).
*/

public class P3643_FlipSquareSubmatrixVertically {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {

        for(int i = 0; i < k/2; i++){
            for(int j = 0; j < k; j++){

                int temp = grid[x + i][y + j];
                grid[x + i][y + j] = grid[x + k -1 - i][y + j];
                grid[x + k -1 - i][y + j] = temp;
            }
        }

        return grid;
    }
}
