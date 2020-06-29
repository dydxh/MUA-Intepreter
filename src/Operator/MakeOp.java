package src.mua.Operator;

import src.mua.Tokenizer.Data;
import src.mua.Namespace.NameSpace;

import java.util.Vector;

public final class MakeOp extends Operation {
    public static Data execute(Vector<Data> argv, NameSpace nameSpace) throws Exception {
        if(!Data.isName(argv.get(1))) {
            throw new Exception(argv.toString() + " is not a name.");
        }
        nameSpace.add(argv.get(1).getWord(), argv.get(0));
        return null;
    }
}
