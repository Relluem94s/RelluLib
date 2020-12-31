package de.relluem94.rellulib.vector;

import java.text.DecimalFormat;

public class Vector2i implements IVector {

    public int x;

    public int y;

    public Vector2i(int x, int y) {
        setTo(x, y);
    }

    public Vector2i(Vector2i v) {
        setTo(v);
    }

    public Vector2i() {
        setZero();
    }

    public void setTo(Vector2i v) {
        this.x = v.x;
        this.y = v.y;
    }

    public void setTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setZero() {
        this.x = 0;
        this.y = 0;
    }

    public void negate() {
        this.x = -this.x;
        this.y = -this.y;
    }

    @Override
    public String toString() {
        return "X:" + x + " Y:" + y;
    }

    @Override
    public String toShortString() {
        return "X:" + new DecimalFormat("##.##").format(x) + " Y:" + new DecimalFormat("##.##").format(y);
    }

    @Override
    public String toListString() {
        return "" + x + "," + y;
    }

}
