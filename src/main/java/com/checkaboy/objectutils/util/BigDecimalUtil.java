package com.checkaboy.objectutils.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Function;

public class BigDecimalUtil {

    // Extract or convert

    public static <I> BigDecimal valueOf(Function<I, Double> extractor, I i) {
        try {
            return BigDecimal.valueOf(extractor.apply(i));
        } catch (Exception ignore) {
            return null;
        }
    }

    public static BigDecimal valueOf(Object o) {
        if (o instanceof String)
            return valueOf((String) o);
        else if (o instanceof Integer)
            return valueOf((Integer) o);
        else if (o instanceof Long)
            return valueOf((Long) o);
        else if (o instanceof Float)
            return valueOf((Float) o);
        else if (o instanceof Double)
            return valueOf((Double) o);

        return null;
    }

    public static BigDecimal valueOf(String str) {
        try {
            return new BigDecimal(str);
        } catch (Exception ignore) {
            return null;
        }
    }

    public static BigDecimal valueOf(Integer integer) {
        try {
            return new BigDecimal(integer);
        } catch (Exception ignore) {
            return null;
        }
    }

    public static BigDecimal valueOf(Long aLong) {
        try {
            return new BigDecimal(aLong);
        } catch (Exception ignore) {
            return null;
        }
    }

    public static BigDecimal valueOf(Float aFloat) {
        try {
            return new BigDecimal(aFloat);
        } catch (Exception ignore) {
            return null;
        }
    }

    public static BigDecimal valueOf(Double aDouble) {
        try {
            return new BigDecimal(aDouble);
        } catch (Exception ignore) {
            return null;
        }
    }

    public static BigDecimal getBigDecimal(BigDecimal validate, long upperLimit, int decimalPlaces) {
        if (validate == null) return null;

        double minusValue = Math.pow(0.1d, decimalPlaces);
        double val = validate.doubleValue();

        if (val >= upperLimit) {
            val = upperLimit;
            val -= minusValue;
        } else if (val <= (upperLimit * -1)) {
            val = upperLimit * -1;
            val += minusValue;
        }

        validate = new BigDecimal(val);
        return validate.setScale(decimalPlaces, RoundingMode.HALF_UP);
    }

    public static BigDecimal getBigDecimal(BigDecimal validate, long upperLimit, int decimalPlaces, boolean obsession) {
        if (validate == null) return null;

        if (obsession) {
            double val = validate.doubleValue();

            if (val >= upperLimit) {
                int round = (int) (val / upperLimit);
                val -= upperLimit * round;
            }

            validate = new BigDecimal(val);
            return validate.setScale(decimalPlaces, RoundingMode.HALF_UP);
        } else {
            return getBigDecimal(validate, upperLimit, decimalPlaces);
        }
    }

    public static Double getDoubleValue(BigDecimal bigDecimal) {
        try {
            return bigDecimal.doubleValue();
        } catch (Exception e) {
            return null;
        }
    }

    public static Double getDoubleValue(BigDecimal bigDecimal, double isNull) {
        try {
            return bigDecimal.doubleValue();
        } catch (Exception e) {
            return isNull;
        }
    }

    public static Integer getIntegerValue(BigDecimal bigDecimal) {
        try {
            return bigDecimal.intValue();
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer getIntegerValue(BigDecimal bigDecimal, int isNull) {
        try {
            return bigDecimal.intValue();
        } catch (Exception e) {
            return isNull;
        }
    }

    public static Double getDoubleValueWithScale(BigDecimal bigDecimal, int scale, RoundingMode roundingMode) {
        try {
            return bigDecimal.setScale(scale, roundingMode).doubleValue();
        } catch (Exception e) {
            return null;
        }
    }

    public static BigDecimal getBigDecimal(Double value) {
        if (value == null) return null;
        return BigDecimal.valueOf(value);
    }

    public static BigDecimal sum(BigDecimal... bigDecimals) {
        BigDecimal sum = new BigDecimal(0);

        for (BigDecimal bigDecimal : bigDecimals) {
            if (bigDecimal != null)
                sum = sum.add(bigDecimal);
        }

        return sum;
    }

    public static BigDecimal multiply(BigDecimal... bigDecimals) {
        BigDecimal sum = new BigDecimal(1);

        for (BigDecimal bigDecimal : bigDecimals) {
            if (bigDecimal != null)
                sum = sum.multiply(bigDecimal);
            else return null;
        }

        return sum;
    }

    public static BigDecimal pow(BigDecimal bigDecimal, int power) {
        if (bigDecimal == null) return new BigDecimal(0);
        return bigDecimal.pow(power);
    }

    public static BigDecimal scale(BigDecimal bigDecimal, int scale, RoundingMode roundingMode) {
        if (bigDecimal == null) return null;
        return bigDecimal.setScale(scale, roundingMode == null ? RoundingMode.HALF_UP : roundingMode);
    }

    public static BigDecimal scale(BigDecimal bigDecimal, int scale) {
        return scale(bigDecimal, scale, null);
    }

    public static BigDecimal divide(BigDecimal value, Number divider, RoundingMode roundingMode, int scale) {
        BigDecimal decimalDivider = valueOf(divider + "");

        try {
            if (decimalDivider != null)
                return value.divide(decimalDivider, scale, roundingMode);
        } catch (Exception ignore) {
        }

        return value;
    }

}
