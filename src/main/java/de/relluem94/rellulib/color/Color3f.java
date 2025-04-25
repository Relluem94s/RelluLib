package de.relluem94.rellulib.color;

@SuppressWarnings("unused")
public class Color3f implements ColorF<Color3f> {

    public static final Color3f BLACK = new Color3f(0.0F, 0.0F, 0.0F);
    public static final Color3f WHITE = new Color3f(1.0F, 1.0F, 1.0F);
    public static final Color3f BLUE = new Color3f(0.0F, 0.0F, 1.0F);
    public static final Color3f RED = new Color3f(1.0F, 0.0F, 0.0F);
    public static final Color3f YELLOW = new Color3f(1.0F, 1.0F, 0.0F);
    public static final Color3f GREEN = new Color3f(0.0F, 1.0F, 0.0F);
    public static final Color3f GREY = new Color3f(0.5F, 0.5F, 0.5F);
    public static final Color3f LIGHT_GREY = new Color3f(0.5F, 0.5F, 0.5F);

    public Color3f(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color3f(Color3f color) {
        this.r = color.r;
        this.g = color.g;
        this.b = color.b;
    }

    public final float r;
    public final float g;
    public final float b;

    public Color3f getColor() {
        return this;
    }

    public String toString() {
        return "r" + r + ", g" + g + ", b" + b;
    }

    public String toIntString() {
        return r + "," + g + "," + b;
    }

    @Override
    public float[] toArray() {
        float[] array = new float[3];
        array[0] = r;
        array[1] = g;
        array[2] = b;
        return array;
    }
}
