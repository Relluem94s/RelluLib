package de.relluem94.rellulib.vector;

import java.text.DecimalFormat;

public class Vector4f implements IVector {

    public float x;

    public float y;

    public float z;

    public float w;

    public Vector4f(float x, float y, float z, float w) {
        setTo(x, y, z, w);
    }

    public Vector4f(Vector4f v) {
        setTo(v);
    }

    public Vector4f() {
        setZero();
    }

    public void setTo(Vector4f v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        this.w = v.w;
    }

    public void setTo(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public void setZero() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.w = 0.0f;
    }

    public void negate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
        this.w = -this.w;
    }

    @Override
    public String toString() {
        return new String("X:" + x + " Y:" + y + " Z:" + z + " W:" + w);
    }

    @Override
    public String toShortString() {
        return "X:" + new DecimalFormat("##.##").format(x) + " Y:" + new DecimalFormat("##.##").format(y)
                + " Z:" + new DecimalFormat("##.##").format(z) + " W:" + new DecimalFormat("##.##").format(w);
    }

    @Override
    public String toListString() {
        return "" + x + "," + y + "," + z + "," + w;
    }

}
