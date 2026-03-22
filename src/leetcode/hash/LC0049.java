package leetcode.hash;

/*
49. Group Anagrams

Pattern: HashMap / Grouping by canonical form

Time: O(n * k log k)
Space: O(n * k)

Idea:
- duas palavras são anagramas se a versão ordenada delas for igual
- ordenar cada palavra para gerar uma "assinatura"
- usar a assinatura como chave em um HashMap
- agrupar todas as palavras com mesma assinatura

Key trick:
usar a palavra ordenada como chave para agrupar os anagramas
*/

import java.util.*;

public class LC0049 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {

            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String str_sort = String.copyValueOf(charArray);

            List<String> strings;

            if (map.containsKey(str_sort))
                strings = map.get(str_sort);
            else
                strings = new ArrayList<>();

            strings.add(str);
            map.put(str_sort, strings);
        }

        return new ArrayList<>(map.values());
    }
}
