package src.mua.Operator;

import src.mua.Namespace.NameSpace;
import src.mua.Tokenizer.Data;

import java.util.Vector;

public final class LastOp extends Operation {
    public static Data execute(Vector<Data> argv) throws Exception {
        if(argv.get(0).isList())
            return argv.get(0).getList().get(argv.get(0).getList().size() - 1);
        return new Data(argv.get(0).toString().substring(argv.get(0).toString().length() - 1));
    }
}
