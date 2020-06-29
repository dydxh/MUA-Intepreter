package src.mua.OperatorStack;

import src.mua.Tokenizer.Tokens;

import java.util.Stack;

public class OpStack {
    private class OpItem {
        public Tokens.Token token;
        public int argc;

        OpItem(Tokens.Token val, int limit) {
            argc = limit;
            token = val;
        }
    }

    Stack<OpItem> stack;

    public OpStack() {
        stack = new Stack<OpItem>();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void push(Tokens.Token val, int count) {
        stack.push(new OpItem(val, count));
    }
    public void push(Tokens.Token val) {
        stack.push(new OpItem(val, 1));
    }

    public Tokens.Token pop() {
        if(stack.isEmpty())
            return null;
        return stack.pop().token;
    }

    public int getTopArgc() {
        if(stack.isEmpty())
            return -1;
        return stack.peek().argc;
    }

    public Tokens.Token getTopToken() {
        if(stack.isEmpty())
            return null;
        return stack.peek().token;
    }
}
