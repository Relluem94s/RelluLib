package de.relluem94.rellulib.vector;

import java.text.DecimalFormat;

@Deprecated
public class Vector5i implements IVector {

    private int x;
    private int y;
    private int z;
    private int w;
    private int v;

    public Vector5i(int x, int y, int z, int w, int v) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.v = v;
    }

    public Vector5i(Vector5i v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        this.w = v.w;
        this.v = v.v;
    }

    public Vector5i() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.w = 0;
        this.v = 0;
    }

    public void setTo(Vector5i v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        this.w = v.w;
        this.v = v.v;
    }

    public void setTo(int x, int y, int z, int w, int v) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.v = v;
    }

    public void setZero() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.w = 0;
        this.v = 0;
    }

    public void negate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
        this.w = -this.w;
        this.v = -this.v;
    }

    @Override
    public String toString() {
        return "X:" + x + " Y:" + y + " Z:" + z + " W:" + w + " V:" + v;
    }

    @Override
    public String toShortString() {
        return "X:" + new DecimalFormat(VectorConstants.DECIMAL_FORMAT_PATTERN).format(x) + " Y:" + new DecimalFormat(VectorConstants.DECIMAL_FORMAT_PATTERN).format(y)
                + " Z:" + new DecimalFormat(VectorConstants.DECIMAL_FORMAT_PATTERN).format(z) + " W:" + new DecimalFormat(VectorConstants.DECIMAL_FORMAT_PATTERN).format(w)
                + " V:" + new DecimalFormat(VectorConstants.DECIMAL_FORMAT_PATTERN).format(v);
    }

    @Override
    public String toListString() {
        return x + "," + y + "," + z + "," + w + "," + v;
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

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }
}
