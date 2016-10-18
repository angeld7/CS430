package edu.drexel.cs430.renderengine.util;

import com.sun.media.sound.InvalidFormatException;
import edu.drexel.cs430.renderengine.geometry.Geometry;
import edu.drexel.cs430.renderengine.geometry.Line;
import edu.drexel.cs430.renderengine.geometry.Point;

import java.io.*;

/**
 * Created by Angel on 9/25/2016.
 */
public class PostScriptReader implements Closeable {
    private static final String LINE = "Line";
    private static final String MOVE_TO = "moveto";
    private static final String LINE_TO = "lineto";
    private static final String STROKE = "stroke";
    private static final String BEGIN = "%%%BEGIN";
    private static final String END = "%%%END";
    private BufferedReader br;
    String line;

    public PostScriptReader(File file) throws IOException {
        br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null && !BEGIN.equals(line)) {
        }
        this.line = br.readLine();
    }

    public Geometry getNextType() throws InvalidFormatException {
        if (line == null) return null;
        while (line != null) {
            String[] args = line.split(" ");
            if (args.length > 0) {
                String command = args[args.length - 1];
                if (LINE.equals(command)) {
                    return Geometry.LINE;
                } else if (MOVE_TO.equals(command)) {
                    return Geometry.POLYGON;
                } else if (LINE_TO.equals(command)) {
                    return Geometry.POINT;
                } else if (STROKE.equals(command)) {
                    return Geometry.STROKE;
                } else if (END.equals(command)) {
                    return null;
                }
            }
            next();
        }
        throw new InvalidFormatException("Invalid line in post script file: line = '" + line + "'");
    }

    public Point parsePoint() {
        if (line != null) {
            String[] args = line.split(" ");
            if (args != null && args.length == 3 && (MOVE_TO.equals(args[2]) || LINE_TO.equals(args[2]))) {
                return new Point(
                        Integer.valueOf(args[0]),
                        Integer.valueOf(args[1])
                );
            }
        }
        throw new IllegalStateException("No point object has been specified on this line in the post script file.");

    }


    public Line parseLineObject() {
        if (line != null) {
            String[] args = line.split(" ");
            if (args != null && args.length == 5 && LINE.equals(args[4])) {
                return new Line(
                        new Point(
                                Integer.valueOf(args[0]),
                                Integer.valueOf(args[1])),
                        new Point(
                                Integer.valueOf(args[2]),
                                Integer.valueOf(args[3]))
                );
            }
        }
        throw new IllegalStateException("No line object has been specified on this line in the post script file.");
    }

    public void next() {
        try {
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        br.close();
    }
}
