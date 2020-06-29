package src.mua.Operator;

import src.mua.Tokenizer.Data;
import src.mua.Namespace.NameSpace;

import java.util.Vector;

public final class PrintOp extends Operation {
    public static Data execute(Vector<Data> argv, NameSpace nameSpace) {
        System.out.println(argv.get(0).toString());
//        System.out.println(argv.get(0));
        return null;
    }
}
