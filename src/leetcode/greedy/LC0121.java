package leetcode.greedy;

/*
121. Best Time to Buy and Sell Stock

Pattern: Greedy / Running Minimum

Time: O(n)
Space: O(1)

Idea:
- manter o menor preço visto até agora
- para cada dia calcular o lucro possível vendendo naquele dia
- atualizar o lucro máximo encontrado

Key trick:
atualizar o menor preço antes de calcular o lucro
*/

public class LC0121 {
    public int maxProfit(int[] prices) {

        int min_price = 1000000;
        int profit = 0;

        for(int r: prices){
            if(r < min_price){
                min_price = r;
            }
            else if(r - min_price > profit){
                profit = r - min_price;
            }
        }

        return profit;
    }
}
