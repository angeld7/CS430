package edu.drexel.cs430.renderengine.render;

import edu.drexel.cs430.renderengine.geometry.Canvas;
import edu.drexel.cs430.renderengine.geometry.Line;
import edu.drexel.cs430.renderengine.geometry.Polygon;
import edu.drexel.cs430.renderengine.matrix.MatrixGenerator;
import edu.drexel.cs430.renderengine.render.clip.LineClipper;
import edu.drexel.cs430.renderengine.render.clip.PolygonClipper;
import edu.drexel.cs430.renderengine.util.Arguments;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
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
    private RealMatrix worldToViewport2D;
    private RealMatrix worldToViewport3D;

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
        if (args.is3d()) {
            worldToViewport3D = (new Array2DRowRealMatrix(
                    new double[][]{
                            {0.89, 0, 0, 0},
                            {0, 0.89, 0, 0},
                            {0, 0, 0.63, -0.63},
                            {0, 0, 0, 1}
                    }
            )).multiply(new Array2DRowRealMatrix(
                    new double[][]{
                            {-1, 0, 0, 0},
                            {0, 1, 0, 0},
                            {0, 0, -1, 0},
                            {0, 0, 0, 1}
                    }
            ));//MatrixGenerator.get3DMatrix(args);
        }
        transformation = MatrixGenerator.generateTransformationMatrix(args);
        worldToViewport2D = MatrixGenerator.generateWorldToViewportMatrix(args);
    }

    public void render2D(Line line) {
        line.transform2D(transformation);
        line = lineClipper.clip(line);
        if (line != null) {
            line.transform3D(worldToViewport2D);
            lineRenderer.render(line);
        }
    }

    public void render2D(Polygon polygon) {
        polygon.transform2D(transformation);
        polygon = polygonClipper.clip2D(polygon);
        if (polygon != null) {
            polygon.transform2D(worldToViewport2D);
            for (Line side : polygon) {
                lineRenderer.render(side);
            }
            if (polygon.isFill()) {
                fillRenderer.fillPolygon(polygon);
            }
        }
    }

    public void render3D(Polygon polygon) {
        polygon.transform3D(worldToViewport3D);
        polygon = polygonClipper.clip2D(polygon);
        //polygon = polygonClipper.clip3D(polygon);
        if (polygon != null) {
            polygon.transform2D(worldToViewport2D);
            for (Line side : polygon) {
                if (side.start().x() > 0 && side.start().y() > 0 && side.end().x() > 0 && side.end().y() > 0) {
                    lineRenderer.render(side);
                }
            }
        }
    }

    public boolean[][] getPixelMatrix() {
        return canvas.pixelMatrix;
    }
}
