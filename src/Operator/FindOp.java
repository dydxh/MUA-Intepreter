package src.mua.Operator;

import src.mua.Tokenizer.Data;
import src.mua.Namespace.NameSpace;

import java.util.Vector;

public final class FindOp extends Operation {
    public static Data execute(Vector<Data> argv) {
        Vector<Data> list = argv.get(1).getList();
        Data val = argv.get(0);
        for(Data iter: list) {
            if(val.equals(iter))
                return new Data(true);
        }
        return new Data(false);
    }
}
