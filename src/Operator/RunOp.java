package src.mua.Operator;

import src.mua.Function.Utils;
import src.mua.Namespace.NameSpace;
import src.mua.Tokenizer.Data;
import src.mua.Tokenizer.Tokenizer;

import java.util.Vector;

public final class RunOp extends Operation {
    public static Data execute(Vector<Data> argv, NameSpace nameSpace, Tokenizer tokenizer) throws Exception {
        Utils.ListRunner runner = new Utils.ListRunner(tokenizer, nameSpace);
        runner.execute(argv.get(0).getList());
        return null;
    }
}
