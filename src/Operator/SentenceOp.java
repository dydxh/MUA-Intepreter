package src.mua.Operator;

import src.mua.Tokenizer.Data;
import src.mua.Namespace.NameSpace;

import java.util.Vector;

public final class SentenceOp extends Operation {
    public static Data execute(Vector<Data> argv) throws Exception {
        Vector<Data> retval = new Vector<Data>();
        if(argv.get(1).isList()) {
            for(Data iter:argv.get(1).getList())
                retval.add(iter);
        }
        else {
            retval.add(argv.get(1));
        }
        if(argv.get(0).isList()) {
            for(Data iter:argv.get(0).getList())
                retval.add(iter);
        }
        else {
            retval.add(argv.get(0));
        }
        return new Data(retval);
    }
}
