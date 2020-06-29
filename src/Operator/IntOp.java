package src.mua.Operator;

import src.mua.Tokenizer.Data;
import src.mua.Namespace.NameSpace;

import java.util.Random;
import java.util.Vector;

public final class IntOp extends Operation {
    public static Data execute(Vector<Data> argv) {
        return new Data(Math.floor(argv.get(0).toDouble()));
    }
}
