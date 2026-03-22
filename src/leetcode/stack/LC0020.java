package leetcode.stack;

/*
20. Valid Parentheses

Pattern: Stack

Time: O(n)
Space: O(n)

Idea:
- percorrer a string
- empilhar parênteses de abertura
- quando aparecer um de fechamento, verificar se corresponde ao topo da stack

Key trick:
usar a stack para garantir que os fechamentos aconteçam na ordem correta
*/

import java.util.Stack;

public class LC0020 {
    public boolean isValid(String s){

        Stack<Character> stack = new Stack<>();

        for(char c: s.toCharArray()){
            if(c == '{' || c == '[' || c == '(')
                stack.add(c);
            else if (!stack.isEmpty()){
                char d = stack.peek();
                if(d == '(' && c == ')')
                    stack.pop();
                else if(d == '[' && c == ']')
                    stack.pop();
                else if(d == '{' && c == '}')
                    stack.pop();
                else {
                    return false;
                }
            }
            else {
                return false;
            }
        }

        return stack.isEmpty();
    }
}
