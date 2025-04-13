package de.relluem94.rellulib.vector;

import java.text.DecimalFormat;

public class Vector4i implements IVector {

    private int x;
    private int y;
    private int z;
    private int w;

    public Vector4i(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4i(Vector4i v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        this.w = v.w;
    }

    public Vector4i() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.w = 0;
    }

    public void setTo(Vector4i v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        this.w = v.w;
    }

    public void setTo(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public void setZero() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.w = 0;
    }

    public void negate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
        this.w = -this.w;
    }

    @Override
    public String toString() {
        return "X:" + x + " Y:" + y + " Z:" + z + " W:" + w;
    }

    @Override
    public String toShortString() {
        return "X:" + new DecimalFormat(VectorConstants.DECIMAL_FORMAT_PATTERN).format(x) + " Y:" + new DecimalFormat(VectorConstants.DECIMAL_FORMAT_PATTERN).format(y)
                + " Z:" + new DecimalFormat(VectorConstants.DECIMAL_FORMAT_PATTERN).format(z) + " W:" + new DecimalFormat(VectorConstants.DECIMAL_FORMAT_PATTERN).format(w);
    }

    @Override
    public String toListString() {
        return x + "," + y + "," + z + "," + w;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }
}
