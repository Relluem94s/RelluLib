package de.relluem94.rellulib.vector;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class VectorMath {

    public static Vector3f add(Vector3f v1, Vector3f v2) {
        return (new Vector3f(v1.getX() + v2.getX(), v1.getY() + v2.getY(), v1.getZ() + v2.getZ()));
    }

    public static Vector4f add(Vector4f v1, Vector4f v2) {
        return (new Vector4f(v1.getX() + v2.getX(), v1.getY() + v2.getY(), v1.getZ() + v2.getZ(), v1.getW() + v2.getW()));
    }

    public static Vector3f add(Vector3f v1, float x, float y, float z) {
        return (new Vector3f(v1.getX() + x, v1.getY() + y, v1.getZ() + z));
    }

    public static Vector4f add(Vector4f v1, float x, float y, float z, float w) {
        return (new Vector4f(v1.getX() + x, v1.getY() + y, v1.getZ() + z, v1.getW() + w));
    }

    @Contract("_, _ -> new")
    public static @NotNull Vector3f subtract(@NotNull Vector3f v1, @NotNull Vector3f v2) {
        return (new Vector3f(v1.getX() - v2.getX(), v1.getY() - v2.getY(), v1.getZ() - v2.getZ()));
    }

    @Contract("_, _, _, _ -> new")
    public static @NotNull Vector3f subtract(@NotNull Vector3f v1, float x, float y, float z) {
        return (new Vector3f(v1.getX() - x, v1.getY() - y, v1.getZ() - z));
    }

    @Contract("_, _ -> new")
    public static @NotNull Vector4f subtract(@NotNull Vector4f v1, @NotNull Vector4f v2) {
        return (new Vector4f(v1.getX() - v2.getX(), v1.getY() - v2.getY(), v1.getZ() - v2.getZ(), v1.getW() - v2.getW()));
    }

    @Contract("_, _, _, _, _ -> new")
    public static @NotNull Vector4f subtract(@NotNull Vector4f v1, float x, float y, float z, float w) {
        return (new Vector4f(v1.getX() - x, v1.getY() - y, v1.getZ() - z, v1.getW() - w));
    }

    @Contract("_, _ -> new")
    public static @NotNull Vector3f multiply(@NotNull Vector3f v1, float m) {
        return (new Vector3f(v1.getX() * m, v1.getY() * m, v1.getZ() * m));
    }

    @Contract("_, _ -> new")
    public static @NotNull Vector4f multiply(@NotNull Vector4f v1, float m) {
        return (new Vector4f(v1.getX() * m, v1.getY() * m, v1.getZ() * m, v1.getW() * m));
    }

    @Contract("_, _ -> new")
    public static @NotNull Vector3f divide(@NotNull Vector3f v1, float m) {
        return (new Vector3f(v1.getX() / m, v1.getY() / m, v1.getZ() / m));
    }

    @Contract("_, _ -> new")
    public static @NotNull Vector4f divide(@NotNull Vector4f v1, float m) {
        return (new Vector4f(v1.getX() / m, v1.getY() / m, v1.getZ() / m, v1.getW() / m));
    }

    public static @NotNull Vector3f crossProduct(@NotNull Vector3f v1, @NotNull Vector3f v2) {
        Vector3f v3 = new Vector3f();
        v3.setX((v1.getY() * v2.getZ()) - (v1.getZ() * v2.getY()));
        v3.setY((v1.getZ() * v2.getX()) - (v1.getX() * v2.getZ()));
        v3.setZ((v1.getX() * v2.getY()) - (v1.getY() * v2.getX()));
        return v3;
    }

    public static @NotNull Vector3f normal(Vector3f @NotNull [] triangle) {
        Vector3f vector1 = subtract(triangle[2], triangle[0]);
        Vector3f vector2 = subtract(triangle[1], triangle[0]);
        Vector3f normal = crossProduct(vector1, vector2);

        normalize(normal);
        return normal;
    }

    public static float getDotProduct(@NotNull Vector3f v1, @NotNull Vector3f v2) {
        return (v1.getX() * v2.getX() + v1.getY() * v2.getY() + v1.getZ() * v2.getZ());
    }

    public static float getDotProduct(@NotNull Vector4f v1, @NotNull Vector4f v2) {
        return (v1.getX() * v2.getX() + v1.getY() * v2.getY() + v1.getZ() * v2.getZ() + v1.getW() * v2.getW());
    }

    public static double angleBetweenVectors(Vector3f vector1, Vector3f vector2) {
        float dotProduct = getDotProduct(vector1, vector2);
        float vectorsMagnitude = magnitude(vector1) * magnitude(vector2);

        if(vectorsMagnitude == 0){
            return 0;
        }

        double angle = Math.acos(dotProduct / vectorsMagnitude);
        if (angle == Float.NaN) {
            return 0;
        }
        return (angle);
    }

    public static @NotNull Vector4f crossProduct(@NotNull Vector4f v1, @NotNull Vector4f v2, @NotNull Vector4f v3) {
        Vector4f v4 = new Vector4f();

        float x = (v1.getY() * v2.getZ() * v3.getW()) + (v1.getZ() * v2.getW() * v3.getY()) + (v1.getW() * v2.getY() * v3.getZ())
                - (v1.getY() * v2.getW() * v3.getZ()) - (v1.getZ() * v2.getY() * v3.getW()) - (v1.getW() * v2.getZ() * v3.getY());

        float y = (v1.getX() * v2.getW() * v3.getZ()) + (v1.getZ() * v2.getX() * v3.getW()) + (v1.getW() * v2.getZ() * v3.getX())
                - (v1.getX() * v2.getZ() * v3.getW()) - (v1.getZ() * v2.getW() * v3.getX()) - (v1.getW() * v2.getX() * v3.getZ());

        float z = (v1.getX() * v2.getY() * v3.getW()) + (v1.getY() * v2.getW() * v3.getX()) + (v1.getW() * v2.getX() * v3.getY())
                - (v1.getX() * v2.getW() * v3.getY()) - (v1.getY() * v2.getX() * v3.getW()) - (v1.getW() * v2.getY() * v3.getX());

        float w = (v1.getX() * v2.getZ() * v3.getY()) + (v1.getY() * v2.getX() * v3.getZ()) + (v1.getZ() * v2.getY() * v3.getX())
                - (v1.getX() * v2.getY() * v3.getZ()) - (v1.getY() * v2.getZ() * v3.getX()) - (v1.getZ() * v2.getX() * v3.getY());

        v4.setTo(x, y, z, w);
        return v4;

    }

    public static float distance(Vector3f v1, Vector3f v2) {
        Vector3f res = VectorMath.subtract(v1, v2);
        return VectorMath.magnitude(res);
    }

    public static float magnitude(@NotNull Vector3f v1) {
        float r = (float) Math.sqrt((v1.getX() * v1.getX()) + (v1.getY() * v1.getY()) + (v1.getZ() * v1.getZ()));
        if (r <= 0.00001) {
            return 0f;
        } else {
            return r;
        }
    }

    public static float magnitude(@NotNull Vector4f v1) {
        return (float) Math.sqrt((v1.getX() * v1.getX()) + (v1.getY() * v1.getY())
                + (v1.getZ() * v1.getZ()) + (v1.getW() * v1.getW()));
    }

    public static @NotNull Vector3f normalize(Vector3f v1) {
        float f = magnitude(v1);

        if(f == 0){
            return new Vector3f();
        }

        v1.setX(v1.getX() / f);
        v1.setY(v1.getY()/ f);
        v1.setZ(v1.getZ()/ f);

        return v1;
    }

    public static @NotNull Vector4f normalize(Vector4f v1) {
        float f = magnitude(v1);
        
        if(f == 0){
            return new Vector4f();
        }

        v1.setX(v1.getX() / f);
        v1.setY(v1.getY()/ f);
        v1.setZ(v1.getZ()/ f);
        v1.setW(v1.getW()/ f);

        return v1;
    }

    public static boolean equals(@NotNull Vector3f v1, float x, float y, float z) {
        return (v1.getX() == x && v1.getY() == y && v1.getZ() == z);
    }

    public static boolean equals(@NotNull Vector3f v1, @NotNull Vector3f v2) {
        return (v1.getX() == v2.getX() && v1.getY() == v2.getY() && v1.getZ() == v2.getZ());
    }

    public static boolean equals(@NotNull Vector4f v1, @NotNull Vector4f v2) {
        return (v1.getX() == v2.getX() && v1.getY() == v2.getY() && v1.getZ() == v2.getZ() && v1.getW() == v2.getW());
    }

    public boolean equals(@NotNull Vector4f v1, float x, float y, float z, float w) {
        return (v1.getX() == x && v1.getY() == y && v1.getZ() == z && v1.getW() == w);
    }
}
