package edu.drexel.cs430.renderengine.util;

/**
 * Created by Angel on 9/27/2016.
 */
public class ArgParser {
    public static Arguments getArgs(String[] args) {
        Arguments arguments = new Arguments();
        for(int x = 0; x < args.length - 1; ++x) {
            switch(args[x]) {
                case "-f":
                    arguments.setFilename(args[++x]);
                    break;
                case "-s":
                    arguments.setScale(Float.parseFloat(args[++x]));
                    break;
                case "-r":
                    arguments.setRotation(Float.parseFloat(args[++x]));
                    break;
                case "-m":
                    arguments.setTranslateX(Integer.parseInt(args[++x]));
                    break;
                case "-n":
                    arguments.setTranslateY(Integer.parseInt(args[++x]));
                    break;
                case "-a":
                    arguments.setLowerWorldX(Integer.parseInt(args[++x]));
                    break;
                case "-b":
                    arguments.setLowerWorldY(Integer.parseInt(args[++x]));
                    break;
                case "-c":
                    arguments.setUpperWorldX(Integer.parseInt(args[++x]));
                    break;
                case "-d":
                    arguments.setUpperWorldY(Integer.parseInt(args[++x]));
                    break;
                case "-j":
                    arguments.setLowerViewX(Integer.parseInt(args[++x]));
                    break;
                case "-k":
                    arguments.setLowerViewY(Integer.parseInt(args[++x]));
                    break;
                case "-o":
                    arguments.setUpperViewX(Integer.parseInt(args[++x]));
                    break;
                case "-p":
                    arguments.setUpperViewY(Integer.parseInt(args[++x]));
                    break;
                default:
                    break;
            }
        }
        return arguments;
    }
}
