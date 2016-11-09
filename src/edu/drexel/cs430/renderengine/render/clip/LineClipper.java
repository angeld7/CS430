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
        int b1 = getBitCode(line.start()), b2 = getBitCode(line.end());
        if ((b1 & b2) != 0) return null;
        if ((b1 | b2) != 0) {
            for (Line side : canvas.sides) {
                line = clip(line, side);
            }
        }
        return line;
    }

    public Line clip(Line l1, Line l2) {
        Point s = l1.start(), e = l1.end();
        float t = RenderUtils.findIntersectionT(l1, l2);
        boolean sO = RenderUtils.isLeft(s, l2);
        boolean eO = RenderUtils.isLeft(e, l2);
        if (sO != eO) {
            if (t >= 0 && t <= 1) {
                if (sO) {
                    s = l1.getPointFromT(t);
                } else {
                    e = l1.getPointFromT(t);
                }
            }
        }
        return sO && eO ? null : new Line(s, e);
    }


    private int getBitCode(Point point) {
        int bitCode = 0;
        if (point.x() < canvas.xMin) {
            bitCode |= 1;
        } else if (point.x() >= canvas.xMax) {
            bitCode |= 2;
        }
        if (point.y() < canvas.yMin) {
            bitCode |= 8;
        } else if (point.y() >= canvas.yMax) {
            bitCode |= 4;
        }
        return bitCode;
    }
}
