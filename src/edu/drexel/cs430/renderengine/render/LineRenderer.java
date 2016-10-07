package edu.drexel.cs430.renderengine.render;

import edu.drexel.cs430.renderengine.shapes.Line;
import edu.drexel.cs430.renderengine.shapes.Point;

/**
 * Created by Angel on 9/25/2016.
 */
public class LineRenderer {

    private boolean[][] pixelMatrix;
    private int height,width;

    public LineRenderer(int canvasX, int canvasY) {
        pixelMatrix = new boolean[canvasY][canvasX];
        height = canvasY;
        width = canvasX;
    }

    public void drawLine(Line line) {
        if(!clipLine(line)) return;
        Point start = line.getStartPoint();
        Point end = line.getEndPoint();
        if (start.getX() == end.getX()) {
            //Horizontal line
            float x = start.getX();
            float startY = Math.min(start.getY(), end.getY());
            float endY = Math.max(start.getY(), end.getY());
            for (float y = startY; y <= endY; y++) {
                pixelMatrix[Math.round(y)][Math.round(x)] = true;
            }
        } else if (start.getY() == end.getY()) {
            //Vertical line
            float y = start.getY();
            float startX = Math.min(start.getX(), end.getX());
            float endX = Math.max(start.getX(), end.getX());
            for (float x = startX; x <= endX; x++) {
                pixelMatrix[Math.round(y)][Math.round(x)] = true;
            }
        } else {
            float slope = (end.getY() - start.getY()) / (float) (end.getX() - start.getX());
            float dx = Math.abs(end.getX() - start.getX());
            float dy = Math.abs(end.getY() - start.getY());
            float D = 2 * dy - dx;
            if (slope > 0 && slope <= 1) {
                if (start.getX() > end.getX()) swap(start, end);
                float y = start.getY();
                for (float x = start.getX(); x <= end.getX(); x++) {
                    pixelMatrix[Math.round(y)][Math.round(x)] = true;
                    if ((D = nextD(D, dx, dy)) > 0) y++;
                }
            } else if (slope > 1) {
                if (start.getY() > end.getY()) swap(start, end);
                float x = start.getX();
                for (float y = start.getY(); y <= end.getY(); y++) {
                    pixelMatrix[Math.round(y)][Math.round(x)] = true;
                    if ((D = nextD(D, dy, dx)) > 0) x++;
                }
            } else if (slope < 0 && slope >= -1) {
                if (start.getX() > end.getX()) swap(start, end);
                float y = start.getY();
                for (float x = start.getX(); x <= end.getX(); x++) {
                    pixelMatrix[Math.round(y)][Math.round(x)] = true;
                    if ((D = nextD(D, dx, dy)) > 0) y--;
                }
            } else {
                if (start.getY() > end.getY()) swap(start, end);
                float x = start.getX();
                for (float y = start.getY(); y <= end.getY(); y++) {
                    pixelMatrix[Math.round(y)][Math.round(x)] = true;
                    if ((D = nextD(D, dy, dx)) > 0) x--;
                }
            }
        }
    }

    private float nextD(float D, float n1, float n2) {
        D += D <= 0 ? 2 * n2 : 2 * (n2 - n1);
        return D;
    }

    private void swap(Point s, Point e) {
        float tX = s.getX(), tY = s.getY();
        s.setX(e.getX());
        s.setY(e.getY());
        e.setX(tX);
        e.setY(tY);
    }

    private boolean clipLine(Line line){
        Point s = line.getStartPoint(), e = line.getEndPoint();
        int b1 = getBitCode(s), b2 = getBitCode(e);
        if((b1 & b2) != 0) return false;
        if((b1 | b2) != 0) {
            if(b1 != 0) clipPoint(s, e, b1);
            if(b2 != 0) clipPoint(e, s, b2);
        }
        return true;
    }

    private Point clipPoint(Point point, Point ref, int bitCode) {
        for(int b = 1; b <= 8; b *= 2) {
            if((b | bitCode) == b) movePoint(point, ref, b);
        }
        return point;
    }

    private void movePoint(Point point, Point ref, int bit) {
        float x1 = point.getX(), y1 = point.getY(), x2 = ref.getX(), y2 = ref.getY();
        switch(bit) {
            case 1:
                point.setY(proportion(x1,y1,x2,y2,0));
                point.setX(0);
                break;
            case 2:
                point.setY(proportion(x1,y1,x2,y2,width-1));
                point.setX(width-1);
                break;
            case 4:
                point.setX(proportion(y1, x1, y2, x2, height - 1));
                point.setY(height-1);
                break;
            case 8:
                point.setX(proportion(y1,x1,y2,x2,0));
                point.setY(0);
                break;
        }
    }

    private float proportion(float a, float b, float c, float d, float l) {
        return (l-a / (a - c)) * (d - b) + b;
    }

    private int getBitCode(Point point) {
        int bitCode = 0;
        if(point.getX() < 0) {
            bitCode |= 1;
        } else if(point.getX() >= width) {
            bitCode |= 2;
        }
        if(point.getY() < 0) {
            bitCode |= 8;
        } else if(point.getY() >= height) {
            bitCode |= 4;
        }
        return bitCode;
    }

    public boolean[][] getPixelMatrix() {
        return pixelMatrix;
    }
}
