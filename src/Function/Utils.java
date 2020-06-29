package src.mua.Function;

import src.mua.Namespace.NameSpace;
import src.mua.OperatorStack.StackControl;
import src.mua.Tokenizer.Data;
import src.mua.Tokenizer.Tokenizer;
import src.mua.Tokenizer.Tokens;

import java.util.List;
import java.util.Vector;

public class Utils {
    private static boolean lastIsThing = false;

    public static int getFuncArgc(String funcName, NameSpace nameSpace) throws Exception {
        if(!nameSpace.isName(funcName))
            throw new Exception("[Error] " + funcName + " is not a function.");
        Data body = nameSpace.get(funcName);
        try {
            return body.getList().get(0).getList().size();
        } catch (Exception e) {
            throw new Exception("[Error] " + funcName + " is not a function.");
        }
    }

    public static Vector<Data> getArgName(String funcName, NameSpace nameSpace) throws Exception {
        if(!nameSpace.isName(funcName))
            throw new Exception("[Error] " + funcName + " is not a function.");
        Data body = nameSpace.get(funcName);
        try {
            return body.getList().get(0).getList();
        } catch (Exception e) {
            throw new Exception("[Error] " + funcName + " is not a function.");
        }
    }

    public static Vector<Data> getFuncBody(String funcName, NameSpace nameSpace) throws Exception {
        if(!nameSpace.isName(funcName))
            throw new Exception("[Error] " + funcName + " is not a function.");
        Data body = nameSpace.get(funcName);
        try {
            return body.getList().get(1).getList();
        } catch (Exception e) {
            throw new Exception("[Error] " + funcName + " is not a function.");
        }
    }

    public static Tokens getToken(Data data) {
        String val = data.toString();
        if(Tokens.isToken(val) && !val.equals("[")) {
            lastIsThing = val.equals("thing");
            return new Tokens(Tokens.getTokenByName(val), data);
        }
        else {
            if(data.getType() == Data.Type.WORD && data.getWordVal().charAt(0) != '\"' && !lastIsThing) {
                lastIsThing = false;
                return new Tokens(Tokens.Token.func, data);
            }
            lastIsThing = false;
            return new Tokens(Tokens.Token.value, data);
        }
    }

    public static class ListRunner {
        private Tokenizer tokenizer;
        private NameSpace nameSpace;
        private StackControl stack = null;

        public ListRunner(Tokenizer tknizer, NameSpace nSpace) {
            tokenizer = tknizer;
            nameSpace = nSpace;
            stack = new StackControl(tknizer, nameSpace);
        }

        public void execute(Vector<Data> argv) throws Exception {
            for(Data x:argv) {
                stack.push(Utils.getToken(x));
                if(nameSpace.shouldStop())
                    return ;
            }
        }

    }
}
