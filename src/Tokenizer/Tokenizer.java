package src.mua.Tokenizer;

import java.io.ByteArrayInputStream;
import java.util.Vector;

public class Tokenizer {
    InputControl inControl;
    boolean lastIsThing;

    public Tokenizer() {
        inControl = new InputControl();
        lastIsThing = false;
    }
    public Tokenizer(String val) {
        inControl = new InputControl(new ByteArrayInputStream(val.getBytes()));
        lastIsThing = false;
    }

    public Tokens getToken() throws Exception {
        String val = inControl.getStringItem();
        if(Tokens.isToken(val) && !val.equals("[")) {
            lastIsThing = val.equals("thing");
            return new Tokens(Tokens.getTokenByName(val), new Data(val));
        }
        else {
            if(val.charAt(0) == '"') {
                lastIsThing = false;
                return new Tokens(Tokens.Token.value, new Data(val));
            }
            else if(val.equals("true") || val.equals("false")) {
                lastIsThing = false;
                return new Tokens(Tokens.Token.value, new Data(val.equals("true")));
            }
            else if(val.equals("[")) {
                Vector<Data> tmpval = new Vector<Data>();
                Tokens tmptoken = getToken();
                while(tmptoken.token != Tokens.Token.rbrac) {
                    tmpval.add(tmptoken.value);
                    tmptoken = getToken();
                }
                lastIsThing = false;
                return new Tokens(Tokens.Token.value, new Data(tmpval));
            }
            else if(lastIsThing) {
                lastIsThing = false;
                return new Tokens(Tokens.Token.value, new Data(val));
            }
            else {
                lastIsThing = false;
                try {
                    return new Tokens(Tokens.Token.value, new Data(processExpr(val)));
                } catch (Exception e) {
                    return new Tokens(Tokens.Token.func, new Data(val));
                }
            }
        }
    }

    private double processExpr(String val) throws Exception { // Process + - * / later
        if(Data.isNumeric(val))
            return Double.parseDouble(val);
        else {
            throw new Exception("Unknow value: " + val);
        }
    }
}
