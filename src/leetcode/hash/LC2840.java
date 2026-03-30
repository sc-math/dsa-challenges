package leetcode.hash;

/*
2840. Check if Strings Can be Made Equal With Operations II

Pattern: HashMap / Frequency Counting / String

Time: O(n)
Space: O(n)

Idea:
- operações só permitem trocar caracteres em índices com mesma paridade
- separar os caracteres de cada string em dois grupos:
  - índices pares
  - índices ímpares
- contar a frequência de cada grupo separadamente para s1 e s2
- comparar os mapas de frequência:
  - pares de s1 == pares de s2
  - ímpares de s1 == ímpares de s2

Key trick:
- tratar pares e ímpares como problemas independentes
- usar HashMap para contar frequência ao invés de ordenar

Edge cases:
- strings já iguais → retorna true
- caracteres repetidos → frequência garante validade
- tamanho mínimo (n = 1) → sempre válido

Insight:
esse problema é basicamente um "anagram check", mas separado por paridade,
já que as operações restringem as trocas a posições específicas
*/

import java.util.HashMap;

public class LC2840 {
    public boolean checkStrings(String s1, String s2) {
        HashMap<Character, Integer> even1 = new HashMap<>();
        HashMap<Character, Integer> odd1 = new HashMap<>();
        HashMap<Character, Integer> even2 = new HashMap<>();
        HashMap<Character, Integer> odd2 = new HashMap<>();

        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);

            if (i % 2 == 0) {
                even1.put(c1, even1.getOrDefault(c1, 0) + 1);
                even2.put(c2, even2.getOrDefault(c2, 0) + 1);
            } else {
                odd1.put(c1, odd1.getOrDefault(c1, 0) + 1);
                odd2.put(c2, odd2.getOrDefault(c2, 0) + 1);
            }
        }

        return even1.equals(even2) && odd1.equals(odd2);
    }
}
