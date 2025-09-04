package com.checkaboy.objectutils.util;

import com.checkaboy.objectutils.model.Named;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TextSetterUtil {

    public static String textNullWrapper(String text) {
        return textNullWrapper(text, "");
    }

    public static String textNullWrapper(String text, String nullValue) {
        return text == null ? nullValue : text;
    }

    public static String setterObject(Object o) {
        if (o == null) return null;

        if (o instanceof Date) return parseDate((Date) o);
        if (o instanceof Boolean) return parseBoolean((Boolean) o);
        if (o instanceof Named) return ((Named) o).getName();

        return o.toString();
    }

    public static String setterObjectOrElseIfNull(Object o, String defaultValue) {
        if (o == null) return defaultValue;

        if (o instanceof Date) return parseDate((Date) o);
        if (o instanceof Boolean) return parseBoolean((Boolean) o);
        if (o instanceof Named) return ((Named) o).getName();

        return o.toString();
    }

    public static String setterBoolean(Boolean o) {
        if (o == null) return null;

        return parseBoolean(o);
    }

    public static String setterBooleanOrElse(Boolean o, String defaultValue) {
        if (o == null) return defaultValue;

        return parseBoolean(o);
    }

    public static String setterDate(Date o) {
        if (o == null) return null;

        return parseDate(o);
    }

    public static String setterDate(Date o, String pattern) {
        if (o == null) return null;

        return parseDate(o, pattern);
    }

    public static String setterDateOrElse(Date o, String defaultValue) {
        if (o == null) return defaultValue;

        return parseDate(o);
    }

    public static String setterDateOrElse(Date o, String pattern, String defaultValue) {
        if (o == null) return defaultValue;

        return parseDate(o, pattern);
    }

    protected static String parseBoolean(Boolean bool) {
        return (bool ? "Да" : "Нет");
    }

    protected static String parseDate(Date date) {
        return parseDate(date, "dd.MM.yyyy HH:mm");
    }

    protected static String parseDate(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public static String textLengthInspector(int maxLength, String oldValue, String newValue) {
        if (newValue == null) return null;
        if (newValue.length() <= maxLength) {
            return newValue;
        } else if (oldValue.length() <= maxLength) {
            return oldValue;
        } else {
            throw new IllegalArgumentException("Длина текста превышает возможное значение в " + maxLength + " символов");
        }
    }

    public static String textLengthInspector(String oldValue, String newValue) {
        return textLengthInspector(255, oldValue, newValue);
    }

}
