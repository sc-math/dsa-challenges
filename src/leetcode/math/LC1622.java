package leetcode.math;

/*
1622. Fancy Sequence

Pattern: Math / Linear Transformation / Modular Arithmetic

Time: O(1) per operation
Space: O(n)

Idea:
- Cada elemento da lista sofre uma função linear f(x) = a*x + b
  onde 'a' acumula multipliações e 'b' acumula adições.
- Ao adicionar um novo elemento, ele é "normalizado" para não sofrer
  retroativamente as operações anteriores.
- Modular inverse é usado para "desfazer" multiplicações acumuladas na hora
  de salvar o elemento.

Key trick:
- Usar ax + b acumulado em vez de atualizar toda a lista em cada operação,
  garantindo O(1) por operação mesmo com muitas modificações.
- Modular arithmetic evita overflow e atende restrição do problema.
*/

import java.util.ArrayList;

public class LC1622 {
    private ArrayList<Long> list;
    private long totalAdd;
    private long totalMult;
    private static final int MOD = 1_000_000_007;

    public LC1622() {
        list = new ArrayList<>();
        totalAdd = 0;
        totalMult = 1;
    }

    private long modPow(long x, long y, long mod){
        long res = 1;
        x = x % mod;

        while(y > 0){
            if (y % 2 == 1){
                res = (res * x) % mod;
            }
            y = y / 2;
            x = (x * x) % mod;
        }
        return res;
    }

    public void append(int val) {
        long x = (val - totalAdd + MOD) % MOD;
        this.list.add((x * modPow(totalMult, MOD-2, MOD)) % MOD);
    }

    public void addAll(int inc) {
        totalAdd = (totalAdd + inc) % MOD;
    }

    public void multAll(int m) {
        totalAdd = (totalAdd * m) % MOD;
        totalMult = (totalMult * m) % MOD;
    }

    public int getIndex(int idx) {
        if(idx >= list.size()) return -1;
        return (int) ((totalMult * list.get(idx) + totalAdd) % MOD);
    }
}
