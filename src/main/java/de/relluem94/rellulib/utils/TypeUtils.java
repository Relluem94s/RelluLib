package de.relluem94.rellulib.utils;

import java.text.DecimalFormat;

import de.relluem94.rellulib.color.Color3f;
import de.relluem94.rellulib.color.Color3i;
import de.relluem94.rellulib.color.Color4f;
import de.relluem94.rellulib.color.Color4i;
import de.relluem94.rellulib.vector.Vector2f;
import de.relluem94.rellulib.vector.Vector2i;
import de.relluem94.rellulib.vector.Vector3f;
import de.relluem94.rellulib.vector.Vector3i;
import de.relluem94.rellulib.vector.Vector4f;
import de.relluem94.rellulib.vector.Vector4i;
import de.relluem94.rellulib.vector.Vector5f;
import de.relluem94.rellulib.vector.Vector5i;

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

    public static Vector2f toVector2f(String input) {
        String[] vector = input.split(",");
        if (vector.length == 2) {
            float x = toFloat(vector[0]);
            float y = toFloat(vector[1]);
            return new Vector2f(x, y);
        } else {
            return new Vector2f();
        }
    }

    public static Vector3f toVector3f(String input) {
        String[] vector = input.split(",");
        if (vector.length == 3) {
            float x = toFloat(vector[0]);
            float y = toFloat(vector[1]);
            float z = toFloat(vector[2]);
            return new Vector3f(x, y, z);
        } else {
            return new Vector3f();
        }
    }

    public static Vector4f toVector4f(String input) {
        String[] vector = input.split(",");
        if (vector.length == 4) {
            float x = toFloat(vector[0]);
            float y = toFloat(vector[1]);
            float z = toFloat(vector[2]);
            float w = toFloat(vector[3]);
            return new Vector4f(x, y, z, w);
        } else {
            return new Vector4f();
        }
    }

    public static Vector5f toVector5f(String input) {
        String[] vector = input.split(",");
        if (vector.length == 5) {
            float x = toFloat(vector[0]);
            float y = toFloat(vector[1]);
            float z = toFloat(vector[2]);
            float w = toFloat(vector[3]);
            float v = toFloat(vector[4]);
            return new Vector5f(x, y, z, w, v);
        } else {
            return new Vector5f();
        }
    }

    public static Vector2i toVector2i(String input) {
        String[] vector = input.split(",");
        if (vector.length == 2) {
            int x = toInt(vector[0]);
            int y = toInt(vector[1]);
            return new Vector2i(x, y);
        } else {
            return new Vector2i();
        }
    }

    public static Vector3i toVector3i(String input) {
        String[] vector = input.split(",");
        if (vector.length == 3) {
            int x = toInt(vector[0]);
            int y = toInt(vector[1]);
            int z = toInt(vector[2]);
            return new Vector3i(x, y, z);
        } else {
            return new Vector3i();
        }
    }

    public static Vector4i toVector4i(String input) {
        String[] vector = input.split(",");
        if (vector.length == 4) {
            int x = toInt(vector[0]);
            int y = toInt(vector[1]);
            int z = toInt(vector[2]);
            int w = toInt(vector[3]);
            return new Vector4i(x, y, z, w);
        } else {
            return new Vector4i();
        }
    }

    public static Vector5i toVector5i(String input) {
        String[] vector = input.split(",");
        if (vector.length == 5) {
            int x = toInt(vector[0]);
            int y = toInt(vector[1]);
            int z = toInt(vector[2]);
            int w = toInt(vector[3]);
            int v = toInt(vector[4]);
            return new Vector5i(x, y, z, w, v);
        } else {
            return new Vector5i();
        }
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
