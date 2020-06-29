package src.mua.OperatorStack;

import src.mua.Function.Executor;
import src.mua.Namespace.NameSpace;
import src.mua.Operator.OperatorHandler;
import src.mua.Tokenizer.Data;
import src.mua.Tokenizer.Tokens;
import src.mua.Tokenizer.Tokenizer;
import src.mua.Function.Utils;

import java.util.Stack;
import java.util.Vector;

public class StackControl {
    DataStack dataStack;
    OpStack opStack;
    NameSpace nameSpace;
    Tokenizer tokenizer;

    private enum State {
        Func, Expr
    }
    Stack<State> curState;

    public StackControl(Tokenizer tknizer) {
        dataStack = new DataStack();
        opStack = new OpStack();
        nameSpace = new NameSpace();
        tokenizer = tknizer;

        curState = new Stack<>();
        curState.push(State.Func);
    }

    public StackControl(Tokenizer tknizer, NameSpace nSpace) {
        dataStack = new DataStack();
        opStack = new OpStack();
        nameSpace = nSpace;
        tokenizer = tknizer;

        curState = new Stack<>();
        curState.push(State.Func);
    }

    public void push(Tokens item) throws Exception {
        dataStack.push(item.value, 1);
        if(OperatorHandler.OpArgc.containsKey(item.token.name())) {
            opStack.push(item.token, OperatorHandler.OpArgc.get(item.token.name()));
        } // Add local function procedure
        else if (item.token == Tokens.Token.func) {
            int argc = Utils.getFuncArgc(item.value.getWord(), nameSpace);
            if(argc == -1)
                throw new Exception("[Error] function " + item.value.getWord() + " doesn't exist.");
            opStack.push(item.token, argc + 1);
            curState.push(State.Func);
        }
        else if(item.token == Tokens.Token.lpar) {
            opStack.push(item.token, 0);
            curState.push(State.Expr);
        }
        else if(item.token == Tokens.Token.rpar) {
            dataStack.pop();
            int argc = 0;
            Vector<Data> argv = new Vector<Data>();
            Data tmp = dataStack.pop();
            while(!tmp.toString().equals("(")) {
                argc += 1;
                argv.add(tmp);
                tmp = dataStack.pop();
            }
            while(opStack.getTopToken() != Tokens.Token.lpar)
                opStack.pop();
            opStack.pop();
//            double result = 0;
            double result = ExprStack.CalcExpr(argc, argv);
            dataStack.push(new Data(result), 1);
            opStack.push(Tokens.Token.value, 1);
            curState.pop();
        }
        else if(item.token == Tokens.Token.subop) {
            if(curState.peek() == State.Func)
                opStack.push(Tokens.Token.subop, 2); // negtive number
            else
                opStack.push(Tokens.Token.subop, 0); // operator sub
        }
        else {
            opStack.push(item.token);
        }
        while(satisfy()) {
            int argc = opStack.getTopArgc();
            Tokens.Token opName = opStack.pop();
            Vector<Data> argv = new Vector<Data>();
            for(int i = 0; i < argc; i++) {
                argv.add(dataStack.pop());
            }

            if(opName == Tokens.Token.func) {
                Executor executor = new Executor(tokenizer, nameSpace);
//                System.out.println(argv);
                executor.execute(argv);
                if(executor.getNameSpace().shouldStop())
                    executor.getNameSpace().unsetStop();
                if(executor.getNameSpace().hasReturnValue()) {
                    dataStack.push(executor.getNameSpace().getRetval(), dataStack.getTopIndex() + 1);
                    executor.getNameSpace().unsetReturnValue();
                }
                curState.pop();
            }
            else {
                OperatorHandler opHandler = new OperatorHandler(opName.name(), argv, nameSpace, tokenizer);
                Data retval = opHandler.execute();
                if(nameSpace.shouldStop())
                    return ;
                else if(opHandler.isHasReturnValue())
                    dataStack.push(retval, dataStack.getTopIndex() + 1);
            }
        }

        while(opStack.isEmpty() && !dataStack.isEmpty()) {
            System.out.println(dataStack.pop().toString());
        }
//        System.out.println("============== wow ==============");
    }

    private boolean satisfy() {
        if(opStack.isEmpty() || dataStack.isEmpty())
            return false;
        return dataStack.getTopIndex() == opStack.getTopArgc();
    }


}
