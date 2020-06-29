package src.mua.Tokenizer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Vector;

public class Data implements Serializable {
    public enum Type {
        UNDEFINED, NUMBER, WORD, LIST, BOOL
    }

    private Type type = Type.UNDEFINED;
    private double numVal;
    private String wordVal;
    private Vector<Data> listVal;
    private boolean boolVal;

    public Data(double val) {
        type = Type.NUMBER;
        this.numVal = val;
    }
    public Data(String val) {
        type = Type.WORD;
        this.wordVal = val;
    }
    public Data(Vector<Data> val) {
        type = Type.LIST;
        this.listVal = val;
    }
    public Data(boolean val) {
        type = Type.BOOL;
        this.boolVal = val;
    }

    Data(Data val) {
        if(val == null) {
            return ;
        }
        type = val.type;
        numVal = val.numVal;
        wordVal = val.wordVal;
        listVal = val.listVal; // maybe dangerous
        boolVal = val.boolVal;
    }

    public Type getType() {
        return type;
    }
    public String getTypeName() {
        return type.name().toLowerCase();
    }
    public double getNumber() {
        if(type != Type.NUMBER) {
            return 0;
        }
        return numVal;
    }
    public String getWordVal() {
        return wordVal;
    }
    public String getWord() {
        if(type != Type.WORD) {
            return "";
        }
        if(wordVal.charAt(0) == '\"')
            return wordVal.substring(1);
        return wordVal;
    }
    public Vector<Data> getList() {
        if(type != Type.LIST) {
            return null;
        }
        return listVal;
    }
    public boolean getBool() {
        if(type != Type.BOOL) {
            return false;
        }
        return boolVal;
    }
    public boolean append(Data val) {
        if(type != Type.LIST || listVal == null) {
            return false;
        }
        if(val == null) {
            return false;
        }
        return listVal.add(val);
    }

    public double toDouble() {
        switch(type) {
            case BOOL:
                return boolVal ? 1.0 : 0.0;
            case NUMBER:
                return numVal;
            case WORD:
                if(isNumeric(getWord()))
                    return Double.parseDouble(getWord());
            case LIST:
            default:
                return 0.0;
        }
    }

    public int toInt() {
        switch(type) {
            case BOOL:
                return boolVal ? 1 : 0;
            case NUMBER:
                return (int)numVal;
            case WORD:
                if(isInteger(getWord()))
                    return Integer.parseInt(getWord());
            case LIST:
            default:
                return 0;
        }
    }

    public boolean toBool() {
        switch(type) {
            case BOOL:
                return boolVal;
            case NUMBER:
                return numVal != 0;
            case WORD:
                return !wordVal.equals("");
            case LIST:
                return !listVal.isEmpty();
            default:
                return true;
        }
    }

    public String toString() {
        switch(type) {
            case BOOL:
                return boolVal ? "true" : "false";
            case NUMBER:
                if(Math.abs((int)numVal - numVal) < 1e-6)
                    return Integer.toString((int)numVal);
                return Double.toString(numVal);
            case WORD:
                return getWord();
            case LIST:
                return listString(list2String().toString());
            default:
                return "undefined";
        }
    }

    public String listString(String val) {
        return val.substring(1, val.length() - 1);
    }

    public boolean isEmpty() {
        switch(type) {
            case BOOL:
                return boolVal == false;
            case NUMBER:
                return numVal == 0;
            case WORD:
                return wordVal.equals("");
            case LIST:
                return listVal.size() == 0;
            default:
                return true;
        }
    }

    public boolean isWord() {
        return type == Type.WORD;
    }

    public boolean isList() {
        return type == Type.LIST;
    }

    private String repr() {
        switch(type) {
            case BOOL:
                return boolVal ? "true" : "false";
            case NUMBER:
                if(Math.abs((int)numVal - numVal) < 1e-6)
                    return Integer.toString((int)numVal);
                return Double.toString(numVal);
            case WORD:
                return getWord();
            case LIST:
                return list2String().toString();
            default:
                return "undefined";
        }
    }

    private StringBuffer list2String() {
        StringBuffer val = new StringBuffer("[");
        int len = listVal.size();
        int i = 0;
        for(Data item: listVal) {
            i += 1;
            val.append(item.repr());
            if(i != len)
                val.append(" ");
        }
        val.append("]");
        return val;
    }

    public boolean equals(Object val) {
        if(this == val)
            return true;
        if(val == null || val.getClass() != getClass()) {
            return false;
        }
        Data tmpval = (Data)val;
        if(tmpval.type != type) {
            return false;
        }
        switch (type) {
            case NUMBER:
                return numVal == tmpval.numVal;
            case WORD:
                return wordVal.equals(tmpval.wordVal);
            case LIST:
                return listVal.equals(tmpval.listVal);
            case BOOL:
                return boolVal == tmpval.boolVal;
            default:
                return true; // undefined equals to undefined
        }
    }

    public static boolean isName(Data val) {
        if(val == null || val.type != Type.WORD)
            return false;
        if(val.wordVal.equals(""))
            return false;
        if(val.wordVal.charAt(0) == '\"' || Character.isLetter(val.wordVal.charAt(0))) {
            for(int i = 1; i < val.wordVal.length(); i++) {
                if(val.wordVal.charAt(i) == '_' || Character.isLetterOrDigit(val.wordVal.charAt(i)))
                    continue;
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean isInteger(String val) {
        try {
            new BigInteger(val).toString();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isNumeric(String val) {
        try {
            new BigDecimal(val).toString();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
