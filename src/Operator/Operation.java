package src.mua.Operator;

import src.mua.Tokenizer.Data;
import src.mua.Namespace.NameSpace;
import src.mua.Tokenizer.Tokenizer;

import java.util.Vector;

public abstract class Operation {
//    private Vector<Data> argv;

    public static Data execute(Vector<Data> argv) throws Exception {
        return null;
    }
    public static Data execute(Vector<Data> argv, NameSpace nameSpace) throws Exception {
        return null;
    }
    public static Data execute(Vector<Data> argv, NameSpace nameSpace, Tokenizer tokenizer) throws Exception {
        return null;
    }
}
