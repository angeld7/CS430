package edu.drexel.cs430.assignments;

import edu.drexel.cs430.renderengine.render.LineRenderer;
import edu.drexel.cs430.renderengine.shapes.Line;
import edu.drexel.cs430.renderengine.util.ArgParser;
import edu.drexel.cs430.renderengine.util.PostScriptReader;
import edu.drexel.cs430.renderengine.util.XPMWriter;

import java.io.File;
import java.io.IOException;

/**
 * Created by Angel on 10/3/16.
 */
public class A2 {
    public static void main(String[] args){
        LineRenderer lineRenderer = new LineRenderer(500, 500);
        String filename = ArgParser.getArg("-f", args);
        if (filename != null) {
            try (PostScriptReader reader = new PostScriptReader(new File(filename))) {
                Line line;
                while ((line = reader.parseLineObject()) != null) {
                    lineRenderer.drawLine(line);
                }
                XPMWriter writer = new XPMWriter(lineRenderer.getPixelMatrix());
                System.out.println(writer.createXPMString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
