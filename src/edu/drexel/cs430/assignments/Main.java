package edu.drexel.cs430.assignments;

import edu.drexel.cs430.renderengine.geometry.Geometry;
import edu.drexel.cs430.renderengine.geometry.Point;
import edu.drexel.cs430.renderengine.geometry.Polygon;
import edu.drexel.cs430.renderengine.render.Renderer;
import edu.drexel.cs430.renderengine.util.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by Angel on 10/18/2016.
 */
public class Main {
    public static void main(String[] args) {
        Arguments arguments = ArgParser.getArgs(args);
        if (arguments.getFilename() != null) {
            try {
                Renderer renderer = new Renderer(arguments);
                if (arguments.is3d()) {
                    try (SMFReader reader = new SMFReader(new File(arguments.getFilename()))) {
                        Polygon polygon;
                        while ((polygon = reader.nextPolygon()) != null) {
                            renderer.render3D(polygon);
                        }
                    }
                } else {
                    Geometry type;
                    Polygon p = new Polygon(new Point(0, 0));
                    try (PostScriptReader reader = new PostScriptReader(new File(arguments.getFilename()))) {
                        while ((type = reader.getNextType()) != null) {
                            if (type == Geometry.LINE) {
                                renderer.render2D(reader.parseLineObject());
                            } else if (type == Geometry.POLYGON) {
                                p = new Polygon(reader.parsePoint());
                            } else if (type == Geometry.POINT) {
                                p.addVertex(reader.parsePoint());
                            } else if (type == Geometry.STROKE) {
                                renderer.render2D(p);
                            }
                            reader.next();
                        }
                    }
                }
                XPMWriter writer = new XPMWriter(renderer.getPixelMatrix());
                System.out.println(writer.createXPMString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
