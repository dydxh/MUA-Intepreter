package src.mua.Operator;

import src.mua.Tokenizer.Data;
import src.mua.Namespace.NameSpace;
import src.mua.Tokenizer.Tokenizer;

import java.util.HashMap;
import java.util.Vector;

public class OperatorHandler {
    public static HashMap<String, Integer> OpArgc = new HashMap<String, Integer>() {{
        put("add", 3); put("sub", 3); put("mod", 3); put("div", 3); put("mul", 3);
        put("and", 3); put("or", 3); put("xor", 3); put("not", 2);
        put("eq", 3); put("lt", 3); put("gt", 3);
        put("make", 3); put("thing", 2); put("erase", 2); put("isname", 2); put("print", 2); put("read", 1);
        put("repeat", 3); put("iff", 4); put("readlist", 1);
        put("export", 2); put("output", 2); put("stop", 1);
        put("isword", 2); put("isnumber", 2); put("islist", 2); put("isbool", 2); put("isempty", 2);
        put("word", 3); put("sentence", 3); put("list", 3); put("join", 3);
        put("first", 2); put("last", 2); put("butfirst", 2); put("butlast", 2);
        //put("reverse", 2); put("find", 3); put("index", 3);
        put("random", 2); put("intt", 2); put("sqrtt", 2);
        put("wait", 2); put("save", 2); put("load", 2);
        put("erall", 1); put("poall", 1);
        put("addop", 0); put("mulop", 0); put("divop", 0); put("modop", 0);
        put("run", 2);
    }};

    private String opName;
    private int argc;
    private Vector<Data> argv;
    private NameSpace nameSpace;
    private boolean hasReturnValue;
    private Tokenizer tokenizer;

    public OperatorHandler(String name, Vector<Data> arglist, NameSpace nSpace, Tokenizer tknizer) {
        opName = name;
        argc = arglist.size();
        argv = arglist;
        nameSpace = nSpace;
        hasReturnValue = false;
        tokenizer = tknizer;
    }

    public boolean isHasReturnValue() {
        return hasReturnValue;
    }

