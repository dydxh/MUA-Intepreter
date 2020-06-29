package src.mua.Operator;

import src.mua.Tokenizer.Data;
import src.mua.Namespace.NameSpace;

import java.util.Random;
import java.util.Vector;

public final class RandomOp extends Operation {
    public static Data execute(Vector<Data> argv) {
        Random tmp = new Random();
        return new Data(tmp.nextDouble() * argv.get(0).toDouble());
    }
}
