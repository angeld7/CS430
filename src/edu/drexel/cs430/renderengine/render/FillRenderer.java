package edu.drexel.cs430.renderengine.render;

import edu.drexel.cs430.renderengine.geometry.Canvas;
import edu.drexel.cs430.renderengine.geometry.Line;
import edu.drexel.cs430.renderengine.geometry.Point;
import edu.drexel.cs430.renderengine.geometry.Polygon;
import edu.drexel.cs430.renderengine.render.util.RenderUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Angel on 10/19/16.
 */
public class FillRenderer {
    Canvas canvas;

    public FillRenderer(Canvas canvas) {
        this.canvas = canvas;
    }

    public void fillPolygon(Polygon p) {
        for (int y = Math.round(p.yMin()); y < p.yMax(); y++) {
            List<Integer> intersectionsX = new ArrayList<>();
            Line scanLine = new Line(new Point(p.xMin(), y), new Point(p.xMax(), y));
            for (Line side : p) {
                float yStart = Math.round(side.start().y()), yEnd = Math.round(side.end().y());
                if (yStart == yEnd) continue;
                if (y == Math.max(yStart, yEnd)) continue;
                float t = RenderUtils.findIntersectionT(side, scanLine);
                if (t >= 0 && t <= 1) {
                    Point i = side.getPointFromT(t);
                    intersectionsX.add(Math.round(i.x()));
                }
            }
            Collections.sort(intersectionsX);
            if (intersectionsX.size() % 2 == 1) {
                throw new IllegalStateException("Polygon is deformed");
            }
            for (int i = 0; i < intersectionsX.size(); i += 2) {
                for (int x = intersectionsX.get(i); x <= intersectionsX.get(i + 1); x++) {
                    canvas.pixelMatrix[y][x] = true;
                }
            }

        }
    }
}
