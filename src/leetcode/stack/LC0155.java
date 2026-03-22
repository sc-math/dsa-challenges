package leetcode.stack;

/*
155. Min Stack

Pattern: Stack / Design Data Structure

Time: O(1) for all operations
Space: O(n)

Idea:
- usar duas pilhas
- uma para os valores
- outra para armazenar os mínimos históricos

Key trick:
quando um novo valor é <= mínimo atual, empilhar também na minStack
*/

import java.util.Stack;

public class LC0155 {
    Stack<Integer> stack;
    Stack<Integer> minStack;

    public LC0155() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if(minStack.isEmpty() || val <= minStack.peek()){
            minStack.push(val);
        }
    }

    public void pop() {
        int val = stack.pop();
        if(val == minStack.peek()){
            minStack.pop();
        }

    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
