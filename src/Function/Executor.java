package src.mua.Function;

import src.mua.Namespace.NameSpace;
import src.mua.OperatorStack.StackControl;
import src.mua.Tokenizer.Data;
import src.mua.Tokenizer.Tokenizer;
import src.mua.Tokenizer.Tokens;

import javax.naming.Name;
import java.util.Vector;

public class Executor {
    private Tokenizer tokenizer;
    private NameSpace nameSpace;
    private StackControl stack = null;

    public Executor(Tokenizer tknizer, NameSpace nSpace) {
        tokenizer = tknizer;
        nameSpace = new NameSpace("wow", nSpace);
        stack = new StackControl(tknizer, nameSpace);
    }

    public NameSpace getNameSpace() {
        return nameSpace;
    }

    public void BindArguments(Vector<Data> names, Vector<Data> argv) {
        int argc = names.size();
        for(int i = 0; i < argc; i++)
            nameSpace.add(names.get(i).toString(), argv.get(argc - 1 - i));
    }

    public void execute(Vector<Data> argv) throws Exception {
        String funcName = argv.lastElement().toString();
        BindArguments(Utils.getArgName(funcName, nameSpace), argv);

        Vector<Data> body = Utils.getFuncBody(funcName, nameSpace);
        for(Data x:body) {
            stack.push(Utils.getToken(x));
            if(nameSpace.shouldStop())
                return ;
        }
    }
}
