package de.relluem94.rellulib.vector;

import java.text.DecimalFormat;

public class Vector3i implements IVector {

    private int x;
    private int y;
    private int z;

    public Vector3i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3i(Vector3i v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    public Vector3i() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public void setTo(Vector3i v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    public void setTo(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setZero() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
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
}
