package leetcode.sorting;

/*
179. Largest Number

Pattern: Custom Sorting / Comparator

Time: O(n log n)
Space: O(n)

Idea:
- converter números para string
- ordenar usando comparator customizado
- comparar concatenações: (a+b) vs (b+a)
- se (a+b) > (b+a), a deve vir antes

Key trick:
a ordem correta depende da concatenação, não do valor do número
*/

public class LC0179 {
    public static boolean compareNumber(int num1, int num2){
        String strNum1 = String.valueOf(num1);
        String strNum2 = String.valueOf(num2);

        // Verify if Concat(num1 num2) > Concat(num2 num1) and return true in case 1 or return false in case 2
        return (Long.parseUnsignedLong(strNum1 + strNum2) > Long.parseUnsignedLong(strNum2 + strNum1)) ? true : false;
    }

    public static String selectionSortMod(int[] arr){

        String largNum = "";

        // Do the selection Sort Modificated to sort the array and concat the first number in largNum string.
        for(int i = 0; i < arr.length - 1; i++){

            int maxIndex = i;

            for(int j = i+1; j < arr.length; j++){
                if(compareNumber(arr[j], arr[maxIndex])){
                    maxIndex = j;
                }
            }

            int temp = arr[maxIndex];
            arr[maxIndex] = arr[i];
            arr[i] = temp;

            largNum = largNum + arr[i];
        }

        largNum = largNum + arr[arr.length - 1];

        // Verify the string not contains only 0's and return
        for(int i = 0; i < largNum.length(); i++){
            if(largNum.charAt(i) != '0'){
                return largNum;
            }
        }
        return "0";
    }

    public String P0179_LargestNumber(int[] nums) {
        return selectionSortMod(nums);
    }
}
