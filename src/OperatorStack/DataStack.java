package src.mua.OperatorStack;

import src.mua.Tokenizer.Data;

import java.util.Stack;

public class DataStack {
    private class DataItem {
        public Data data;
        public int idx;

        DataItem(Data val, int index) {
            idx = index;
            data = val;
        }
    }

    Stack<DataItem> stack;

    public DataStack() {
        stack = new Stack<DataItem>();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void push(Data val, int index) {
        stack.push(new DataItem(val, index));
    }
    public void push(Data val) {
        stack.push(new DataItem(val, 1));
    }

    public Data pop() {
        if(stack.isEmpty())
            return null;
        return stack.pop().data;
    }

    public int getTopIndex() {
        if(stack.isEmpty())
            return 0;
        return stack.peek().idx;
    }

    public Data getTopData() {
        if(stack.isEmpty())
            return null;
        return stack.peek().data;
    }
}
