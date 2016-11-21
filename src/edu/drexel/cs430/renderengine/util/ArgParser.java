package edu.drexel.cs430.renderengine.util;

/**
 * Created by Angel on 9/27/2016.
 */
public class ArgParser {
    public static Arguments getArgs(String[] args) {
        Arguments arguments = new Arguments();
        for (int x = 0; x < args.length; ++x) {
            switch (args[x]) {
                case "-f":
                    arguments.setFilename(args[++x]);
                    break;
                case "-s":
                    arguments.setScale(Float.parseFloat(args[++x]));
                    break;
                case "-r":
                    if (arguments.is3d()) {
                        // y value of the VPN in VRC coordinates
                        arguments.setVpnY(Float.parseFloat(args[++x]));
                    } else {
                        arguments.setRotation(Float.parseFloat(args[++x]));
                    }
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
                case "-x": //x value for PRP in VRC coordinates
                    arguments.setPrpX(Float.parseFloat(args[++x]));
                    break;
                case "-y": //y value for PRP in VRC coordinates
                    arguments.setPrpY(Float.parseFloat(args[++x]));
                    break;
                case "-z": //z value for PRP in VRC coordinates
                    arguments.setPrpZ(Float.parseFloat(args[++x]));
                    break;
                case "-X": // x value of the VRP in VRC coordinates
                    arguments.setVrpX(Float.parseFloat(args[++x]));
                    break;
                case "-Y": // y value of the VRP in VRC coordinates
                    arguments.setVrpY(Float.parseFloat(args[++x]));
                    break;
                case "-Z": // z value of the VRP in VRC coordinates
                    arguments.setVrpZ(Float.parseFloat(args[++x]));
                    break;
                case "-q": // x value of the VPN in VRC coordinates
                    arguments.setVpnX(Float.parseFloat(args[++x]));
                    break;
                case "-w": // z value of VPN in VRC coordinates
                    arguments.setVpnZ(Float.parseFloat(args[++x]));
                    break;
                case "-Q": // x value of VUP in VRC coordinates
                    arguments.setVupX(Float.parseFloat(args[++x]));
                    break;
                case "-R": // y value of VUP in VRC coordinates
                    arguments.setVupY(Float.parseFloat(args[++x]));
                    break;
                case "-W": // z value of VUP in VRC coordinates
                    arguments.setVupZ(Float.parseFloat(args[++x]));
                    break;
                case "-u": // u min of VRC in VRC coordinates
                    arguments.setuMin(Float.parseFloat(args[++x]));
                    break;
                case "-v": // v min of VRC in VRC coordinates
                    arguments.setvMin(Float.parseFloat(args[++x]));
                    break;
                case "-U": // u max of VRC in VRC coordinates
                    arguments.setuMax(Float.parseFloat(args[++x]));
                    break;
                case "-V": // v max of VRC in VRC coordinates
                    arguments.setvMax(Float.parseFloat(args[++x]));
                    break;
                case "-P": // Use parallel projection
                    arguments.setParallelProjection(true);
                    break;
                case "-display":
                    arguments.setDisplay(true);
                    break;
                case "-F":
                    arguments.setFront(Float.parseFloat(args[x++]));
                    break;
                case "-B":
                    arguments.setBack(Float.parseFloat(args[x++]));
                    break;
                case "-g":
                    arguments.setFilename2(args[++x]);
                    break;
                case "-i":
                    arguments.setFilename3(args[++x]);
                    break;
                default:
                    break;
            }
        }
        return arguments;
    }
}
