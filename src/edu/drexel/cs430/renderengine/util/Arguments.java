package edu.drexel.cs430.renderengine.util;

/**
 * Created by Angel on 11/8/2016.
 */
public class Arguments {
    private String filename;
    private double scale = 1;
    private double rotation = 0;
    private int translateX = 0;
    private int translateY = 0;
    private int lowerWorldX = 0;
    private int lowerWorldY = 0;
    private int upperWorldX = 499;
    private int upperWorldY = 499;
    private int lowerViewX = 0;
    private int lowerViewY = 0;
    private int upperViewX = 499;
    private int upperViewY = 499;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public int getTranslateX() {
        return translateX;
    }

    public void setTranslateX(int translateX) {
        this.translateX = translateX;
    }

    public int getTranslateY() {
        return translateY;
    }

    public void setTranslateY(int translateY) {
        this.translateY = translateY;
    }

    public int getLowerWorldX() {
        return lowerWorldX;
    }

    public void setLowerWorldX(int lowerWorldX) {
        this.lowerWorldX = lowerWorldX;
    }

    public int getLowerWorldY() {
        return lowerWorldY;
    }

    public void setLowerWorldY(int lowerWorldY) {
        this.lowerWorldY = lowerWorldY;
    }

    public int getUpperWorldX() {
        return upperWorldX;
    }

    public void setUpperWorldX(int upperWorldX) {
        this.upperWorldX = upperWorldX;
    }

    public int getUpperWorldY() {
        return upperWorldY;
    }

    public void setUpperWorldY(int upperWorldY) {
        this.upperWorldY = upperWorldY;
    }

    public int getLowerViewX() {
        return lowerViewX;
    }

    public void setLowerViewX(int lowerViewX) {
        this.lowerViewX = lowerViewX;
    }

    public int getLowerViewY() {
        return lowerViewY;
    }

    public void setLowerViewY(int lowerViewY) {
        this.lowerViewY = lowerViewY;
    }

    public int getUpperViewX() {
        return upperViewX;
    }

    public void setUpperViewX(int upperViewX) {
        this.upperViewX = upperViewX;
    }

    public int getUpperViewY() {
        return upperViewY;
    }

    public void setUpperViewY(int upperViewY) {
        this.upperViewY = upperViewY;
    }
}
