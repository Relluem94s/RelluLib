package de.relluem94.rellulib.vector;

import java.text.DecimalFormat;

public class Vector3i implements IVector {

    public int x;

    public int y;

    public int z;

    public Vector3i(int x, int y, int z) {
        setTo(x, y, z);
    }

    public Vector3i(Vector3i v) {
        setTo(v);
    }

    public Vector3i() {
        setZero();
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
        return new String("X:" + x + " Y:" + y + " Z:" + z);
    }

    @Override
    public String toShortString() {
        return "X:" + new DecimalFormat("##.##").format(x) + " Y:" + new DecimalFormat("##.##").format(y) + " Z:" + new DecimalFormat("##.##").format(z);
    }

    @Override
    public String toListString() {
        return "" + x + "," + y + "," + z;
    }

}
