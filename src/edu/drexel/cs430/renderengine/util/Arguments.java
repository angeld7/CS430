package edu.drexel.cs430.renderengine.util;

import edu.drexel.cs430.renderengine.geometry.Vector;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Angel on 11/8/2016.
 */
public class Arguments {
    private String filename;
    private String filename2;
    private String filename3;
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
    private float prpX = 0;
    private float prpY = 0;
    private float prpZ = 1;
    private float vrpX = 0;
    private float vrpY = 0;
    private float vrpZ = 0;
    private float vpnX = 0;
    private float vpnY = 0;
    private float vpnZ = -1;
    private float vupX = 0;
    private float vupY = 1;
    private float vupZ = 0;
    private float uMin = -0.7f;
    private float vMin = -0.7f;
    private float uMax = 0.7f;
    private float vMax = 0.7f;
    private float front = 0.6f;
    private float back = -0.6f;
    private boolean parallelProjection = false;
    private boolean display = false;

    public String getFilename() {
        return filename;
    }

    public boolean is3d() {
        return filename != null && ".smf".equals(filename.substring(filename.length() - 4).toLowerCase());
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<String> getFilenames() {
        return Arrays.asList(filename, filename2, filename3);
    }

    public String getFilename2() {
        return filename2;
    }

    public void setFilename2(String filename2) {
        this.filename2 = filename2;
    }

    public String getFilename3() {
        return filename3;
    }

    public void setFilename3(String filename3) {
        this.filename3 = filename3;
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

    public float getPrpX() {
        return prpX;
    }

    public void setPrpX(float prpX) {
        this.prpX = prpX;
    }

    public float getPrpY() {
        return prpY;
    }

    public void setPrpY(float prpY) {
        this.prpY = prpY;
    }

    public float getPrpZ() {
        return prpZ;
    }

    public void setPrpZ(float prpZ) {
        this.prpZ = prpZ;
    }

    public float getVrpX() {
        return vrpX;
    }

    public void setVrpX(float vrpX) {
        this.vrpX = vrpX;
    }

    public float getVrpY() {
        return vrpY;
    }

    public void setVrpY(float vrpY) {
        this.vrpY = vrpY;
    }

    public float getVrpZ() {
        return vrpZ;
    }

    public void setVrpZ(float vrpZ) {
        this.vrpZ = vrpZ;
    }

    public float getVpnX() {
        return vpnX;
    }

    public void setVpnX(float vpnX) {
        this.vpnX = vpnX;
    }

    public float getVpnY() {
        return vpnY;
    }

    public void setVpnY(float vpnY) {
        this.vpnY = vpnY;
    }

    public float getVpnZ() {
        return vpnZ;
    }

    public void setVpnZ(float vpnZ) {
        this.vpnZ = vpnZ;
    }

    public float getVupX() {
        return vupX;
    }

    public void setVupX(float vupX) {
        this.vupX = vupX;
    }

    public float getVupY() {
        return vupY;
    }

    public void setVupY(float vupY) {
        this.vupY = vupY;
    }

    public float getVupZ() {
        return vupZ;
    }

    public void setVupZ(float vupZ) {
        this.vupZ = vupZ;
    }

    public float getuMin() {
        return uMin;
    }

    public void setuMin(float uMin) {
        this.uMin = uMin;
    }

    public float getvMin() {
        return vMin;
    }

    public void setvMin(float vMin) {
        this.vMin = vMin;
    }

    public float getuMax() {
        return uMax;
    }

    public void setuMax(float uMax) {
        this.uMax = uMax;
    }

    public float getvMax() {
        return vMax;
    }

    public void setvMax(float vMax) {
        this.vMax = vMax;
    }

    public boolean isParallelProjection() {
        return parallelProjection;
    }

    public float getFront() {
        return front;
    }

    public void setFront(float front) {
        this.front = front;
    }

    public float getBack() {
        return back;
    }

    public void setBack(float back) {
        this.back = back;
    }

    public void setParallelProjection(boolean parrallelProjection) {
        this.parallelProjection = parrallelProjection;
    }

    public Vector getPRP() {
        return new Vector(prpX, prpY, prpZ);
    }

    public Vector getVRP() {
        return new Vector(vrpX, vrpY, vrpZ);
    }

    public Vector getVPN() {
        return new Vector(vpnX, vpnY, vpnZ);
    }

    public Vector getVUP() {
        return new Vector(vupX, vupY, vupZ);
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }
}
