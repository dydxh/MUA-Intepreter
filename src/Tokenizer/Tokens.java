package src.mua.Tokenizer;

import java.util.HashMap;

public class Tokens {
    public enum Token {
        value, func,
        add, sub, mod, div, mul,
        and, or, xor, not,
        gt, lt, eq,
        make, thing, erase, isname, print, read,
        repeat, iff, export, output, stop, readlist,
        isword, isnumber, islist, isbool, isempty,
        lbrac, rbrac, lpar, rpar,
        word, sentence, list, join, first, last, butfirst, butlast,
        //reverse, find, index,
        random, intt, sqrtt,
        wait, save, load, erall, poall,
        addop, subop, modop, divop, mulop,
        run
    }
    public static String[] keywords = {
        "add", "sub", "div", "mod", "mul",
        "and", "or", "xor", "not",
        "gt", "lt", "eq",
        "make", "thing", "erase", "isname", "print", "read",
        "repeat", "if", "export", "output", "stop", "readlist",
        "isword", "isnumber", "islist", "isbool", "isempty",
        "true", "false",
        "[", "]", "(", ")",
        "word", "sentence", "list", "join", "first", "last", "butfirst", "butlast",
        //"reverse", "find", "index",
        "random", "int", "sqrt",
        "wait", "save", "load", "erall", "poall",
        "+", "-", "*", "/", "%",
        "run"
    };

    private static HashMap<String, Token> str2token = new HashMap<String, Token>() {{
            put("add", Token.add);
            put("sub", Token.sub);
            put("mod", Token.mod);
            put("div", Token.div);
            put("mul", Token.mul);
            put("and", Token.and);
            put("or", Token.or);
            put("xor", Token.xor);
            put("not", Token.not);
            put("gt", Token.gt);
            put("lt", Token.lt);
            put("eq", Token.eq);
            put("make", Token.make);
            put("thing", Token.thing);
            put("erase", Token.erase);
            put("isname", Token.isname);
            put("print", Token.print);
            put("read", Token.read);
            put("repeat", Token.repeat);
            put("if", Token.iff);
            put("export", Token.export);
            put("output", Token.output);
            put("stop", Token.stop);
            put("readlist", Token.readlist);
            put("isword", Token.isword);
            put("isnumber", Token.isnumber);
            put("islist", Token.islist);
            put("isbool", Token.isbool);
            put("isempty", Token.isempty);
            put("[", Token.lbrac);
            put("]", Token.rbrac);
            put("(", Token.lpar);
            put(")", Token.rpar);
            put("word", Token.word);
            put("sentence", Token.sentence);
            put("list", Token.list);
            put("join", Token.join);
            put("first", Token.first);
            put("last", Token.last);
            put("butfirst", Token.butfirst);
            put("butlast", Token.butlast);
//            put("reverse", Token.reverse);
//            put("find", Token.find);
//            put("index", Token.index);
            put("random", Token.random);
            put("int", Token.intt);
            put("sqrt", Token.sqrtt);
            put("wait", Token.wait);
            put("save", Token.save);
            put("load", Token.load);
            put("erall", Token.erall);
            put("poall", Token.poall);
            put("+", Token.addop);
            put("-", Token.subop);
            put("*", Token.mulop);
            put("/", Token.divop);
            put("%", Token.modop);
            put("run", Token.run);
    }};

    public Token token;
    public Data value;

    public Tokens(Token val_token, Data val_value) {
        token = val_token;
        value = val_value;
    }

    public static boolean isToken(String name) {
        return str2token.containsKey(name);
    }
    public static Token getTokenByName(String name) {
        return str2token.get(name);
    }
}
