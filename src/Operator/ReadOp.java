package src.mua.Operator;

import src.mua.Tokenizer.Data;
import src.mua.Tokenizer.Tokens;
import src.mua.Namespace.NameSpace;
import src.mua.Tokenizer.Tokenizer;

import java.util.Vector;

public final class ReadOp extends Operation {
    public static Data execute(Vector<Data> argv, NameSpace nameSpace, Tokenizer tokenizer) throws Exception {
        Tokens token = tokenizer.getToken();
        if(token.token != Tokens.Token.value) {
            throw new Exception("[Read Error]: Except number or word.");
        }
        if(token.value.getType() != Data.Type.WORD && token.value.getType() != Data.Type.NUMBER) {
            throw new Exception("[Read Error]: Except number or word.");
        }
        return token.value;
    }
}
