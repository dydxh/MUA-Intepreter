package src.mua.Operator;

import src.mua.Function.Utils;
import src.mua.Namespace.NameSpace;
import src.mua.Tokenizer.Data;
import src.mua.Tokenizer.Tokenizer;

import java.util.Vector;

public final class RepeatOp extends Operation {
    public static Data execute(Vector<Data> argv, NameSpace nameSpace, Tokenizer tokenizer) throws Exception {
        int rep_cnt = (int)argv.get(1).getNumber();
        Vector<Data> body = argv.get(0).getList();
        Utils.ListRunner runner = new Utils.ListRunner(tokenizer, nameSpace);
        for(int i = 0; i < rep_cnt; i++) {
            runner.execute(body);
            if(nameSpace.shouldStop())
                return null;
        }
        return null;
    }
}
