package edu.drexel.cs430.renderengine.render.clip;

import edu.drexel.cs430.renderengine.geometry.Canvas;
import edu.drexel.cs430.renderengine.geometry.Line;
import edu.drexel.cs430.renderengine.geometry.Point;
import edu.drexel.cs430.renderengine.render.util.RenderUtils;

/**
 * Created by Angel on 10/17/2016.
 */
public class LineClipper {

    Canvas canvas;

    public LineClipper(Canvas canvas) {
        this.canvas = canvas;
    }

    public Line clip(Line line) {
        line = new Line(line);
        for (Line side : canvas.sides) {
            line = clip(line, side);
        }
        return line;
    }

    public Line clip(Line l1, Line l2) {
        Point s = l1.start(), e = l1.end();
        boolean sO = RenderUtils.isLeft(s, l2);
        boolean eO = RenderUtils.isLeft(e, l2);
        if (sO != eO) {
            float t = RenderUtils.findIntersectionT(l1, l2);
            if (sO) {
                s = l1.getPointFromT(t);
            } else {
                e = l1.getPointFromT(t);
            }
        }
        return sO && eO ? null : new Line(s, e);
    }
}
