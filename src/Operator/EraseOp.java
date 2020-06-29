package src.mua.Operator;

import src.mua.Tokenizer.Data;
import src.mua.Namespace.NameSpace;

import java.util.Vector;

public final class EraseOp extends Operation {
    public static Data execute(Vector<Data> argv, NameSpace nameSpace) throws Exception {
        if(!Data.isName(argv.get(0)) || !nameSpace.isName(argv.get(0).getWord())) {
            throw new Exception(argv.toString() + " is not a name.");
        }
        nameSpace.remove(argv.get(0).getWord());
        return null;
    }
}
