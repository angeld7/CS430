package edu.drexel.cs430.renderengine.util;

/**
 * Created by Angel on 9/25/2016.
 */
public class XPMWriter {
    boolean[][] pixelMatrix;
    public XPMWriter(boolean[][] pixelMatrix) {
        this.pixelMatrix = pixelMatrix;
    }

    public String createXPMString() {
        StringBuilder sb = new StringBuilder();
        sb.append("static char *sco100[] = {\n\"");
        sb.append(pixelMatrix.length).append(" ").append(pixelMatrix[0].length);
        sb.append(" 2 1\"\n\"# c #ffffff\",\n\"- c #000000\",\n");
        for(int y = pixelMatrix.length - 1; y >= 0; y--) {
            sb.append("\"");
            for (int x = 0; x < pixelMatrix[y].length; x++) {
                sb.append(pixelMatrix[y][x] ? "#" : "-");
            }
            sb.append("\"");
            if(y != pixelMatrix.length - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("};");
        return sb.toString();
    }
}
