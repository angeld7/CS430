package edu.drexel.cs430.assignments;

import edu.drexel.cs430.renderengine.Renderer;
import edu.drexel.cs430.renderengine.shapes.Line;
import edu.drexel.cs430.renderengine.util.ArgParser;
import edu.drexel.cs430.renderengine.util.PostScriptReader;
import edu.drexel.cs430.renderengine.util.XPMWriter;

import java.io.File;
import java.io.IOException;

/**
 * Created by Angel on 9/25/2016.
 */
public class A1 {
    public static void main(String[] args) {
        Renderer renderer = new Renderer(500, 500);
        String filename = ArgParser.getArg("-f",args);
        if (filename != null) {
            try (PostScriptReader reader = new PostScriptReader(new File(filename))) {
                Line line;
                while ((line = reader.parseLineObject()) != null) {
                    renderer.drawLine(line);
                }
                XPMWriter writer = new XPMWriter(renderer.getPixelMatrix());
                System.out.println(writer.createXPMString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
