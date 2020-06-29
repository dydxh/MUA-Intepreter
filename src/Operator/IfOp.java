package src.mua.Operator;

import src.mua.Function.Utils;
import src.mua.Namespace.NameSpace;
import src.mua.Tokenizer.Data;
import src.mua.Tokenizer.Tokenizer;

import java.util.Vector;

public final class IfOp extends Operation {
    public static Data execute(Vector<Data> argv, NameSpace nameSpace, Tokenizer tokenizer) throws Exception {
        boolean statement = argv.get(2).toBool();
        Vector<Data> body_true = argv.get(1).getList();
        Vector<Data> body_false = argv.get(0).getList();
        Utils.ListRunner runner = new Utils.ListRunner(tokenizer, nameSpace);
        if(statement)
            runner.execute(body_true);
        else
            runner.execute(body_false);
        return null;
    }
}
