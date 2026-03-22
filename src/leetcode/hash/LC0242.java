package leetcode.hash;

/*
242. Valid Anagram

Pattern: Hash Table / Frequency Count

Time: O(n)
Space: O(1)

Idea:
- contar frequência das letras da primeira string
- subtrair frequência usando a segunda string
- se todas as contagens terminarem em 0, é um anagrama

Key trick:
usar array de tamanho 26 para mapear letras 'a' → 'z'
*/

import java.util.Arrays;

public class LC0242 {
    public boolean isAnagram(String s, String t) {

        int[] alphabet = new int[26];

        for(int i = 0; i < s.length(); i++){
            alphabet[s.charAt(i) - 'a']++;
        }

        for(int i = 0; i < t.length(); i++){
            alphabet[t.charAt(i) - 'a']--;
        }

        return Arrays.stream(alphabet).allMatch(n -> n == 0);

    }
}
