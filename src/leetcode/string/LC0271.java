package leetcode.string;

/*
271. Encode and Decode Strings

Pattern: String Encoding / Serialization

Time: O(n)
Space: O(n)

Idea:
- codificar cada string como: length + '#' + string
- durante decode, ler o tamanho antes de '#'
- extrair a substring correspondente

Key trick:
usar length prefix para evitar conflitos com delimitadores
*/

import java.util.*;

public class LC0271 {

    static Map<Integer, String> map = new HashMap<>();

    public String encode(List<String> strs) {

        StringBuilder encoded = new StringBuilder();

        for(String str : strs){
            encoded.append(str.length());
            encoded.append("#");
            encoded.append(str);
        }

        return encoded.toString();
    }

    public List<String> decode(String s) {

        List<String> result = new ArrayList<>();

        int i = 0;

        while(i < s.length()){

            int j = i;

            while(s.charAt(j) != '#'){
                j++;
            }

            int length = Integer.parseInt(s.substring(i, j));

            String word = s.substring(j + 1, j + 1 + length);

            result.add(word);

            i = j + 1 + length;
        }

        return result;
    }
}