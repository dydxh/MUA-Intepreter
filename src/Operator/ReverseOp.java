package src.mua.Operator;

import src.mua.Namespace.NameSpace;
import src.mua.Tokenizer.Data;

import java.util.Vector;

public final class ReverseOp extends Operation {
    public static Data execute(Vector<Data> argv) throws Exception {
        if(argv.get(0).isList()) {
            Vector<Data> tmp = new Vector<Data>();
            Vector<Data> args = argv.get(0).getList();
            for(int i = args.size() - 1; i >= 0; i--)
                tmp.add(args.get(i));
            return new Data(tmp);
        }
        throw new Exception("Reverse argument must be a list.");
    }
}
