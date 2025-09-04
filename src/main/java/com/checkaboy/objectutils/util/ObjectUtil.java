package com.checkaboy.objectutils.util;

import java.util.Comparator;
import java.util.List;

public class ObjectUtil {

    public static boolean equals(Object... objects) {
        for (int i = 0; i < objects.length - 1; i++) {
            if (!objects[i].equals(objects[i + 1]))
                return false;
        }

        return true;
    }

    public static boolean equals(List<Object> objects) {
        for (int i = 0; i < objects.size() - 1; i++) {
            if (!objects.get(i).equals(objects.get(i + 1)))
                return false;
        }

        return true;
    }

    public static <T> boolean equalsWithGetter(List<T> objects, Comparator<T> c) {
        for (int i = 0; i < objects.size() - 1; i++) {
            if (c.compare(objects.get(i), objects.get(i + 1)) != 0)
                return false;
        }

        return true;
    }

    public static <T> T getWithoutNull(T obj, T isNull) {
        return obj == null ? isNull : obj;
    }

}
