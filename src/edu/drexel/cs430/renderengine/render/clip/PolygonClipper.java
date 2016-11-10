package edu.drexel.cs430.renderengine.render.clip;

import edu.drexel.cs430.renderengine.geometry.Canvas;
import edu.drexel.cs430.renderengine.geometry.Line;
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

    public Polygon clip(Polygon polygon) {
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
}
