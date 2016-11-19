package edu.drexel.cs430.renderengine.matrix;

import edu.drexel.cs430.renderengine.geometry.Vector;
import edu.drexel.cs430.renderengine.util.Arguments;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * Created by Angel on 11/8/2016.
 */
public class MatrixGenerator {
    public static RealMatrix generateTransformationMatrix(Arguments args) {
        //Find total composite transformation
        RealMatrix translation = getTranslationMatrix2D(args.getTranslateX(), args.getTranslateY());
        RealMatrix scale = getScaleMatrix2D(args.getScale(), args.getScale());
        RealMatrix rotation = getRotationMatrix2D(Math.toRadians(args.getRotation()));
        return translation.multiply(rotation.multiply(scale));//scale.multiply(rotation.multiply(translation));
    }

    public static RealMatrix generateWorldToViewportMatrix(Arguments args) {
        //Map to viewport
        double xMax = args.getUpperWorldX();
        double yMax = args.getUpperWorldY();
        double xMin = args.getLowerWorldX();
        double yMin = args.getLowerWorldY();
        double uMax = args.getUpperViewX();
        double vMax = args.getUpperViewY();
        double uMin = args.getLowerViewX();
        double vMin = args.getLowerViewY();
        RealMatrix translate1 = getTranslationMatrix2D(uMin, vMin);
        RealMatrix worldScale = getScaleMatrix2D(
                (uMax - uMin) / (xMax - xMin),
                (vMax - vMin) / (yMax - yMin)
        );
        RealMatrix translate2 = getTranslationMatrix2D(-xMin, -yMin);
        return translate1.multiply(worldScale.multiply(translate2));
    }

    private static RealMatrix getTranslationMatrix2D(double x, double y) {
        return new Array2DRowRealMatrix(
                new double[][]{{1, 0, x}, {0, 1, y}, {0, 0, 1}}
        );
    }

    private static RealMatrix getScaleMatrix2D(double x, double y) {
        return new Array2DRowRealMatrix(
                new double[][]{{x, 0, 0}, {0, y, 0}, {0, 0, 1}}
        );
    }

    private static RealMatrix getRotationMatrix2D(double theta) {
        return new Array2DRowRealMatrix(
                new double[][]{{Math.cos(theta), -Math.sin(theta), 0}, {Math.sin(theta), Math.cos(theta), 0}, {0, 0, 1}}
        );
    }

    private static RealMatrix getTranslationMatrix3D(Vector t) {
        return new Array2DRowRealMatrix(
                new double[][]{
                        {1, 0, 0, t.x()},
                        {0, 1, 0, t.y()},
                        {0, 0, 1, t.z()},
                        {0, 0, 0, 1}
                }
        );
    }

    private static RealMatrix getCameraRotation(Vector vpn, Vector vup) {
        Vector n = vpn.normalize();
        Vector u = vup.crossProduct(vpn).normalize();
        Vector v = n.crossProduct(u);
        return new Array2DRowRealMatrix(
                new double[][]{
                        {u.x(), u.y(), u.z(), 0},
                        {v.x(), v.y(), v.z(), 0},
                        {n.x(), n.y(), n.z(), 0},
                        {0, 0, 0, 1}
                }
        );
    }

    public static RealMatrix get3DMatrix(Arguments args) {
        RealMatrix viewMatrix = getViewMatrix(args.getVPN(), args.getVUP(), args.getVRP());
        RealMatrix projectionMatrix;
        if (args.isParallelProjection()) {
            projectionMatrix = getOrthographicProjectionMatrix(args.getuMax(), args.getuMin(), args.getvMax(), args.getvMin(), args.getFront(), args.getBack(), args.getPRP());
        } else {
            projectionMatrix = getPerspectiveProjectionMatrix(args.getuMax(), args.getuMin(), args.getvMax(), args.getvMin(), args.getBack(), args.getPRP());
        }
        return projectionMatrix.multiply(viewMatrix);
    }

    private static RealMatrix getViewMatrix(Vector vpn, Vector vup, Vector vrp) {
        return getCameraRotation(vpn, vup).multiply(getTranslationMatrix3D(vrp.multiply(-1)));
    }

    private static RealMatrix getPerspectiveProjectionMatrix(float uMax, float uMin, float vMax, float vMin, float back, Vector prp) {
        return new Array2DRowRealMatrix(
                new double[][]{
                        {(2 * prp.z()) / ((uMax - uMin) * (prp.z() - back)), 0, ((uMax + uMin) - 2 * prp.x()) / ((uMax - uMin) * (prp.z() - back)), -(((uMax + uMin) * prp.z()) / ((uMax - uMin) * (prp.z() - back)))},
                        {0, (2 * prp.z()) / ((vMax - vMin) * (prp.z() - back)), ((vMax + vMin) - 2 * prp.y()) / ((vMax - vMin) * (prp.z() - back)), -(((vMax + vMin) * prp.z()) / ((vMax - vMin) * (prp.z() - back)))},
                        {0, 0, 1 / (prp.z() - back), -(prp.z() / (prp.z() - back))},
                        {0, 0, 0, 1}
                }
        );
    }

    private static RealMatrix getOrthographicProjectionMatrix(float uMax, float uMin, float vMax, float vMin, float front, float back, Vector prp) {
        return new Array2DRowRealMatrix(
                new double[][]{
                        {2 / (uMax - uMin), 0, ((uMax + uMin) - 2 * prp.x()) / ((uMax - uMin) * prp.z()), -((uMax + uMin) / 2)},
                        {0, 2 / (vMax - vMin), ((vMax + vMin) - 2 * prp.y()) / ((vMax - vMin) * prp.z()), -((vMax + vMin) / 2)},
                        {0, 0, 1 / (front - back), -(front / (front - back))},
                        {0, 0, 0, 1}
                }
        );
    }
}
