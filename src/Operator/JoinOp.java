package src.mua.Operator;

import src.mua.Tokenizer.Data;
import src.mua.Namespace.NameSpace;

import java.util.Vector;

public final class JoinOp extends Operation {
    public static Data execute(Vector<Data> argv) throws Exception {
        if(!argv.get(1).isList()) {
            throw new Exception("Join Error.");
        }
        Vector<Data> retval = new Vector<Data>(argv.get(1).getList());
        retval.add(argv.get(0));
        return new Data(retval);
    }
}