    public Data execute() {
        Data retval;
        Operation op;
        try {
            switch (opName) {
                case "add":
                    retval = AddOp.execute(argv, nameSpace);
                    hasReturnValue = true;
                    break;
                case "sub":
                    retval = SubOp.execute(argv, nameSpace);
                    hasReturnValue = true;
                    break;
                case "mod":
                    retval = ModOp.execute(argv, nameSpace);
                    hasReturnValue = true;
                    break;
                case "div":
                    retval = DivOp.execute(argv, nameSpace);
                    hasReturnValue = true;
                    break;
                case "mul":
                    retval = MulOp.execute(argv, nameSpace);
                    hasReturnValue = true;
                    break;
                case "and":
                    retval = AndOp.execute(argv, nameSpace);
                    hasReturnValue = true;
                    break;
                case "or":
                    retval = OrOp.execute(argv, nameSpace);
                    hasReturnValue = true;
                    break;
                case "xor":
                    retval = XorOp.execute(argv, nameSpace);
                    hasReturnValue = true;
                    break;
                case "not":
                    retval = NotOp.execute(argv, nameSpace);
                    hasReturnValue = true;
                    break;
                case "eq":
                    retval = EqOp.execute(argv, nameSpace);
                    hasReturnValue = true;
                    break;
                case "lt":
                    retval = LtOp.execute(argv, nameSpace);
                    hasReturnValue = true;
                    break;
                case "gt":
                    retval = GtOp.execute(argv, nameSpace);
                    hasReturnValue = true;
                    break;
                case "make":
                    retval = MakeOp.execute(argv, nameSpace);
                    hasReturnValue = false;
                    break;
                case "thing":
                    retval = ThingOp.execute(argv, nameSpace);
                    hasReturnValue = true;
                    break;
                case "erase":
                    retval = EraseOp.execute(argv, nameSpace);
                    hasReturnValue = false;
                    break;
                case "isname":
                    retval = IsNameOp.execute(argv, nameSpace);
                    hasReturnValue = true;
                    break;
                case "print":
                    retval = PrintOp.execute(argv, nameSpace);
                    hasReturnValue = false;
                    break;
                case "read":
                    retval = ReadOp.execute(argv, nameSpace, tokenizer);
                    hasReturnValue = true;
                    break;
                case "repeat" :
                    retval = RepeatOp.execute(argv, nameSpace, tokenizer);
                    hasReturnValue = false;
                    break;
                case "iff" :
                    retval = IfOp.execute(argv, nameSpace, tokenizer);
                    hasReturnValue = false;
                    break;
                case "export" :
                    retval = ExportOp.execute(argv, nameSpace);
                    hasReturnValue = false;
                    break;
                case "stop" :
                    nameSpace.setStop();
                    retval = null;
                    hasReturnValue = false;
                    break;
                case "output" :
                    nameSpace.setReturnValue(argv.get(0));
                    retval = null;
                    hasReturnValue = false;
                    break;
                case "isword" :
                    retval = IswordOp.execute(argv);
                    hasReturnValue = true;
                    break;
                case "isnumber" :
                    retval = IsnumberOp.execute(argv);
                    hasReturnValue = true;
                    break;
                case "islist" :
                    retval = IslistOp.execute(argv);
                    hasReturnValue = true;
                    break;
                case "isbool" :
                    retval = IsboolOp.execute(argv);
                    hasReturnValue = true;
                    break;
                case "isempty" :
                    retval = IsemptyOp.execute(argv);
                    hasReturnValue = true;
                    break;
                case "word":
                    retval = WordOp.execute(argv);
                    hasReturnValue = true;
                    break;
                case "sentence":
                    retval = SentenceOp.execute(argv);
                    hasReturnValue = true;
                    break;
                case "list":
                    retval = ListOp.execute(argv);
                    hasReturnValue = true;
                    break;
                case "join":
                    retval = JoinOp.execute(argv);
                    hasReturnValue = true;
                    break;
                case "first":
                    retval = FirstOp.execute(argv);
                    hasReturnValue = true;
                    break;
                case "last":
                    retval = LastOp.execute(argv);
                    hasReturnValue = true;
                    break;
                case "butfirst":
                    retval = ButFirstOp.execute(argv);
                    hasReturnValue = true;
                    break;
                case "butlast":
                    retval = ButLastOp.execute(argv);
                    hasReturnValue = true;
                    break;
//                case "reverse":
//                    retval = ReverseOp.execute(argv);
//                    hasReturnValue = true;
//                    break;
//                case "find" :
//                    retval = FindOp.execute(argv);
//                    hasReturnValue = true;
//                    break;
//                case "index" :
//                    retval = IndexOp.execute(argv);
//                    hasReturnValue = true;
//                    break;
                case "random":
                    retval = RandomOp.execute(argv);
                    hasReturnValue = true;
                    break;
                case "intt":
                    retval = IntOp.execute(argv);
                    hasReturnValue = true;
                    break;
                case "sqrtt":
                    retval = SqrtOp.execute(argv);
                    hasReturnValue = true;
                    break;
                case "wait":
                    retval = WaitOp.execute(argv);
                    hasReturnValue = false;
                    break;
                case "save":
                    retval = SaveOp.execute(argv, nameSpace);
                    hasReturnValue = false;
                    break;
                case "load":
                    retval = LoadOp.execute(argv, nameSpace);
                    hasReturnValue = false;
                    break;
                case "erall":
                    retval = ErallOp.execute(argv, nameSpace);
                    hasReturnValue = false;
                    break;
                case "poall":
                    retval = PoallOp.execute(argv, nameSpace);
                    hasReturnValue = false;
                    break;
                case "subop":
                    retval = SubopOp.execute(argv);
                    hasReturnValue = true;
                    break;
                case "run":
                    retval = RunOp.execute(argv, nameSpace, tokenizer);
                    hasReturnValue = false;
                    break;
                default:
                    assert argc == 1 : "[Arguments number error]: shoud be 1.\n";
                    retval = argv.get(0);
                    hasReturnValue = true;
            }
        } catch (Exception e) {
//            System.err.println(e);
            return null;
        }
        if(!hasReturnValue)
            assert retval == null: "[Unexcepted return value]\n";
        return retval;
    }
}
