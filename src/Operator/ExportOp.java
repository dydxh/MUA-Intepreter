package src.mua.Operator;

import src.mua.Namespace.NameSpace;
import src.mua.Tokenizer.Data;

import java.util.Vector;

public final class ExportOp extends Operation {
    public static Data execute(Vector<Data> argv, NameSpace nameSpace) throws Exception {
        if(!Data.isName(argv.get(0)) || !nameSpace.isName(argv.get(0).getWord())) {
            throw new Exception(argv.toString() + " is not a name.");
        }
        nameSpace.addGlobal(argv.get(0).getWord(), nameSpace.get(argv.get(0).getWord()));
        return null;
    }
}
