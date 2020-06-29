package src.mua.Operator;

import src.mua.Tokenizer.Data;
import src.mua.Namespace.NameSpace;

import java.util.Vector;

public final class PoallOp extends Operation {
    public static Data execute(Vector<Data> argv, NameSpace nameSpace) {
        nameSpace.showAll();
        return null;
    }
}
