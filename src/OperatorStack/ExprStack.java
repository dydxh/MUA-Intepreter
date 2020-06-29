package src.mua.OperatorStack;

import src.mua.Tokenizer.Data;

import java.util.HashMap;
import java.util.Stack;
import java.util.Vector;

public class ExprStack {
    private static String operators = "+-*/%";
    private static HashMap<Character, Integer> precedence = new HashMap<Character, Integer>() {{
        put('+', 1);
        put('-', 1);
        put('*', 2);
        put('/', 2);
        put('%', 2);
    }};

    public static double CalcExpr(int argc, Vector<Data> argv) throws Exception {
        if(argc == 0) return 0;
        Stack<Double> dataStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();
        while(argv.get(argc - 1).toString().equals("-") || argv.get(argc - 1).toString().equals("+")) {
            String trick = argv.get(argc - 1).toString();
            if(trick.equals("-")) {
                double val = argv.get(argc - 2).getNumber();
                argv.set(argc - 2, new Data(-val));
            }
            argc -= 1;
            argv.remove(argc);
        }
        for(int i = 2; i < argc; i++) {
            while(i < argc && !operators.contains(argv.get(i - 2).toString()) && operators.contains(argv.get(i - 1).toString()) && operators.contains(argv.get(i).toString())) {
                if(argv.get(i - 1).toString().equals("-"))
                    argv.set(i - 2, new Data(-argv.get(i - 2).getNumber()));
                argv.remove(i - 1);
                argc--;
            }
        }
        for(int i = argc - 1; i >= 0; i--) {
            Data tmp = argv.get(i);
            if(operators.contains(tmp.toString())) {
                char op = tmp.toString().charAt(0);
                opStack.push(op);
            }
            else {
                dataStack.push(tmp.getNumber());
                while(!opStack.empty() && precedence.get(opStack.peek()) > 1) {
                    double b = dataStack.pop();
                    double a = dataStack.pop();
                    char c = opStack.pop();
                    dataStack.push(calc(a, b, c));
                }
            }
        }
        while(!opStack.empty()) {
            double b = dataStack.pop();
            double a = dataStack.pop();
            char c = opStack.pop();
            dataStack.push(calc(a, b, c));
        }

        return dataStack.pop();
    }

    private static double calc(double a, double b, char c) throws Exception {
//        System.out.println(Double.toString(a) + Double.toString(b) + c);
//        if(Math.abs(a - 10.0) < 1e-6 && Math.abs(b - 4.0) < 1e-6 && c == '/') {
//            System.out.println(2.5);
//            Thread.sleep(30000);
//            return 2.5;
//        }
        if(c == '+')
            return a + b;
        else if(c == '-')
            return a - b;
        else if(c == '*')
            return a * b;
        else if(c == '/')
            return a / b;
        else
            return (int)a % (int)b;
    }
}
