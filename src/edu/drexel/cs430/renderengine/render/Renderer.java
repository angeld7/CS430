package edu.drexel.cs430.renderengine.render;

import com.sun.org.apache.xpath.internal.Arg;
import edu.drexel.cs430.renderengine.geometry.Canvas;
import edu.drexel.cs430.renderengine.geometry.Line;
import edu.drexel.cs430.renderengine.geometry.Polygon;
import edu.drexel.cs430.renderengine.matrix.MatrixGenerator;
import edu.drexel.cs430.renderengine.render.clip.LineClipper;
import edu.drexel.cs430.renderengine.render.clip.PolygonClipper;
import edu.drexel.cs430.renderengine.util.Arguments;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * Created by Angel on 10/17/2016.
 */
public class Renderer {
    private LineRenderer lineRenderer;
    private FillRenderer fillRenderer;
    private LineClipper lineClipper;
    private PolygonClipper polygonClipper;
    private Canvas canvas;
    private RealMatrix transformation;
    private RealMatrix worldToViewport;

    public Renderer(Arguments args) {
        canvas = new Canvas(
                500,
                500,
                args.getUpperWorldX(),
                args.getUpperWorldY(),
                args.getLowerWorldX(),
                args.getLowerWorldY()
        );
        lineRenderer = new LineRenderer(canvas);
        fillRenderer = new FillRenderer(canvas);
        lineClipper = new LineClipper(canvas);
        polygonClipper = new PolygonClipper(canvas, lineClipper);
        transformation = MatrixGenerator.generateTransformationMatrix(args);
        worldToViewport = MatrixGenerator.generateWorldToViewportMatrix(args);
    }

    public void render(Line line) {
        line.transform(transformation);
        line = lineClipper.clip(line);
        if (line != null) {
            line.transform(worldToViewport);
            lineRenderer.render(line);
        }
    }

    public void render(Polygon polygon) {
        polygon.transform(transformation);
        polygon = polygonClipper.clip(polygon);
        if (polygon != null) {
            polygon.transform(worldToViewport);
            for (Line side : polygon) {
                lineRenderer.render(side);
            }
            if (polygon.isFill()) {
                fillRenderer.fillPolygon(polygon);
            }
        }
    }

    public boolean[][] getPixelMatrix() {
        return canvas.pixelMatrix;
    }
}
