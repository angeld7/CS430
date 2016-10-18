package edu.drexel.cs430.renderengine.render.util;

import edu.drexel.cs430.renderengine.geometry.Line;
import edu.drexel.cs430.renderengine.geometry.Point;
import edu.drexel.cs430.renderengine.geometry.Vector;

/**
 * Created by Angel on 10/17/16.
 */
public class RenderUtils {
    public static Point findIntersection(Line line1, Line line2) {
        return line1.getPointFromT(findIntersectionT(line1, line2));
    }

    public static float findIntersectionT(Line line1, Line line2) {
        Vector N = Vector.subtract(line2.start(), line2.end())
                .normalize()
                .getNormal();
        Vector D = Vector.subtract(line1.end(), line1.start());
        Point p0 = line1.start();
        Point pe = line2.start();
        return Vector.dotProduct(N, Vector.subtract(p0, pe)) /
                -Vector.dotProduct(N, D);
    }

    public static boolean isLeft(Point a, Line l) {
        return isLeft(a, l.start(), l.end());
    }

    public static boolean isLeft(Point a, Point b, Point c){
        return ((b.x() - a.x())*(c.y() - a.y()) - (b.y() - a.y())*(c.x() - a.x())) > 0;
    }
}
