package src.mua.Operator;

import src.mua.Tokenizer.Data;
import src.mua.Namespace.NameSpace;

import java.io.*;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

public final class LoadOp extends Operation {
    public static Data execute(Vector<Data> argv, NameSpace nameSpace) throws Exception {
        String filename = argv.get(0).getWord();
        //if(filename.equals("b.mua")) { // The document is not clear enough
        //    System.out.println("bbbb");
        //    return null;
        //} 
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(filename)));
        HashMap<String, Data> savedScope = (HashMap<String, Data>)ois.readObject();
        ois.close();

        nameSpace.scope.putAll(savedScope);
        return null;
    }
}
