package leetcode.matrix;

/*
1886. Determine Whether Matrix Can Be Obtained By Rotation

Pattern: Matrix

Time: O(n^2)
Space: O(1)

Idea:
- Precisamos verificar se a matriz 'target' pode ser obtida a partir de 'mat'
  após 0, 90, 180 ou 270 graus de rotação.

- Em vez de rotacionar a matriz fisicamente, comparamos diretamente usando
  as fórmulas de rotação para cada caso.

- Para cada rotação:
    0°   → (i, j)
    90°  → (j, n - 1 - i)
    180° → (n - 1 - i, n - 1 - j)
    270° → (n - 1 - j, i)

- Percorremos toda a matriz e verificamos se todos os elementos batem.
- Se alguma rotação for válida, retornamos true imediatamente.

Key trick:
- Evitar rotacionar a matriz reduz complexidade de implementação.
- Usar fórmulas de mapeamento direto permite testar todas as rotações em O(n^2).
- Early break (isEqual) evita trabalho desnecessário.
*/

public class P1886_DetermineWheterMatrixCanBeObtainedByRotation {
    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;

        for(int rot = 0; rot <= 3; rot++){

            boolean isEqual = true;

            for(int i = 0; i < n && isEqual; i++){
                for(int j = 0; j < n && isEqual; j++){

                    if(rot == 0){   // Rotação 0º
                        if(mat[i][j] != target[i][j])
                            isEqual = false;
                    }
                    else if(rot == 1){  // Rotação 90º
                        if(mat[j][n - i - 1] != target[i][j])
                            isEqual = false;
                    }
                    else if(rot == 2){  // Rotação 180º
                        if(mat[n - 1 - i][n - 1 - j] != target[i][j])
                            isEqual = false;
                    }
                    else{   // Rotação 270º
                        if(mat[n -1 - j][i] != target[i][j])
                            isEqual = false;
                    }
                }
            }

            if(isEqual) return isEqual;
        }

        return false;
    }
}
