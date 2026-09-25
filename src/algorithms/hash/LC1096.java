package algorithms.hash;

/*
1096. Brace Expansion II

Pattern: Recursive Descent Parser / HashSet / Cartesian Product

Time: O(n + total output size), onde n = expression.length()
      - percorrer os caracteres é O(n)
      - o cartesian product em cada nível pode gerar até O(|a| * |b|) combinações,
        então o custo real é dominado pelo tamanho dos sets intermediários/resultado,
        não só pelo tamanho da string de entrada
Space: O(n) pra profundidade da recursão (pilha) + O(total output size) pros sets
       (cada nível cria HashSets novos, mas eles saem de escopo e viram lixo
       assim que a chamada retorna, então não acumula ao longo da execução)

Idea:
- expressão é uma gramática pequena com 2 operações: união (,) e concatenação (justaposição)
- parser recursivo descendente: cada chamada de parse() processa um nível de aninhamento,
  usando idx como cursor compartilhado (atributo da classe) entre a chamada de fora e as recursivas
- concatenação = produto cartesiano de dois Set<String>, começando do elemento neutro {""}
- união (vírgula) = Set.addAll, nunca produto cartesiano — são termos fechados, não devem se misturar
- duas variáveis locais por nível: saved (termo atual em construção, via produto cartesiano)
  e union (termos já fechados por vírgula, acumulados via addAll)

Key trick:
- separar "o que ainda tá em construção" (saved) de "o que já foi fechado" (union) —
  evita multiplicar por engano um termo que já devia estar intocável
- idx como estado compartilhado (não passado por parâmetro) é o que permite a recursão
  "avançar a leitura" pro nível de fora sem precisar devolver posição explicitamente
- elemento neutro de cada operação: {""} pra concatenação (produto), conjunto vazio pra união (soma)

Edge cases:
- sequência de letras sem chave nenhuma (ex: "abc") → nunca entra no branch de } nem de ,,
  precisa de flush do seq fora do while, no fallthrough final
- letras logo antes de abrir uma chave (ex: "a{b,c}") → precisam ser "descarregadas" via
  cartesian product em saved antes de recursar, senão a recursão relê o mesmo caractere
- vírgula dentro de bloco aninhado não deve vazar pro union do nível de fora (isolamento por
  chamada recursiva própria resolve isso automaticamente)

Insight:
parecido com "Decode String" / "Basic Calculator" no uso de índice compartilhado + recursão,
mas em vez de acumular um número/string, acumula um Set<String> — a operação de "concatenar"
vira produto cartesiano e "separar por vírgula" vira união de conjuntos
*/

import java.util.*;

public class LC1096 {
    private String expr;
    private int idx;

    public List<String> braceExpansionII(String expression) {
        this.expr = expression;
        this.idx = 0;
        Set<String> result = parse();

        List<String> list = new ArrayList<>(result);
        Collections.sort(list);
        return list;
    }

    private Set<String> parse(){
        Set<String> union = new HashSet<>();
        Set<String> saved = new HashSet<>();
        saved.add("");

        StringBuilder seq = new StringBuilder();

        while (idx < expr.length()) {
            if(expr.charAt(idx) == '{'){
                saved = cartesianProduct(saved, Set.of(seq.toString()));
                seq.setLength(0);

                idx++;
                Set<String> bloco = parse();
                saved = cartesianProduct(saved, bloco);
            }
            else if(expr.charAt(idx) == '}'){
                saved = cartesianProduct(saved, Set.of(seq.toString()));
                union.addAll(saved);

                idx++;
                return union;
            }
            else if (expr.charAt(idx) == ','){
                saved = cartesianProduct(saved, Set.of(seq.toString()));
                seq.setLength(0);

                union.addAll(saved);
                saved.clear();
                saved.add("");

                idx++;
            }
            else{
                seq.append(expr.charAt(idx));
                idx++;
            }
        }
        return cartesianProduct(saved, Set.of(seq.toString()));
    }

    private Set<String> cartesianProduct(Set<String> a, Set<String> b){
        Set<String> result = new HashSet<>();
        for(String word1 : a){
            for(String word2: b){
                result.add(word1 + word2);
            }
        }
        return result;
    }
}