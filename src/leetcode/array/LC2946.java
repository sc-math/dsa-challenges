package leetcode.array;

/*
2946. Matrix Similarity After Cyclic Shifts

Pattern: Simulation / Circular Array

Time: O(m * n)
Space: O(1)

Idea:
- cada linha da matriz é rotacionada ciclicamente k posições
- como rotação circular repete a cada n, usamos k % n
- para cada célula, verificamos se o valor permanece igual após o shift

Key trick:
- usar índice circular: (c + k) % n
- evita criar uma nova matriz, comparando direto

Edge cases:
- k >= n → reduz para k % n
- n == 1 → sempre igual (rotação não muda nada)

Insight:
problema simples de rotação circular por linha,
basicamente verificar se a matriz é invariável após um shift de k posições
*/

public class LC2946 {
    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;

        for(int r = 0; r < m; r++){
            for(int c = 0; c < n; c++){
                if(mat[r][c] != mat[r][(c + k) % n])
                    return false;
            }
        }

        return true;
    }
}
