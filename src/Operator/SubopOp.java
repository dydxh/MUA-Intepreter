package src.mua.Operator;

import src.mua.Tokenizer.Data;
import src.mua.Namespace.NameSpace;

import java.util.Vector;

public final class SubopOp extends Operation {
    public static Data execute(Vector<Data> argv) throws Exception {
        return new Data(-argv.get(0).toDouble());
    }
}
