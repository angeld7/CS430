package edu.drexel.cs430.assignments;

import edu.drexel.cs430.renderengine.Renderer;
import edu.drexel.cs430.renderengine.shapes.Line;
import edu.drexel.cs430.renderengine.util.PostScriptReader;
import edu.drexel.cs430.renderengine.util.XPMWriter;

import java.io.File;
import java.io.IOException;

/**
 * Created by Angel on 9/25/2016.
 */
public class A1 {
    public static void main(String[] args) {
        if (args.length < 1) return;
        Renderer renderer = new Renderer(500, 500);
        try (PostScriptReader reader = new PostScriptReader(new File(args[0]))) {
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
