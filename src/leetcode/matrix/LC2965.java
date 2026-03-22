package leetcode.matrix;

/*
2965. Find the Repeated and Missing Values

Pattern: Array / Sorting

Time: O(n log n)
Space: O(n)

Idea:
- transformar a matriz em array 1D
- ordenar
- percorrer para achar:
    * valor repetido
    * valor faltando
*/

import java.util.*;

public class LC2965 {

    public int[] findMissingAndRepeatedValues(int[][] grid) {
        List<Integer> numList = new ArrayList<>();

        // transforma a matriz 2D em lista 1D
        for (int[] row : grid) {
            for (int num : row) {
                numList.add(num);
            }
        }

        Collections.sort(numList);

        int lastNum = 0;
        int repeated = 0;
        int missing = 0;

        for (int num : numList) {
            if (num == lastNum) {
                repeated = num;
            }
            if (num > lastNum + 1) {
                missing = lastNum + 1;
            }
            lastNum = num;
        }

        if (missing == 0) {
            missing = numList.size();
        }

        return new int[]{repeated, missing};
    }
}