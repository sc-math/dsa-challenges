package leetcode.stack;

/*
224. Basic Calculator

Pattern: Stack / Expression Parsing

Time: O(n)
Space: O(n)

Idea:
- percorrer a expressão caractere por caractere
- construir números completos
- aplicar sinais (+ ou -)
- usar stack para salvar contexto quando encontrar '('

Key trick:
ao fechar ')', multiplicar o resultado interno pelo sinal anterior
e somar ao resultado anterior
*/

import java.util.*;

public class LC0224 {
    public int calculate(String s) {

        int size = s.length();
        int sign = 1;
        int ans = 0;
        int num = 0;
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < size; i++){
            if(Character.isDigit(s.charAt(i))){
                num = s.charAt(i) - '0';
                while(i + 1 < size && Character.isDigit(s.charAt(i+1))){
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                num *= sign;
                ans += num;
                num = 0;
                sign = 1;
            }
            else if(s.charAt(i) == '+'){
                sign = 1;
            }
            else if(s.charAt(i) == '-'){
                sign = -1;
            }
            else if(s.charAt(i) == '('){
                stack.push(ans);
                stack.push(sign);
                ans = 0;
                sign = 1;
            }
            else if(s.charAt(i) == ')'){
                int prevSign = stack.pop();
                ans = prevSign * ans;
                int precAns = stack.pop();
                ans = precAns + ans;
            }
        }
        return ans;
    }
}
