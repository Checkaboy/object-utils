package com.checkaboy.objectutils.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Taras Shaptala
 */
public class BigDecimalComparator {

    public enum Comparator {
        NOT_EQUAL,
        EQUAL,
        MORE,
        MORE_OR_EQUAL,
        LESS,
        LESS_OR_EQUAL,
        ;
    }

    public static boolean compare(BigDecimal bigDecimal, BigDecimal compare, int decimalPlaces, Comparator comparator) {
        if (comparator == Comparator.EQUAL && bigDecimal == null && compare == null)
            return true;
        else if (comparator != Comparator.EQUAL && bigDecimal == null && compare == null)
            return false;
        else if (comparator != Comparator.NOT_EQUAL && (bigDecimal == null || compare == null))
            return false;
        else if (comparator == Comparator.NOT_EQUAL && (bigDecimal == null || compare == null))
            return true;
        return compare(bigDecimal, compare.doubleValue(), decimalPlaces, comparator);
    }

    public static boolean compare(BigDecimal bigDecimal, double compare, int decimalPlaces, Comparator comparator) {
        BigDecimal compareBigDecimal = new BigDecimal(compare);

        int to = bigDecimal.setScale(decimalPlaces, RoundingMode.HALF_UP)
                .compareTo(compareBigDecimal.setScale(decimalPlaces, RoundingMode.HALF_UP));

        switch (comparator) {
            case NOT_EQUAL:
                return to != 0;
            case EQUAL:
                return to == 0;
            case MORE:
                return to > 0;
            case MORE_OR_EQUAL:
                return to >= 0;
            case LESS:
                return to < 0;
            case LESS_OR_EQUAL:
                return to <= 0;
            default:
                throw new RuntimeException("Не удалось выполнить операцию сравнения");
        }
    }

    public static BigDecimal max(int decimalPlaces, BigDecimal... values) {
        if (values == null || values.length == 0)
            return null;

        if (values.length == 1)
            return values[0];

        BigDecimal max = values[0];

        for (int i = 1; i < values.length; i++) {
            if (compare(values[i], max, decimalPlaces, Comparator.MORE))
                max = values[i];
        }

        return max;
    }

    public static BigDecimal min(int decimalPlaces, BigDecimal... values) {
        if (values == null || values.length == 0)
            return null;

        if (values.length == 1)
            return values[0];

        BigDecimal min = values[0];

        for (int i = 1; i < values.length; i++) {
            if (compare(values[i], min, decimalPlaces, Comparator.LESS))
                min = values[i];
        }

        return min;
    }

}
