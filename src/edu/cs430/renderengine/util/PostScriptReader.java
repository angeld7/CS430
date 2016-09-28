package edu.cs430.renderengine.util;

import edu.cs430.renderengine.shapes.Line;
import edu.cs430.renderengine.shapes.Point;

import java.io.*;

/**
 * Created by Angel on 9/25/2016.
 */
public class PostScriptReader implements Closeable {
    private static final String BEGIN = "%%%BEGIN";
    private static final String END = "%%%END";
    private BufferedReader br;

    public PostScriptReader(File file) throws IOException {
        br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null && !BEGIN.equals(line)) {
        }
    }

    public Line parseLineObject() {
        Line lineObj = null;
        String line;
        try {
            if ((line = br.readLine()) != null && !END.equals(line)) {
                String[] args = line.split(" ");
                if (args.length == 5 && Line.ID.equals(args[4])) {
                    lineObj = new Line(
                        new Point(
                            Integer.valueOf(args[0]),
                            Integer.valueOf(args[1])),
                        new Point(
                            Integer.valueOf(args[2]),
                            Integer.valueOf(args[3]))
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineObj;
    }

    @Override
    public void close() throws IOException {
        br.close();
    }
}
