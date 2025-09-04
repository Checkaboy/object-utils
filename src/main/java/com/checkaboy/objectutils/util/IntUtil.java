package com.checkaboy.objectutils.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IntUtil {

    public static List<Integer> cast(List list) {
        List<Integer> longs = new ArrayList<>(list.size());
        for (Object aInt : list) {
            if (aInt instanceof Integer) {
                int i = (Integer) aInt;
                longs.add(i);
            }
        }

        return longs;
    }

    public static Integer cast(Object val) {
        return cast(val, null, null);
    }

    public static Integer cast(Object val, Integer defaultVal) {
        return cast(val, defaultVal, null);
    }

    public static Integer cast(Object val, IExCallback exCallback) {
        return cast(val, null, exCallback);
    }

    public static Integer cast(Object val, Integer defaultVal, IExCallback exCallback) {
        Integer aInt;

        try {
            aInt = (Integer) val;
        } catch (Exception e) {
            if (exCallback != null) exCallback.signal(e);
            aInt = defaultVal;
        }

        return aInt;
    }

    public static int parse(String value) {
        return parse(value, List.of(" "));
    }

    public static int parse(String value, List<String> replaces) {
        return parse(value, replaces, null);
    }

    public static int parse(String value, List<String> replaces, IExCallback exCallback) {
        if (value == null) return -1;
        if (Objects.equals(value, "")) return -1;

        try {
            value = value.trim();
            for (String replace : replaces)
                value = value.replaceAll(replace, "");
            return Integer.parseInt(value);
        } catch (Exception e) {
            if (exCallback != null) exCallback.signal(e);
            return -1;
        }
    }


}
