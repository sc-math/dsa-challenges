package algorithms.stack;
/*
1614. Maximum Nesting Depth of the Parentheses

Pattern: Nesting Depth Counter (stack pattern sem stack literal)

Time: O(n)
Space: O(1)

Idea:
- contador simples: incrementa em '(', decrementa em ')'
- guarda o maior valor que o contador atingiu no caminho

Key trick:
- não precisa de Stack de verdade — só o valor da profundidade importa,
  não o conteúdo empilhado, então um int substitui a estrutura inteira

Insight:
mesma família do 1190/0224 (rastrear aninhamento), mas versão mais simples
onde só a profundidade máxima importa, não o conteúdo de cada nível
*/
public class LC1614 {
    public int maxDepth(String s) {
        int count = 0;
        int max_count = 0;
        for(char symbol:s.toCharArray()){
            if(symbol == '(')
                count++;
            else if(symbol == ')'){
                if(count > max_count)
                    max_count = count;
                count--;
            }
        }
        return max_count;
    }
}
