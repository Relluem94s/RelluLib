package de.relluem94.rellulib.color;

@SuppressWarnings("unused")
public class Color4f implements ColorF<Color4f> {

    public static final Color4f BLACK = new Color4f(0.0F, 0.0F, 0.0F, 1.0F);
    public static final Color4f WHITE = new Color4f(1.0F, 1.0F, 1.0F, 1.0F);
    public static final Color4f BLUE = new Color4f(0.0F, 0.0F, 1.0F, 1.0F);
    public static final Color4f RED = new Color4f(1.0F, 0.0F, 0.0F, 1.0F);
    public static final Color4f GREEN = new Color4f(0.0F, 1.0F, 0.0F, 1.0F);
    public static final Color4f GRAY = new Color4f(0.5F, 0.5F, 0.5F, 1.0F);

    public Color4f(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public Color4f(Color4f color) {
        this.r = color.r;
        this.g = color.g;
        this.b = color.b;
        this.a = color.a;
    }

    public float r;
    public float g;
    public float b;
    public float a;

    @Override
    public Color4f getColor() {
        return this;
    }

    @Override
    public String toString() {
        return "r" + r + ", g" + g + ", b" + b + ", a" + a;
    }

    @Override
    public String toIntString() {
        return r + "," + g + "," + b + "," + a;
    }

    @Override
    public float[] toArray() {
        float[] array = new float[4];
        array[0] = r;
        array[1] = g;
        array[2] = b;
        array[3] = a;
        return array;
    }
}
