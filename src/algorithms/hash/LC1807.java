package algorithms.hash;
/*
1807. Evaluate the Bracket Pairs of a String

Pattern: HashMap Lookup / String Parsing (single pass)

Time: O(n + m), onde n = s.length() e m = soma dos tamanhos das entradas de knowledge
      - construir o HashMap é O(m)
      - percorrer s uma única vez é O(n)
Space: O(m) pro HashMap + O(n) pro StringBuilder do resultado

Idea:
- pré-processar knowledge num Map<String,String> pra lookup O(1) de chave->valor
- uma única varredura da string s com StringBuilder:
  - fora de parênteses: caractere vai direto pro resultado
  - dentro de parênteses: caractere vai se acumulando numa chave (StringBuilder separado)
  - ao fechar ')': procura a chave no map, adiciona o valor (ou "?" se não souber) no resultado

Key trick:
- usar uma flag (key_flag) pra saber se está "dentro" ou "fora" de um par de parênteses,
  e checar essa flag ANTES de checar o símbolo — ordem do if/else-if importa aqui,
  senão o ')' de fechamento acaba sendo tratado como "mais uma letra da chave"
- sem colchetes aninhados (garantido pelo enunciado), então não precisa de pilha/contador
  de profundidade, só a flag booleana resolve

Edge cases:
- chave desconhecida (não existe no knowledge) → substitui por "?"
- mesma chave aparecendo várias vezes → cada ocorrência é resolvida independentemente
  (o HashMap responde igual toda vez, sem problema)
- caracteres fora de qualquer parênteses não devem ser tocados, mesmo que coincidam
  com o texto de alguma chave (ex: "aaa" solto, sem parênteses, fica como está)

Insight:
mais simples que parsers com aninhamento (tipo o 1096) porque a ausência de brackets
aninhados elimina a necessidade de recursão ou pilha — uma flag booleana já basta
pra saber "onde" o cursor está
*/

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC1807 {
    public String evaluate(String s, List<List<String>> knowledge) {

        Map<String, String> knowledge_map = new HashMap<>();
        for(List<String> item : knowledge){
            knowledge_map.put(item.get(0), item.get(1));
        }

        StringBuilder result = new StringBuilder();
        StringBuilder key = new StringBuilder();
        boolean key_flag = false;
        for(char symbol : s.toCharArray()){
            if(symbol == '('){
                key_flag = true;
            }
            else if(key_flag && symbol != ')'){
                key.append(symbol);
            }
            else if(symbol == ')'){
                key_flag = false;

                result.append(knowledge_map.getOrDefault(key.toString(), "?"));
                key.setLength(0);
            }
            else {
                result.append(symbol);
            }
        }

        return result.toString();
    }
}
