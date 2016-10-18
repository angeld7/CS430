package edu.drexel.cs430.renderengine.render;

import edu.drexel.cs430.renderengine.geometry.Canvas;
import edu.drexel.cs430.renderengine.geometry.Line;
import edu.drexel.cs430.renderengine.geometry.Polygon;
import edu.drexel.cs430.renderengine.render.clip.LineClipper;
import edu.drexel.cs430.renderengine.render.clip.PolygonClipper;

/**
 * Created by Angel on 10/17/2016.
 */
public class Renderer {
    LineRenderer lineRenderer;
    LineClipper lineClipper;
    PolygonClipper polygonClipper;
    Canvas canvas;

    public Renderer(int width, int height) {
        canvas = new Canvas(width, height);
        lineRenderer = new LineRenderer(canvas);
        lineClipper = new LineClipper(canvas);
        polygonClipper = new PolygonClipper(canvas, lineClipper);
    }

    public void render(Line line) {
        line = lineClipper.clip(line);
        if (line != null) {
            lineRenderer.render(line);
        }
    }

    public void render(Polygon polygon) {
        polygon = polygonClipper.clip(polygon);
        if (polygon != null) {
            for (Line side : polygon) {
                lineRenderer.render(side);
            }
        }
    }

    public boolean[][] getPixelMatrix() {
        return canvas.pixelMatrix;
    }
}
