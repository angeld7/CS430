package edu.drexel.cs430.renderengine.render;

import edu.drexel.cs430.renderengine.geometry.Canvas;
import edu.drexel.cs430.renderengine.geometry.Line;
import edu.drexel.cs430.renderengine.geometry.Point;
import edu.drexel.cs430.renderengine.geometry.Polygon;
import edu.drexel.cs430.renderengine.matrix.MatrixGenerator;
import edu.drexel.cs430.renderengine.render.clip.LineClipper;
import edu.drexel.cs430.renderengine.render.clip.PolygonClipper;
import edu.drexel.cs430.renderengine.util.Arguments;
import edu.drexel.cs430.renderengine.util.SwingDisplay;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.awt.*;

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
    private boolean parallel = true;
    private float zMin;

    private SwingDisplay swingDisplay = null;

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
            worldToViewport3D = MatrixGenerator.get3DMatrix(args);
            args.setLowerWorldX(-1);
            args.setLowerWorldY(-1);
            args.setUpperWorldX(1);
            args.setUpperWorldY(1);
            worldToViewport2D = MatrixGenerator.generateWorldToViewportMatrix(args);
            parallel = args.isParallelProjection();
            zMin = (args.getPrpZ() - args.getFront()) / (args.getBack() - args.getPrpZ());
        } else {
            transformation = MatrixGenerator.generateTransformationMatrix(args);
            worldToViewport2D = MatrixGenerator.generateWorldToViewportMatrix(args);
        }
        if (args.isDisplay()) {
            swingDisplay = new SwingDisplay(canvas);
        }
    }

    public void render2D(Line line) {
        line.transform2D(transformation);
        line = lineClipper.clip(line);
        if (line != null) {
            line.transform2D(worldToViewport2D);
            lineRenderer.render(line);
        }
        if (swingDisplay != null) {
            swingDisplay.refreshImage();
        }
    }

    public void render2D(Polygon polygon) {
        polygon = polygon.transform2D(transformation);
        polygon = polygonClipper.clip2D(polygon);
        if (polygon != null) {
            polygon = polygon.transform2D(worldToViewport2D);
            for (Line side : polygon) {
                lineRenderer.render(side);
            }
            if (polygon.isFill()) {
                fillRenderer.fillPolygon(polygon);
            }
        }
        if (swingDisplay != null) {
            swingDisplay.refreshImage();
        }
    }


    public void render3D(Polygon polygon, Color color) {
        polygon = polygon.transform3D(worldToViewport3D);
        if (!parallel) {
            polygon = polygonClipper.clip3DPerspective(polygon, zMin);
            if (polygon != null) polygon = polygon.perspectiveScale();
        } else {
            polygon = polygonClipper.clip3DParallel(polygon);
        }
        if (polygon != null) {
            polygon = polygon.transform2D(worldToViewport2D);
            polygon.setColor(color);
            for (Line side : polygon) {
                lineRenderer.render(side);
            }
            //fillRenderer.fillPolygon(polygon);
        }
        if (swingDisplay != null) {
            swingDisplay.refreshImage();
        }
    }

    public boolean[][] getPixelMatrix() {
        return canvas.pixelMatrix;
    }
}
