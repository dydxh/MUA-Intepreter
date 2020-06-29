package src.mua.Operator;

import src.mua.Tokenizer.Data;
import src.mua.Namespace.NameSpace;

import java.util.Vector;

public final class IsNameOp extends Operation {
    public static Data execute(Vector<Data> argv, NameSpace nameSpace) {
        return new Data(Data.isName(argv.get(0)) && nameSpace.isName(argv.get(0).getWord()));
    }
}
