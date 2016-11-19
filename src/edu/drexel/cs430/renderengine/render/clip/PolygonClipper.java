package edu.drexel.cs430.renderengine.render.clip;

import edu.drexel.cs430.renderengine.geometry.Canvas;
import edu.drexel.cs430.renderengine.geometry.Line;
import edu.drexel.cs430.renderengine.geometry.Point;
import edu.drexel.cs430.renderengine.geometry.Polygon;
import edu.drexel.cs430.renderengine.render.util.RenderUtils;

/**
 * Created by Angel on 10/17/2016.
 */
public class PolygonClipper {
    Canvas canvas;
    LineClipper lineClipper;

    public PolygonClipper(Canvas canvas, LineClipper lineClipper) {
        this.lineClipper = lineClipper;
        this.canvas = canvas;
    }

    public Polygon clip2D(Polygon polygon) {
        Polygon lastP = polygon;
        Polygon newP = new Polygon();
        for (Line cSide : canvas.sides) {
            for (Line lSide : lastP) {
                Line l = lineClipper.clip(lSide, cSide);
                if (l != null) {
                    if (RenderUtils.isLeft(lSide.start(), cSide)) {
                        newP.addVertex(l.start());
                    }
                    newP.addVertex(l.end());
                }
            }
            lastP = newP;
            newP = new Polygon();
        }
        return lastP.getNamOfVertices() == 0 ? null : lastP;
    }

    public Polygon clip3DParallel(Polygon polygon) {
        return (polygon.xMin() < -1 || polygon.xMax() > 1 ||
                polygon.yMin() < -1 || polygon.yMax() > 1 ||
                polygon.zMin() < -1 || polygon.zMax() > 1)
                ? null : polygon;
    }

    public Polygon clip3DPerspective(Polygon polygon, float zMin) {
        for (Point p : polygon.getVerticies()) {
            if (p.y() > -p.z() || p.y() < p.z() || p.x() > -p.z() || p.x() < p.z() || p.z() < -1 || p.z() > zMin) {
                return null;
            }
        }
        return polygon;
    }
}
