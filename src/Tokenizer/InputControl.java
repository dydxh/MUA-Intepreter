package src.mua.Tokenizer;

import java.io.*;
import java.util.Scanner;

public class InputControl {
    private Scanner input;
    private String curline = "";

    InputControl() {
        input = new Scanner(System.in);
    }
    InputControl(InputStream inputStream) {
        input = new Scanner(inputStream);
    }

    private String strip(String val) {
        if(val == null) {
            return "";
        }
        if(val.contains("//")) {
            val = val.substring(0, val.indexOf("//"));
        }
        val = val.trim();
        return val;
    }

    public static String readFile(String fileName) throws Exception {
        File file = new File(fileName);
        BufferedReader tmpreader = new BufferedReader(new FileReader(file));
        String tmpstr;
        StringBuffer tmpbuf = new StringBuffer();
        while ((tmpstr = tmpreader.readLine()) != null) {
            tmpbuf.append(tmpstr);
        }
        tmpreader.close();
        return tmpbuf.toString();
    }

    String getStringItem() throws Exception {
        while(curline.equals("")) {
            curline = strip(input.nextLine());
//            System.err.println(curline);
        }

        if(curline.charAt(0) == ':') {
            curline = strip(curline.substring(1));
            return "thing";
        }

        String seperator = " \t\n[]()+-*/%";
        String space = " \t\n[]()+-*/%";
        int i;
        boolean isWord = curline.charAt(0) == '\"';
        for(i = 0; i < curline.length(); i++) {
            if(isWord && space.contains(String.valueOf(curline.charAt(i)))) {
                break;
            }
            else if(!isWord && seperator.contains(String.valueOf(curline.charAt(i)))) {
                break;
            }
        }

        String retval;
        int pos;
        if(i == 0) {
            pos = 1;
            retval = curline.substring(0, 1);
            curline = strip(curline.substring(pos));
        }
        else if(i == curline.length()) {
            retval = curline;
            curline = "";
        }
        else {
            pos = i;
            retval = curline.substring(0, pos);
            curline = strip(curline.substring(pos));
        }
        return retval;
    }
}
