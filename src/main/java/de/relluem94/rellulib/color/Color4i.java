package de.relluem94.rellulib.color;

@SuppressWarnings("unused")
public class Color4i implements ColorI<Color4i>{

    public static final Color4i BLACK = new Color4i(0, 0, 0, 0);
    public static final Color4i WHITE = new Color4i(255, 255, 255, 0);
    public static final Color4i BLUE = new Color4i(0, 0, 255, 0);
    public static final Color4i RED = new Color4i(255, 0, 0, 0);
    public static final Color4i GREEN = new Color4i(0, 255, 0, 0);
    public static final Color4i GRAY = new Color4i(128, 128, 128, 0);
    public static final Color4i LIGHT_GREY = new Color4i(200, 200, 200, 0);
    public static final Color4i LIME = new Color4i(127, 255, 0, 0);
    public static final Color4i PURPLE = new Color4i(139, 0, 139, 0);
    public static final Color4i LIGHT_PURPLE = new Color4i(255, 0, 220, 0);
    public static final Color4i BROWN = new Color4i(139, 69, 19, 0);

    public static final Color4i RELLU_RED = new Color4i(227, 59, 46, 0);
    public static final Color4i RELLU_GREEN = new Color4i(152, 216, 1, 0);
    public static final Color4i RELLU_ORANGE = new Color4i(243, 125, 0, 0);
    public static final Color4i RELLU_BLUE = new Color4i(72, 179, 177, 0);
    public static final Color4i RELLU_GRAY = new Color4i(121, 116, 114, 0);

    public Color4i(int r, int g, int b, int a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public int r;
    public int g;
    public int b;
    public int a;

    @Override
    public Color4i getColor() {
        return this;
    }

    @Override
    public int[] toArray() {
        int[] array = new int[4];
        array[0] = r;
        array[1] = g;
        array[2] = b;
        array[3] = a;
        return array;
    }

    @Override
    public String toString() {
        return "r" + r + " g" + g + " b" + b + " a" + a;
    }

    @Override
    public String toIntString() {
        return r + "," + g + "," + b + "," + a;
    }
}
