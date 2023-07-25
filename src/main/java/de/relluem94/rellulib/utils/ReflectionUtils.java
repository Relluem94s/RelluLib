package de.relluem94.rellulib.utils;

import java.lang.reflect.Field;

public class ReflectionUtils {

    private ReflectionUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Field[] getMemberFields(Object obj) {
        Class<?> objClass = obj.getClass();
        LogUtils.info("Class: " + obj.getClass().getName());

        Field[] fields = objClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
        }
        return fields;
    }

    public static Field getMemberField(Object obj, String name) {
        Class<?> objClass = obj.getClass();

        Field[] fields = objClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equals(name)) {
                return field;
            } else {
                return null;
            }
        }
        return null;
    }

    public static boolean setValue(Field field, Object o, Object val) {
        try {
            field.set(o, val);
            return true;
        } catch (IllegalArgumentException | IllegalAccessException e) {
            LogUtils.error(e.getMessage());
            return false;
        }
    }

    public static void setAccessible(Field field, Object o, boolean accessible) {
        try {
            field.setAccessible(accessible);
        } catch (IllegalArgumentException e) {
            LogUtils.error(e.getMessage());
        }
    }

    public static Object getValue(Field field, Object o) {
        try {
            return field.get(o);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            LogUtils.error(e.getMessage());
            return null;
        }
    }

    public static Object getName(Field field, Object o) {
        try {
            return field.getName();
        } catch (IllegalArgumentException e) {
            LogUtils.error(e.getMessage());
            return null;
        }
    }

    public static Object getModifiers(Field field, Object o) {
        try {
            return field.getModifiers();
        } catch (IllegalArgumentException e) {
            LogUtils.error(e.getMessage());
            return null;
        }
    }

    public static Object getType(Field field, Object o) {
        try {
            return field.getType().getName();
        } catch (IllegalArgumentException e) {
            LogUtils.error(e.getMessage());
            return null;
        }
    }

    public static Object getAnnotations(Field field, Object o) {
        try {
            return field.getAnnotations();
        } catch (IllegalArgumentException e) {
            LogUtils.error(e.getMessage());
            return null;
        }
    }
}
