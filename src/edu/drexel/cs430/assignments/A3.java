package edu.drexel.cs430.assignments;

import edu.drexel.cs430.renderengine.geometry.Geometry;
import edu.drexel.cs430.renderengine.geometry.Point;
import edu.drexel.cs430.renderengine.geometry.Polygon;
import edu.drexel.cs430.renderengine.render.Renderer;
import edu.drexel.cs430.renderengine.util.ArgParser;
import edu.drexel.cs430.renderengine.util.PostScriptReader;
import edu.drexel.cs430.renderengine.util.XPMWriter;

import java.io.File;
import java.io.IOException;

/**
 * Created by Angel on 10/18/2016.
 */
public class A3 {
    public static void main(String[] args) {
        Renderer renderer = new Renderer(500, 500);
        String filename = ArgParser.getArg("-f", args);
        if (filename != null) {
            try (PostScriptReader reader = new PostScriptReader(new File(filename))) {
                Geometry type;
                Polygon p = new Polygon(new Point(0, 0));
                while ((type = reader.getNextType()) != null) {
                    if (type == Geometry.LINE) {
                        renderer.render(reader.parseLineObject());
                    } else if (type == Geometry.POLYGON) {
                        p = new Polygon(reader.parsePoint());
                    } else if (type == Geometry.POINT) {
                        p.addVertex(reader.parsePoint());
                    } else if (type == Geometry.STROKE) {
                        renderer.render(p);
                    }
                    reader.next();
                }
                XPMWriter writer = new XPMWriter(renderer.getPixelMatrix());
                System.out.println(writer.createXPMString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
