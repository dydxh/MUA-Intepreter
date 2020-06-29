package src.mua.Operator;

import src.mua.Tokenizer.Data;
import src.mua.Namespace.NameSpace;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

public final class SaveOp extends Operation {
    public static Data execute(Vector<Data> argv, NameSpace nameSpace) throws Exception {
        String filename = argv.get(0).getWord();
        HashMap<String, Data> curScope = nameSpace.scope;
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(filename)));
        oos.writeObject(curScope);
        oos.close();
        return null;
    }
}
