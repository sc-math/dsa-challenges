package leetcode.stack;

/*
150. Evaluate Reverse Polish Notation

Pattern: Stack / Expression Evaluation

Time: O(n)
Space: O(n)

Idea:
- percorrer os tokens da expressão
- números são empilhados
- operadores removem dois números da pilha
- aplicar operação e empilhar o resultado

Key trick:
a ordem importa para - e / (segundo pop é o primeiro operando)
*/

import java.util.Objects;
import java.util.Stack;

public class LC0150 {
    public int evalRPN(String[] tokens) {
        Stack<Integer> nums = new Stack<>();

        for(String token: tokens){
            if(Objects.equals(token, "+")){
                int num1 = nums.pop();
                int num2 = nums.pop();
                nums.push(num1+num2);
            }
            else if (Objects.equals(token, "-")){
                int num1 = nums.pop();
                int num2 = nums.pop();
                nums.push(num2-num1);
            }
            else if (Objects.equals(token, "*")){
                int num1 = nums.pop();
                int num2 = nums.pop();
                nums.push(num1*num2);
            }
            else if (Objects.equals(token, "/")){
                int num1 = nums.pop();
                int num2 = nums.pop();
                nums.push(num2/num1);
            }
            else{
                int num = Integer.parseInt(token);
                nums.push(num);
            }
        }

        return nums.pop();
    }
}
