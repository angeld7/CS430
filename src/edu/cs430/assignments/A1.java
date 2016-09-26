package edu.cs430.assignments;

import edu.cs430.renderengine.shapes.Line;
import edu.cs430.renderengine.util.PostScriptReader;

import java.io.File;
import java.io.IOException;

/**
 * Created by Angel on 9/25/2016.
 */
public class A1 {
    public static void main(String[] args) {
        if (args.length < 1) return;
        try (PostScriptReader reader = new PostScriptReader(new File(args[0]))) {
            Line line;
            while ((line = reader.parseLineObject()) != null) {
                System.out.println("Start: " + line.getStartPoint().getX() + ", " + line.getStartPoint().getY() + " End: " + line.getEndPoint().getX() + ", " + line.getEndPoint().getY());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
