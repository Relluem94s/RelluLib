package de.relluem94.rellulib.utils;

import java.text.DecimalFormat;

import de.relluem94.rellulib.color.Color3f;
import de.relluem94.rellulib.color.Color3i;
import de.relluem94.rellulib.color.Color4f;
import de.relluem94.rellulib.color.Color4i;
import de.relluem94.rellulib.vector.*;

public class TypeUtils {

    private TypeUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String convertInt2Hex(int n) {
        return Integer.toHexString(n).toUpperCase();
    }
    
    public static int convertHex2Int(int n) {
        return Integer.valueOf(String.valueOf(n), 10);
    }
    
    public static Float toFloat(String input) {
        if (isFloat(input)) {
            return Float.parseFloat(input);
        } else {
            return 0f;
        }
    }

    public static Integer toInt(String input) {
        if (isInt(input)) {
            return Integer.parseInt(input);
        } else {
            return 0;
        }
    }

    public static boolean toBoolean(String input) {
        input = input.replaceAll("[\\\t|\\\n|\\\r]", "");
        return input.equalsIgnoreCase("true");
    }

    public static Vector1<Float> toVector1f(String input) {
        float[] vals = toFloatArray(input, 1);
        return vals.length == 1 ? new Vector1<>(vals[0]) : new Vector1<>(0f);
    }

    public static Vector2<Float> toVector2f(String input) {
        float[] vals = toFloatArray(input, 2);
        return vals.length == 2 ? new Vector2<>(vals[0], vals[1]) : new Vector2<>(0f, 0f);
    }

    public static Vector3<Float> toVector3f(String input) {
        float[] vals = toFloatArray(input, 3);
        return vals.length == 3 ? new Vector3<>(vals[0], vals[1], vals[2]) : new Vector3<>(0f, 0f, 0f);
    }

    public static Vector4<Float> toVector4f(String input) {
        float[] vals = toFloatArray(input, 4);
        return vals.length == 4 ? new Vector4<>(vals[0], vals[1], vals[2], vals[3]) : new Vector4<>(0f, 0f, 0f, 0f);
    }

    public static Vector5<Float> toVector5f(String input) {
        float[] vals = toFloatArray(input, 5);
        return vals.length == 5 ? new Vector5<>(vals[0], vals[1], vals[2], vals[3], vals[4]) : new Vector5<>(0f, 0f, 0f, 0f, 0f);
    }

    public static Vector1<Integer> toVector1i(String input) {
        int[] vals = toIntArray(input, 1);
        return vals.length == 1 ? new Vector1<>(vals[0]) : new Vector1<>(0);
    }

    public static Vector2<Integer> toVector2i(String input) {
        int[] vals = toIntArray(input, 2);
        return vals.length == 2 ? new Vector2<>(vals[0], vals[1]) : new Vector2<>(0, 0);
    }

    public static Vector3<Integer> toVector3i(String input) {
        int[] vals = toIntArray(input, 3);
        return vals.length == 3 ? new Vector3<>(vals[0], vals[1], vals[2]) : new Vector3<>(0, 0, 0);
    }

    public static Vector4<Integer> toVector4i(String input) {
        int[] vals = toIntArray(input, 4);
        return vals.length == 4 ? new Vector4<>(vals[0], vals[1], vals[2], vals[3]) : new Vector4<>(0, 0, 0, 0);
    }

    public static Vector5<Integer> toVector5i(String input) {
        int[] vals = toIntArray(input, 5);
        return vals.length == 5 ? new Vector5<>(vals[0], vals[1], vals[2], vals[3], vals[4]) : new Vector5<>(0, 0, 0, 0, 0);
    }

    private static float[] toFloatArray(String input, int size) {
        String[] parts = input.split(",");
        float[] result = new float[size];
        for (int i = 0; i < parts.length; i++) {
            result[i] = Float.parseFloat(parts[i].trim());
        }
        return result;
    }

    private static int[] toIntArray(String input, int size) {
        String[] parts = input.split(",");
        int[] result = new int[size];
        for (int i = 0; i < parts.length; i++) {
            result[i] = Integer.parseInt(parts[i].trim());
        }
        return result;
    }

    public static Color3f toColor3f(String input) {
        String[] color = input.split(",");
        if (color.length == 3) {
            float r = toFloat(color[0]);
            float g = toFloat(color[1]);
            float b = toFloat(color[2]);
            return new Color3f(r, g, b);
        } else {
            return new Color3f(0, 0, 0);
        }
    }

    public static Color3f toColor3f(int color) {
        int b = color & 0xff;
        int g = (color & 0xff00) >> 8;
        int r = (color & 0xff0000) >> 16;

        return new Color3f(r, g, b);
    }

    public static Color4f toColor4f(int color) {
        int b = color & 0xff;
        int g = (color & 0xff00) >> 8;
        int r = (color & 0xff0000) >> 16;
        int a = (color & 0xff000000) >> 24;

        return new Color4f(r, g, b, a);
    }

    public static Color3i toColor3i(String input) {
        String[] color = input.split(",");
        if (color.length == 3) {
            int r = toInt(color[0]);
            int g = toInt(color[1]);
            int b = toInt(color[2]);
            return new Color3i(r, g, b);
        } else {
            return new Color3i(0, 0, 0);
        }
    }

    public static Color4f toColor4f(String input) {
        String[] color = input.split(",");
        if (color.length == 4) {
            float r = toFloat(color[0]);
            float g = toFloat(color[1]);
            float b = toFloat(color[2]);
            float a = toFloat(color[3]);
            return new Color4f(r, g, b, a);
        } else {
            return new Color4f(0, 0, 0, 0);
        }
    }

    public static Color4i toColor4i(String input) {
        String[] color = input.split(",");
        if (color.length == 4) {
            int r = toInt(color[0]);
            int g = toInt(color[1]);
            int b = toInt(color[2]);
            int a = toInt(color[3]);
            return new Color4i(r, g, b, a);
        } else {
            return new Color4i(0, 0, 0, 0);
        }
    }

    public static boolean isInt(String s) {
        try {
            @SuppressWarnings("unused")
            int i = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException er) {
            return false;
        }
    }

    public static boolean isFloat(String s) {
        try {
            @SuppressWarnings("unused")
            float f = Float.parseFloat(s);
            return true;
        } catch (NumberFormatException er) {
            return false;
        }
    }

    private static final DecimalFormat df = new DecimalFormat();

    public static String longToKB(long l) {
        return df.format((float) l / 1000) + "kb";
    }

    public static String longToSeconds(long time) {
        return df.format((float) time / 1000);
    }

    public static String longToMin(long time) {
        return df.format((float) time / 1000 / 60);
    }
}
