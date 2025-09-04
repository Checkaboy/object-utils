package com.checkaboy.objectutils.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LongUtil {

    public static List<Long> cast(List list) {
        List<Long> longs = new ArrayList<>(list.size());
        for (Object aLong : list) {
            if (aLong instanceof Integer) {
                long l = (Integer) aLong;
                longs.add(l);
            } else if (aLong instanceof Long) {
                long l = (Long) aLong;
                longs.add(l);
            }
        }

        return longs;
    }

    public static Long cast(Object val) {
        return cast(val, null, null);
    }

    public static Long cast(Object val, Long defaultVal) {
        return cast(val, defaultVal, null);
    }

    public static Long cast(Object val, IExCallback exCallback) {
        return cast(val, null, exCallback);
    }

    public static Long cast(Object val, Long defaultVal, IExCallback exCallback) {
        Long aLong;

        try {
            aLong = (Long) val;
        } catch (Exception e) {
            if (exCallback != null) exCallback.signal(e);
            aLong = defaultVal;
        }

        return aLong;
    }

    public static long parse(String value) {
        return parse(value, List.of(" "));
    }

    public static long parse(String value, List<String> replaces) {
        return parse(value, replaces, null);
    }

    public static long parse(String value, List<String> replaces, IExCallback exCallback) {
        if (value == null) return -1L;
        if (Objects.equals(value, "")) return -1L;

        try {
            value = value.trim();
            for (String replace : replaces)
                value = value.replaceAll(replace, "");
            return Long.parseLong(value);
        } catch (Exception e) {
            if (exCallback != null) exCallback.signal(e);
            return -1L;
        }
    }

}
