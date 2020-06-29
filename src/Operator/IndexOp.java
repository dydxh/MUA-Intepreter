package src.mua.Operator;

import src.mua.Tokenizer.Data;
import src.mua.Namespace.NameSpace;

import java.util.Vector;

public final class IndexOp extends Operation {
    public static Data execute(Vector<Data> argv) {
        Vector<Data> list = argv.get(1).getList();
        Data val = argv.get(0);
        for(int i = 0; i < list.size(); i++) {
            if(val.equals(list.get(i)))
                return new Data(i);
        }
        return new Data(-1);
    }
}
