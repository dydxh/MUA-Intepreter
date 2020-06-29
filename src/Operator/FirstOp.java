package src.mua.Operator;

import src.mua.Namespace.NameSpace;
import src.mua.Tokenizer.Data;

import java.util.Vector;

public final class FirstOp extends Operation {
    public static Data execute(Vector<Data> argv) throws Exception {
        if(argv.get(0).isList())
            return argv.get(0).getList().get(0);
        return new Data(argv.get(0).toString().substring(0, 1));
    }
}
