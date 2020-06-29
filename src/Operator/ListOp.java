package src.mua.Operator;

import src.mua.Tokenizer.Data;
import src.mua.Namespace.NameSpace;

import java.util.Vector;

public final class ListOp extends Operation {
    public static Data execute(Vector<Data> argv) throws Exception {
        Vector<Data> retval = new Vector<Data>();
        retval.add(argv.get(1));
        retval.add(argv.get(0));
        return new Data(retval);
    }
}
