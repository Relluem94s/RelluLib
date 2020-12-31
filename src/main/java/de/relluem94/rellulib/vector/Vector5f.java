package de.relluem94.rellulib.vector;

import java.text.DecimalFormat;

public class Vector5f implements IVector {

    public float x;

    public float y;

    public float z;

    public float w;

    public float v;

    public Vector5f(float x, float y, float z, float w, float v) {
        setTo(x, y, z, w, v);
    }

    public Vector5f(Vector5f v) {
        setTo(v);
    }

    public Vector5f() {
        setZero();
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
        return new String("X:" + x + " Y:" + y + " Z:" + z + " W:" + w + " V:" + v);
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

}
