package src.mua.Namespace;

import src.mua.Tokenizer.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

public class NameSpace implements Serializable {
    private static final HashSet<String> keywords = new HashSet<String>() {{
        add("add"); add("sub"); add("div"); add("mod"); add("mul");
        add("and"); add("or"); add("xor"); add("not");
        add("gt"); add("lt"); add("eq");
        add("make"); add("thing"); add("erase"); add("isname"); add("print"); add("read");
        add("true"); add("false");
    }};

    boolean stopSignal;
    boolean hasRetval;
    Data retval;
    public HashMap<String, Data> scope;
    private String scopeName;
    private NameSpace fatherScope;

    public NameSpace() {
        stopSignal = hasRetval = false;
        retval = null;
        scope = new HashMap<String, Data>() {{
            put("pi", new Data(3.14159));
        }};
        scopeName = "Main";
        fatherScope = null;
    }
    public NameSpace(String name, NameSpace father) {
        stopSignal = hasRetval = false;
        retval = null;
        scope = new HashMap<String, Data>() {{
            put("pi", new Data(3.14159));
        }};
        scopeName = name;
        if(father.fatherScope == null)
            fatherScope = father;
        else
            fatherScope = father.fatherScope;
    }

    public boolean shouldStop() {
        return stopSignal;
    }
    public void setStop() {
        stopSignal = true;
    }
    public void unsetStop() {
        stopSignal = false;
    }
    public boolean hasReturnValue() {
        return hasRetval;
    }
    public void setReturnValue(Data data) {
        hasRetval = true;
        retval = data;
    }
    public void unsetReturnValue() {
        hasRetval = false;
    }
    public Data getRetval() throws Exception {
        if(!hasRetval)
            throw new Exception("Doesn't have retval.");
        return retval;
    }

    public String getScopeName() {
        return scopeName;
    }
    public Data add(String name, Data val) {
        if(name == null || name.isEmpty()) {
            return null;
        }
        if(keywords.contains(name)) {
//            System.err.println(name + " is a keyword.");
        }
        return scope.put(name, val);
    }
    public Data addGlobal(String name, Data val) {
        if(name == null || name.isEmpty()) {
            return null;
        }
        if(fatherScope != null) {
            return fatherScope.add(name, val);
        }
        else {
            return add(name, val);
        }
    }
    public Data remove(String name) {
        if(scope.containsKey(name))
            return scope.remove(name);
        else
            return null;
    }
    public Data getInLocal(String name) {
        return scope.getOrDefault(name, null);
    }
    public Data get(String name) {
        if(name == null || name.isEmpty())
            return null;
        if(scope.containsKey(name)) {
            return getInLocal(name);
        }
        else if(fatherScope != null) {
            return fatherScope.get(name);
        }
        return null;
    }

    public boolean isName(String name) {
        if(name == null || name.isEmpty())
            return false;
        if(scope.containsKey(name))
            return true;
        else if(fatherScope != null)
            return fatherScope.isName(name);
        return false;
    }

    public void removeAll() {
        scope.clear();
    }
    public void showAll() {

    }
}
