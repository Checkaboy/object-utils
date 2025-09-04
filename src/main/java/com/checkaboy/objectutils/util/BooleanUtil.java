package com.checkaboy.objectutils.util;

import java.util.Objects;

public class BooleanUtil {

    public static boolean is(Boolean bool) {
        return bool != null && bool;
    }

    public static boolean isOrIfNull(Boolean bool, boolean ifNull) {
        if (bool == null) return ifNull;
        return bool;
    }

    public static boolean parse(String value, String truePattern) {
        if(value == null) return false;
        return Objects.equals(value, truePattern);
    }

    public static boolean parse(String value) {
        if(value == null) return false;
        return Objects.equals(value, "true");
    }

}
