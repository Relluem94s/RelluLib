package de.relluem94.rellulib.vector;

import java.text.DecimalFormat;

public class Vector3f implements IVector {

    private float x;
    private float y;
    private float z;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f(Vector3f v) {
        this.x = v.x;
        this.y = v.y;
    }

    public Vector3f() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
    }

    public void setTo(Vector3f v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    public void setTo(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setZero() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
    }

    public void negate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
    }

    @Override
    public String toString() {
        return "X:" + x + " Y:" + y + " Z:" + z;
    }

    @Override
    public String toShortString() {
        return "X:" + new DecimalFormat(VectorConstants.DECIMAL_FORMAT_PATTERN).format(x) + " Y:" + new DecimalFormat(VectorConstants.DECIMAL_FORMAT_PATTERN).format(y) + " Z:" + new DecimalFormat(VectorConstants.DECIMAL_FORMAT_PATTERN).format(z);
    }

    @Override
    public String toListString() {
        return "" + x + "," + y + "," + z;
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
}
