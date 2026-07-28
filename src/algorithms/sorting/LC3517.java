package algorithms.sorting;

/*
3517. Smallest Palindromic Rearrangement I

Pattern: Counting Sort + Palindrome Half Mirroring

Time: O(n + 26)
Space: O(n)

Idea:
- como s ja e garantidamente um palindromo, a segunda metade tem o
  mesmo multiset de caracteres que a primeira metade (uma e o reverso
  da outra, reverter nao muda quais/quantos caracteres existem)
- entao so preciso: contar as letras da primeira metade (length/2),
  montar a menor sequencia possivel com essa contagem, e espelhar
  pra gerar a segunda metade
- counting sort (array de 26 posicoes, uma por letra) monta a menor
  sequencia sem comparar caractere com caractere: percorrendo o
  array de indice 0 a 25 (a ate z) e despejando cada letra count[i]
  vezes, a ordem alfabetica crescente emerge da propria estrutura
- c - 'a' -> converte caractere pra indice (contar)
  (char)('a' + i) -> converte indice de volta pra caractere (reconstruir)
- StringBuilder.append() evita concatenacao de String O(n^2) (String
  e imutavel, toda concatenacao recria a string inteira)
- se n for impar, sobra 1 caractere do meio que nao entra na metade

Key trick:
StringBuilder.reverse() já pronto pra gerar a metade espelhada, sem
precisar inverter caractere por caractere na mão
*/

public class LC3517 {
    public String smallestPalindrome(String s) {
        int[] countArr = new int[26];
        int length = s.length();

        for(int i = 0; i < (length/2); i++){
            char letter = s.charAt(i);
            countArr[letter - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 26; i++){

            for(int j = 0; j < countArr[i]; j++){
                char letra = (char)('a'+ i);
                sb.append(letra);
            }
        }
        String metade = sb.toString();
        String espelhada = sb.reverse().toString();

        return (length % 2 == 0) ?
                metade + espelhada :
                metade + s.charAt(length/2) + espelhada;
    }
}
