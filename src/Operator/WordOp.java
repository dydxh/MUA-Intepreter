package src.mua.Operator;

import src.mua.Tokenizer.Data;
import src.mua.Namespace.NameSpace;

import java.util.Vector;

public final class WordOp extends Operation {
    public static Data execute(Vector<Data> argv) throws Exception {
        if(!argv.get(1).isWord()) {
            throw new Exception(argv.get(1).toString() + " is not a word.");
        }
        return new Data("\"" + argv.get(1).getWord() + argv.get(0).getWord());
    }
}
