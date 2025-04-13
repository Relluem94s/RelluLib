package de.relluem94.rellulib.vector;

import java.text.DecimalFormat;

public class Vector2f implements IVector {

    private float x;
    private float y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f(Vector2f v) {
        this.x = v.x;
        this.y = v.y;
    }

    public Vector2f() {
        this.x = 0;
        this.y = 0;
    }

    public void setTo(Vector2f v) {
        this.x = v.x;
        this.y = v.y;
    }

    public void setTo(float x, float y) {
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
        return "X:" + new DecimalFormat(VectorConstants.DECIMAL_FORMAT_PATTERN).format(x) + " Y:" + new DecimalFormat(VectorConstants.DECIMAL_FORMAT_PATTERN).format(y);
    }

    @Override
    public String toListString() {
        return x + "," + y;
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
}
