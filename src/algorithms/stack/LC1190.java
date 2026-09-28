package algorithms.stack;
/*
1190. Reverse Substrings Between Each Pair of Parentheses

Pattern: Recursive Descent (stack implícita via call stack)

Time: O(n^2) pior caso — reverse() em cada nível pode tocar o mesmo trecho várias vezes
Space: O(n) — profundidade da recursão + StringBuilder por nível

Idea:
- cada nível de parênteses é isolado, resolvido de dentro pra fora
- idx compartilhado como cursor (igual 1096)
- ao fechar ')', inverte o StringBuilder do nível ANTES de devolver —
  assim o nível de fora só dá append, nunca precisa inserir no início

Key trick:
- reverse() no retorno evita inserir no começo do StringBuilder (caro)
- recursão = stack implícita: cada chamada é um nível empilhado, retorno é o pop

Insight:
mesmo esqueleto do 1096 (idx compartilhado, resolve no retorno),
trocando Set<String>+cartesian product por StringBuilder+reverse()
*/

public class LC1190 {
    int idx;
    String s;

    public String reverseParentheses(String s) {
        this.idx = 0;
        this.s = s;

        return solution();
    }

    private String solution(){
        StringBuilder word = new StringBuilder();

        while(idx < s.length()){

            if(s.charAt(idx) == '('){
                idx++;
                String word_bracket = solution();
                word.append(word_bracket);
            }
            else if(s.charAt(idx) == ')'){
                word.reverse();
                idx++;
                return word.toString();
            }
            else{
                word.append(s.charAt(idx));
                idx++;
            }
        }
        return word.toString();
    }
}
