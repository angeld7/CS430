package edu.drexel.cs430.renderengine.render.util;

import edu.drexel.cs430.renderengine.geometry.Line;
import edu.drexel.cs430.renderengine.geometry.Point;
import edu.drexel.cs430.renderengine.geometry.Vector;

/**
 * Created by Angel on 10/17/16.
 */
public class RenderUtils {
    public static Point findIntersection(Line line1, Line line2) {
        Vector N = Vector.subtract(line2.getStartPoint(), line2.getEndPoint())
                .normalize()
                .getNormal();
        Vector D = Vector.subtract(line1.getEndPoint(), line1.getStartPoint());
        Point p0 = line1.getStartPoint();
        Point pe = line2.getStartPoint();
        float t = Vector.dotProduct(N, Vector.subtract(p0, pe)) /
                    -Vector.dotProduct(N, D);
        return line1.getPointFromT(t);
    }
}
