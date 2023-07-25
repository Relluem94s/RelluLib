package de.relluem94.rellulib.vector;

import java.text.DecimalFormat;

public class Vector5f implements IVector {

    private float x;
    private float y;
    private float z;
    private float w;
    private float v;

    public Vector5f(float x, float y, float z, float w, float v) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.v = v;
    }

    public Vector5f(Vector5f v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        this.w = v.w;
        this.v = v.v;
    }

    public Vector5f() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.w = 0.0f;
        this.v = 0.0f;
    }

    public void setTo(Vector5f v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        this.w = v.w;
        this.v = v.v;
    }

    public void setTo(float x, float y, float z, float w, float v) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.v = v;
    }

    public void setZero() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.w = 0.0f;
        this.v = 0.0f;
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
        return "X:" + new DecimalFormat("##.##").format(x) + " Y:" + new DecimalFormat("##.##").format(y)
                + " Z:" + new DecimalFormat("##.##").format(z) + " W:" + new DecimalFormat("##.##").format(w)
                + " V:" + new DecimalFormat("##.##").format(v);
    }

    @Override
    public String toListString() {
        return "" + x + "," + y + "," + z + "," + w + "," + v;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    public float getV() {
        return v;
    }

    public void setV(float v) {
        this.v = v;
    }
}
