package de.relluem94.rellulib.vector;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class VectorMath {
    public VectorMath() {
        throw new IllegalStateException("Utility class");
    }

    @Contract("_, _, _ -> new")
    public static <T extends Number> @NotNull Vector1<T> add(@NotNull Vector1<T> v1, @NotNull Vector1<? extends Number> v2, Class<T> clazz) {
        Number x1 = v1.x();
        Number x2 = v2.x();
        return new Vector1<>(
                castToType(addNumbers(x1, x2), clazz)
        );
    }

    @Contract("_, _, _ -> new")
    public static <T extends Number> @NotNull Vector2<T> add(@NotNull Vector2<T> v1, @NotNull Vector1<? extends Number> v2, Class<T> clazz) {
        Number x1 = v1.x();
        Number x2 = v2.x();
        T y1 = v1.y();
        return new Vector2<>(
                castToType(addNumbers(x1, x2), clazz),
                y1
        );
    }

    @Contract("_, _, _ -> new")
    public static <T extends Number> @NotNull Vector2<T> add(@NotNull Vector2<T> v1, @NotNull Vector2<? extends Number> v2, Class<T> clazz) {
        Number x1 = v1.x();
        Number x2 = v2.x();
        Number y1 = v1.y();
        Number y2 = v2.y();
        return new Vector2<>(
                castToType(addNumbers(x1, x2), clazz),
                castToType(addNumbers(y1, y2), clazz)
        );
    }

    @Contract("_, _, _ -> new")
    public static <T extends Number> @NotNull Vector3<T> add(@NotNull Vector3<T> v1, @NotNull Vector1<? extends Number> v2, Class<T> clazz) {
        Number x1 = v1.x();
        Number x2 = v2.x();
        T y1 = v1.y();
        T z1 = v1.z();
        return new Vector3<>(
                castToType(addNumbers(x1, x2), clazz),
                y1,
                z1
        );
    }

    @Contract("_, _, _ -> new")
    public static <T extends Number> @NotNull Vector3<T> add(@NotNull Vector3<T> v1, @NotNull Vector2<? extends Number> v2, Class<T> clazz) {
        Number x1 = v1.x();
        Number x2 = v2.x();
        Number y1 = v1.y();
        Number y2 = v2.y();
        T z1 = v1.z();
        return new Vector3<>(
                castToType(addNumbers(x1, x2), clazz),
                castToType(addNumbers(y1, y2), clazz),
                z1
        );
    }

    @Contract("_, _, _ -> new")
    public static <T extends Number> @NotNull Vector3<T> add(@NotNull Vector3<T> v1, @NotNull Vector3<? extends Number> v2, Class<T> clazz) {
        Number x1 = v1.x();
        Number x2 = v2.x();
        Number y1 = v1.y();
        Number y2 = v2.y();
        Number z1 = v1.z();
        Number z2 = v2.z();
        return new Vector3<>(
                castToType(addNumbers(x1, x2), clazz),
                castToType(addNumbers(y1, y2), clazz),
                castToType(addNumbers(z1, z2), clazz)
        );
    }

    @Contract("_, _, _ -> new")
    public static <T extends Number> @NotNull Vector4<T> add(@NotNull Vector4<T> v1, @NotNull Vector1<? extends Number> v2, Class<T> clazz) {
        Number x1 = v1.x();
        Number x2 = v2.x();
        T y1 = v1.y();
        T z1 = v1.z();
        T w1 = v1.w();
        return new Vector4<>(
                castToType(addNumbers(x1, x2), clazz),
                y1,
                z1,
                w1
        );
    }

    @Contract("_, _, _ -> new")
    public static <T extends Number> @NotNull Vector4<T> add(@NotNull Vector4<T> v1, @NotNull Vector2<? extends Number> v2, Class<T> clazz) {
        Number x1 = v1.x();
        Number x2 = v2.x();
        Number y1 = v1.y();
        Number y2 = v2.y();
        T z1 = v1.z();
        T w1 = v1.w();
        return new Vector4<>(
                castToType(addNumbers(x1, x2), clazz),
                castToType(addNumbers(y1, y2), clazz),
                z1,
                w1
        );
    }

    @Contract("_, _, _ -> new")
    public static <T extends Number> @NotNull Vector4<T> add(@NotNull Vector4<T> v1, @NotNull Vector3<? extends Number> v2, Class<T> clazz) {
        Number x1 = v1.x();
        Number x2 = v2.x();
        Number y1 = v1.y();
        Number y2 = v2.y();
        Number z1 = v1.z();
        Number z2 = v2.z();
        T w1 = v1.w();
        return new Vector4<>(
                castToType(addNumbers(x1, x2), clazz),
                castToType(addNumbers(y1, y2), clazz),
                castToType(addNumbers(z1, z2), clazz),
                w1
        );
    }

    @Contract("_, _, _ -> new")
    public static <T extends Number> @NotNull Vector4<T> add(@NotNull Vector4<T> v1, @NotNull Vector4<? extends Number> v2, Class<T> clazz) {
        Number x1 = v1.x();
        Number x2 = v2.x();
        Number y1 = v1.y();
        Number y2 = v2.y();
        Number z1 = v1.z();
        Number z2 = v2.z();
        Number w1 = v1.w();
        Number w2 = v2.w();
        return new Vector4<>(
                castToType(addNumbers(x1, x2), clazz),
                castToType(addNumbers(y1, y2), clazz),
                castToType(addNumbers(z1, z2), clazz),
                castToType(addNumbers(w1, w2), clazz)
        );
    }

    @Contract("_, _, _ -> new")
    public static <T extends Number> @NotNull Vector5<T> add(@NotNull Vector5<T> v1, @NotNull Vector1<? extends Number> v2, Class<T> clazz) {
        Number x1 = v1.x();
        Number x2 = v2.x();
        T y1 = v1.y();
        T z1 = v1.z();
        T w1 = v1.w();
        T v1Val = v1.v();
        return new Vector5<>(
                castToType(addNumbers(x1, x2), clazz),
                y1,
                z1,
                w1,
                v1Val
        );
    }

    @Contract("_, _, _ -> new")
    public static <T extends Number> @NotNull Vector5<T> add(@NotNull Vector5<T> v1, @NotNull Vector2<? extends Number> v2, Class<T> clazz) {
        Number x1 = v1.x();
        Number x2 = v2.x();
        Number y1 = v1.y();
        Number y2 = v2.y();
        T z1 = v1.z();
        T w1 = v1.w();
        T v1Val = v1.v();
        return new Vector5<>(
                castToType(addNumbers(x1, x2), clazz),
                castToType(addNumbers(y1, y2), clazz),
                z1,
                w1,
                v1Val
        );
    }

    @Contract("_, _, _ -> new")
    public static <T extends Number> @NotNull Vector5<T> add(@NotNull Vector5<T> v1, @NotNull Vector3<? extends Number> v2, Class<T> clazz) {
        Number x1 = v1.x();
        Number x2 = v2.x();
        Number y1 = v1.y();
        Number y2 = v2.y();
        Number z1 = v1.z();
        Number z2 = v2.z();
        T w1 = v1.w();
        T v1Val = v1.v();
        return new Vector5<>(
                castToType(addNumbers(x1, x2), clazz),
                castToType(addNumbers(y1, y2), clazz),
                castToType(addNumbers(z1, z2), clazz),
                w1,
                v1Val
        );
    }

    @Contract("_, _, _ -> new")
    public static <T extends Number> @NotNull Vector5<T> add(@NotNull Vector5<T> v1, @NotNull Vector4<? extends Number> v2, Class<T> clazz) {
        Number x1 = v1.x();
        Number x2 = v2.x();
        Number y1 = v1.y();
        Number y2 = v2.y();
        Number z1 = v1.z();
        Number z2 = v2.z();
        Number w1 = v1.w();
        Number w2 = v2.w();
        T v1Val = v1.v();
        return new Vector5<>(
                castToType(addNumbers(x1, x2), clazz),
                castToType(addNumbers(y1, y2), clazz),
                castToType(addNumbers(z1, z2), clazz),
                castToType(addNumbers(w1, w2), clazz),
                v1Val
        );
    }

    @Contract("_, _, _ -> new")
    public static <T extends Number> @NotNull Vector5<T> add(@NotNull Vector5<T> v1, @NotNull Vector5<? extends Number> v2, Class<T> clazz) {
        Number x1 = v1.x();
        Number x2 = v2.x();
        Number y1 = v1.y();
        Number y2 = v2.y();
        Number z1 = v1.z();
        Number z2 = v2.z();
        Number w1 = v1.w();
        Number w2 = v2.w();
        Number v1Val = v1.v();
        Number v2Val = v2.v();

        return new Vector5<>(
                castToType(addNumbers(x1, x2), clazz),
                castToType(addNumbers(y1, y2), clazz),
                castToType(addNumbers(z1, z2), clazz),
                castToType(addNumbers(w1, w2), clazz),
                castToType(addNumbers(v1Val, v2Val), clazz)
        );
    }

    private static <T extends Number> T castToType(Number n, Class<T> clazz) {
        if (clazz == Integer.class) return clazz.cast(n.intValue());
        if (clazz == Float.class) return clazz.cast(n.floatValue());
        if (clazz == Double.class) return clazz.cast(n.doubleValue());
        if (clazz == Long.class) return clazz.cast(n.longValue());
        if (clazz == Short.class) return clazz.cast(n.shortValue());
        throw new IllegalArgumentException("Unsupported type: " + clazz);
    }

    private static @NotNull Number addNumbers(Number a, Number b) {
        if (a instanceof Integer) {
            return a.intValue() + b.intValue();
        } else if (a instanceof Float) {
            return a.floatValue() + b.floatValue();
        } else if (a instanceof Double) {
            return a.doubleValue() + b.doubleValue();
        } else if (a instanceof Long) {
            return a.longValue() + b.longValue();
        } else if (a instanceof Short) {
            return a.shortValue() + b.shortValue();
        } else {
            return a.doubleValue() + b.doubleValue();
        }
    }
}
