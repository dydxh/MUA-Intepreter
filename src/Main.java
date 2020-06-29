package src.mua;

import src.mua.Tokenizer.Tokens;
import src.mua.OperatorStack.StackControl;
import src.mua.Tokenizer.Tokenizer;

public class Main {
    public static Tokenizer tokenizer = new Tokenizer();
    public static StackControl stack = new StackControl(tokenizer);

    public static void main(String[] args) throws Exception {
        while(true) {
            try {
                Tokens token = tokenizer.getToken();
                stack.push(token);
            } catch (Exception e) {
                break;
//                System.err.println("Oops, error occur.");
//                e.printStackTrace();
//                System.exit(-1);
            }
        }
        // Test tokenizer
//        Tokenizer wow = new Tokenizer();
//        for(int i = 0; i < 10; i++) {
//            Tokens tmptoken = wow.getToken();
//            System.out.print(String.valueOf(i) + "=> ");
//            System.out.println(tmptoken.token.name() + ": " + tmptoken.value.toString());
//        }
    }
}

