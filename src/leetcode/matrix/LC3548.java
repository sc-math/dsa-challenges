package leetcode.matrix;

/*
3548. Equal Sum Grid Partition II

Pattern: Prefix Sum + Simulation + HashMap (Frequency) + Edge Cases (1D Grid)

Time: O(m * n)
Space: O(m * n)

Idea:
- tentar todos os cortes possíveis (horizontal e vertical)
- usar prefix sum pra calcular soma da esquerda e direita rapidamente
- manter frequência dos valores em cada lado usando HashMap (left / right)
- se as somas forem iguais → resposta true
- senão, calcular diff = |left - right|
- tentar remover uma célula com valor = diff do lado maior

Key trick:
- atualizar dinamicamente os mapas ao mover o corte (simulação)
- evitar overflow usando long e limitando diff (≤ 1e5)

Edge cases:
- grid 1D (m == 1 ou n == 1):
  só pode remover das extremidades do segmento
- subgrid com apenas 1 linha ou 1 coluna:
  remover do meio quebra conectividade → só bordas
- diff maior que 1e5 pode ser ignorado (não existe no grid)

Insight:
esse problema é uma extensão do “Product of Array Except Self” + prefix,
mas com uma camada extra de simulação e regras de conectividade
*/

import java.util.HashMap;
import java.util.Map;

public class LC3548 {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        long[] sum_row = new long[m];
        long[] sum_col = new long[n];
        long total = 0;

        Map<Integer, Integer> freq = new HashMap<>();

        // soma total
        for (int[] row : grid) {
            for (int val : row) {
                total += val;

                freq.put(val, freq.getOrDefault(val, 0) +1);
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

        Map<Integer, Integer> leftMap = new HashMap<>();
        Map<Integer, Integer> rightMap = new HashMap<>(freq);

        long left;
        long right;

        // Testa divisão nas linhas
        long prefix = 0;
        for(int i = 0; i < m-1; i++){
            prefix += sum_row[i];

            for(int j = 0; j < n; j++){
                int val = grid[i][j];

                leftMap.put(val, leftMap.getOrDefault(val, 0)+1);

                int count = rightMap.getOrDefault(val, 0) -1;
                if(count == 0) rightMap.remove(val);
                else rightMap.put(val, count);
            }

            left = prefix;
            right = total - prefix;

            if(left == right)
                return true;
            else{
                long diff = Math.abs(left - right);
                if(diff > 100000) continue;
                int d = (int) diff;

                if (left > right) {

                    if (n == 1) {
                        // coluna única → lado de cima [0 ... i]
                        if (grid[0][0] == diff || grid[i][0] == diff)
                            return true;
                    } else {
                        int height = i + 1;

                        if (height == 1) {
                            if (grid[0][0] == diff || grid[0][n - 1] == diff)
                                return true;
                        } else {
                            if (leftMap.containsKey(d)) return true;
                        }
                    }

                } else {

                    if (n == 1) {
                        // coluna única → lado de baixo [i+1 ... m-1]
                        if (grid[i+1][0] == diff || grid[m - 1][0] == diff)
                            return true;
                    } else {
                        int height = m - i - 1;

                        if (height == 1) {
                            int row = i + 1;
                            if (grid[row][0] == diff || grid[row][n - 1] == diff)
                                return true;
                        } else {
                            if (rightMap.containsKey(d)) return true;
                        }
                    }
                }
            }
        }

        // Testa divisão nas colunas
        leftMap = new HashMap<>();
        rightMap = new HashMap<>(freq);
        prefix = 0;
        for(int j = 0; j < n-1; j++){
            prefix += sum_col[j];

            for(int i = 0; i < m; i++){
                int val = grid[i][j];

                leftMap.put(val, leftMap.getOrDefault(val, 0)+1);

                int count = rightMap.getOrDefault(val, 0) -1;
                if(count == 0) rightMap.remove(val);
                else rightMap.put(val, count);
            }

            left = prefix;
            right = total - prefix;

            if(left == right)
                return true;
            else{
                long diff = Math.abs(left - right);
                if(diff > 100000) continue;

                int d = (int) diff;

                if (left > right) {
                    if (m == 1) {
                        // lado esquerdo: [0 ... j]
                        if (grid[0][0] == diff || grid[0][j] == diff)
                            return true;
                    } else {
                        int width = j + 1;

                        if (width == 1) {
                            if (grid[0][0] == diff || grid[m - 1][0] == diff)
                                return true;
                        } else {
                            if (leftMap.containsKey(d)) return true;
                        }
                    }

                } else {
                    if (m == 1) {
                        if (grid[0][j+1] == diff || grid[0][n-1] == diff)
                            return true;
                    } else {
                        int width = n - j - 1;

                        if (width == 1) {
                            int col = j + 1;
                            if (grid[0][col] == diff || grid[m - 1][col] == diff)
                                return true;
                        } else {
                            if (rightMap.containsKey(d)) return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}
