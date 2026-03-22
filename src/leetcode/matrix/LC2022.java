package leetcode.matrix;

/*
2022. Convert 1D Array Into 2D Array

Pattern: Array / Simulation

Time: O(m*n)
Space: O(m*n)

Idea:
- verifica se o tamanho do array bate com m*n
- cria a matriz m x n
- preenche linha por linha usando índice
*/

public class LC2022 {
    public int[][] construct2DArray(int[] original, int m, int n) {

        if(original.length != (m * n)){
            // System.out.println("Dentro do if");
            return new int[][]{};
        }

        int[][] matrix = new int[m][n];

        int index = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = original[index];
                index++;
            }
        }

        return matrix;
    }
}
