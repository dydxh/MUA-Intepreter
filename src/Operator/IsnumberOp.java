package src.mua.Operator;

import src.mua.Namespace.NameSpace;
import src.mua.Tokenizer.Data;

import java.util.Vector;

public final class IsnumberOp extends Operation {
    public static Data execute(Vector<Data> argv) {
        return new Data(argv.get(0).getType() == Data.Type.NUMBER);
    }
}
