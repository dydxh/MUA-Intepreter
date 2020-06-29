package src.mua.Operator;

import src.mua.Tokenizer.Data;
import src.mua.Namespace.NameSpace;

import java.util.Random;
import java.util.Vector;

public final class WaitOp extends Operation {
    public static Data execute(Vector<Data> argv) throws Exception {
        Thread.sleep((int)(argv.get(0).toDouble()));
        return null;
    }
}
