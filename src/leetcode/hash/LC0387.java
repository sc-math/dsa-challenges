package leetcode.hash;

/*
387. First Unique Character in a String

Pattern: Hash Table / Frequency Count

Time: O(n)
Space: O(1)

Idea:
- contar frequência de cada caractere
- percorrer a string novamente
- retornar o primeiro índice cuja frequência é 1

Key trick:
usar contagem de frequência para identificar caracteres únicos
*/

import java.util.*;

public class LC0387 {
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();

        for(int i = 0; i < s.length(); i++){
            if(map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), map.get(s.charAt(i))+1);
            }
            else{
                map.put(s.charAt(i), 1);
            }
        }

        System.out.println(map.values());

        for(int i = 0; i < s.length(); i++){
            if(map.get(s.charAt(i)) == 1){
                return i;
            }
        }

        return -1;
    }
}
