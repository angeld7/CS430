package edu.drexel.cs430.renderengine.matrix;

import edu.drexel.cs430.renderengine.util.Arguments;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * Created by Angel on 11/8/2016.
 */
public class MatrixGenerator {
    public static RealMatrix generateTransformationMatrix(Arguments args) {
        //Find total composite transformation
        RealMatrix translation = getTranslationMatrix(args.getTranslateX(), args.getTranslateY());
        RealMatrix scale = getScaleMatrix(args.getScale(), args.getScale());
        RealMatrix rotation = getRotationMatrix(Math.toRadians(args.getRotation()));
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
        RealMatrix translate1 = getTranslationMatrix(uMin, vMin);
        RealMatrix worldScale = getScaleMatrix(
                (uMax - uMin) / (xMax - xMin),
                (vMax - vMin) / (yMax - yMin)
        );
        RealMatrix translate2 = getTranslationMatrix(-xMin, -yMin);
        return translate1.multiply(worldScale.multiply(translate2));
    }

    private static RealMatrix getTranslationMatrix(double x, double y) {
        return new Array2DRowRealMatrix(
                new double[][]{{1, 0, x}, {0, 1, y}, {0, 0, 1}}
        );
    }

    private static RealMatrix getScaleMatrix(double x, double y) {
        return new Array2DRowRealMatrix(
                new double[][]{{x, 0, 0}, {0, y, 0}, {0, 0, 1}}
        );
    }

    private static RealMatrix getRotationMatrix(double theta) {
        return new Array2DRowRealMatrix(
                new double[][]{{Math.cos(theta), -Math.sin(theta), 0}, {Math.sin(theta), Math.cos(theta), 0}, {0, 0, 1}}
        );
    }
}
