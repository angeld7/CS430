package edu.drexel.cs430.renderengine.util;

/**
 * Created by Angel on 9/27/2016.
 */
public class ArgParser {
    public static String getArg(String flag, String[]args) {
        boolean f = false;
        for (String arg : args) {
            if (f) return arg;
            else if (flag.equals(arg)) f = true;
        }
        return null;
    }
}
