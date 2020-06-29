package src.mua.Operator;

import src.mua.Tokenizer.Data;
import src.mua.Namespace.NameSpace;

import java.util.Vector;

public final class XorOp extends Operation {
    public static Data execute(Vector<Data> argv, NameSpace nameSpace) {
        return new Data(argv.get(1).toBool() ^ argv.get(0).toBool());
    }
}
