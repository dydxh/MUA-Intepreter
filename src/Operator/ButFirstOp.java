package src.mua.Operator;

import src.mua.Namespace.NameSpace;
import src.mua.Tokenizer.Data;

import java.util.Vector;

public final class ButFirstOp extends Operation {
    public static Data execute(Vector<Data> argv) throws Exception {
        if(argv.get(0).isList()) {
            Vector<Data> tmp = new Vector<Data>(argv.get(0).getList());
            tmp.remove(0);
            return new Data(tmp);
        }
        return new Data(argv.get(0).toString().substring(1));
    }
}
